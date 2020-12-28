package com.alina.singstreet.view.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.List;

public class PostFragment extends Fragment {
    ShareViewModel shareViewModel;
    PostViewModel postViewModel;
    FragmentPostBinding binding;

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

        String postUID = PostFragmentArgs.fromBundle(getArguments()).getPostPostUID();
        postViewModel.getSingCardByPostUID(postUID).observe(getViewLifecycleOwner(), new Observer<SingCardModel>() {
            @Override
            public void onChanged(SingCardModel singCardModel) {
                binding.icon.setImageResource(singCardModel.icon);
                binding.description.setText(singCardModel.description);
                binding.nickname.setText(singCardModel.nickname);
                binding.rate.setRating(singCardModel.rate);
                binding.timestamp.setText(singCardModel.timestamp);
                binding.song.setText(singCardModel.song);
                binding.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postViewModel.start(singCardModel.path);
                    }
                });
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        CommentCardAdapter adapter = new CommentCardAdapter();
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);

        adapter.setListener(new CommentCardAdapter.CommentCardListener() {
            @Override
            public void clickIcon(String userUID) {

            }
        });

        postViewModel.getCommentByPostUID(postUID).observe(getViewLifecycleOwner(), new Observer<List<CommentModel>>() {
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                adapter.submitList(commentModels);
                adapter.notifyDataSetChanged();
            }
        });


        return binding.getRoot();
    }
}
