package com.alina.singstreet.view.post;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.adapter.CommentCardAdapter;
import com.alina.singstreet.databinding.FragmentPostBinding;
import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.SingCardModel;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class PostFragment extends Fragment {
    static final int PERMISSIONS = 1;
    ShareViewModel shareViewModel;
    PostViewModel postViewModel;
    FragmentPostBinding binding;
    String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        binding.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });
        binding.toolbar.setTitle("动态详情");

        assert getArguments() != null;
        String postUID = PostFragmentArgs.fromBundle(getArguments()).getPostPostUID();

        postViewModel.setUserUID(shareViewModel.getUser().getUserUID());
        postViewModel.setPostUID(postUID);

        postViewModel.getSingCardByPostUID(postUID).observe(getViewLifecycleOwner(), new Observer<SingCardModel>() {
            @Override
            public void onChanged(SingCardModel singCardModel) {
                binding.icon.setImageResource(singCardModel.icon);
                binding.description.setText(singCardModel.description);
                binding.nickname.setText(singCardModel.nickname);
                binding.rate.setRating(singCardModel.rate);
                binding.timestamp.setText(singCardModel.timestamp);
                binding.song.setText(singCardModel.song);
                path = singCardModel.path;

            }
        });

        postViewModel.getPlaying().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.play.setEnabled(!aBoolean);
            }
        });

        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlay();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        CommentCardAdapter adapter = new CommentCardAdapter();
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);

        adapter.setListener(new CommentCardAdapter.CommentCardListener() {
            @Override
            public void clickIcon(String userUID) {
                Navigation.findNavController(binding.getRoot()).navigate(PostFragmentDirections.Profile(userUID));
            }
        });

        postViewModel.getCommentByPostUID(postUID).observe(getViewLifecycleOwner(), new Observer<List<CommentModel>>() {
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                //Log.d("adapter", "onChanged: List<CommentModel> " + commentModels);
                adapter.submitList(commentModels);
                adapter.notifyDataSetChanged();
            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.comment.getText().toString().trim();
                float r = binding.rating.getRating();
                Log.d("binding.send", "onClick: s: " + s + " r: " + r);
                if (s.length() <= 0) {
                    binding.comment.setError("输入框为空");
                    return;
                }
                if (r <= 0) {
                    shareViewModel.showToast("评级为空");
                    return;
                }
                postViewModel.comment(s, r).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            shareViewModel.showToast("评论成功");
                            binding.comment.setText("");
                            binding.rating.setRating(0);
                        } else {
                            shareViewModel.showToast("评论失败");
                        }
                        postViewModel.refreshCommentUID();
                    }
                });
            }
        });

        postViewModel.getDuration().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setMax(integer);
            }
        });
        postViewModel.getPosition().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setProgress(integer);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        postViewModel.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS) {
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    shareViewModel.showToast("你拒绝提供权限");
                    return;
                }
            }
            startPlay();
        }
    }

    public void startPlay() {
        Vector<String> vector = new Vector<>();
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            vector.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (vector.size() > 0) {
            String[] permissions = vector.toArray(new String[vector.size()]);
            ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSIONS);
        } else {
            try {
                Log.d("try play record", "onClick: " + path);
                postViewModel.start(path);
            } catch (IOException e) {
                shareViewModel.showToast("播放失败");
                Log.d("try play record", "onClick: " + path);
            }
        }
    }
}
