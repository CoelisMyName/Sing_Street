package com.alina.singstreet.view.sing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.databinding.FragmentSingBinding;
import com.alina.singstreet.domain.Post;

public class SingFragment extends Fragment {
    FragmentSingBinding binding;
    SingViewModel singViewModel;
    ShareViewModel shareViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sing, container, false);
        singViewModel = new ViewModelProvider(this).get(SingViewModel.class);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        singViewModel.setUserUID(shareViewModel.getUser().getUserUID());
        binding.appbar.toolbar.setTitle("发表唱歌动态");
        binding.appbar.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.appbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });

        singViewModel.getPostLiveData().observe(getViewLifecycleOwner(), new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                if(post.getTitle() != null && post.getDescription() != null && post.getPath() != null
                        && post.getTimestamp() != null && post.getSong() != null && post.getUserUID() != null){
                    binding.appbar.toolbar.getMenu().getItem(0).setVisible(true);
                }
                else {
                    binding.appbar.toolbar.getMenu().getItem(0).setVisible(false);
                }
            }
        });
        singViewModel.getRecording().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case -1:
                        shareViewModel.showToast("录音错误，请检查是否提供权限");
                    case 0:
                        binding.textView.setText("长按开始唱歌");
                        break;
                    case 1:
                        binding.textView.setText("松手结束录音");
                        break;

                }
            }
        });

        binding.appbar.toolbar.inflateMenu(R.menu.menu_sing);
        binding.appbar.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.check){
                    singViewModel.post().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                shareViewModel.showToast("发布成功");
                                Navigation.findNavController(binding.getRoot()).navigateUp();
                            }
                            else {
                                shareViewModel.showToast("发布失败");
                            }
                        }
                    });
                }
                return true;
            }
        });

        binding.fab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    singViewModel.start();
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    singViewModel.stop();
                }
                return true;
            }
        });

        binding.description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() > 300){
                    binding.title.setError("描述信息最大不能超过300个字符");
                }
                else if(s.toString().trim().length() > 0){
                    singViewModel.setDescription(s.toString().trim());
                }
                else {
                    singViewModel.setDescription(null);
                }
            }
        });

        binding.song.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() > 20){
                    binding.title.setError("歌曲名最大不能超过20个字符");
                }
                else if(s.toString().trim().length() > 0){
                    singViewModel.setSong(s.toString().trim());
                }
                else {
                    singViewModel.setSong(null);
                }
            }
        });

        binding.title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() > 50){
                    binding.title.setError("标题最大不能超过50个字符");
                }
                else if(s.toString().trim().length() > 0){
                    singViewModel.setTitle(s.toString().trim());
                }
                else {
                    singViewModel.setTitle(null);
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        singViewModel.stop();
    }
}
