package com.alina.singstreet.view.profile;

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
import com.alina.singstreet.adapter.SingCardAdapter;
import com.alina.singstreet.databinding.FragmentProfileBinding;
import com.alina.singstreet.domain.Follow;
import com.alina.singstreet.model.ProfileDetailModel;
import com.alina.singstreet.model.ProfileModel;

public class ProfileFragment extends Fragment {
    ShareViewModel shareViewModel;
    ProfileViewModel profileViewModel;
    FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        SingCardAdapter adapter = new SingCardAdapter();
        adapter.setListener(new SingCardAdapter.SingCardListener() {
            @Override
            public void clickIcon(String userUID) {
                Navigation.findNavController(binding.getRoot()).navigate(ProfileFragmentDirections.Profile(userUID));
            }

            @Override
            public void clickSingCard(String postUID) {
                Navigation.findNavController(binding.getRoot()).navigate(ProfileFragmentDirections.Post(postUID));
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);

        binding.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        assert getArguments() != null;
        String userUID = ProfileFragmentArgs.fromBundle(getArguments()).getUserUserUID();
        profileViewModel.getProfileDetailByUserUID(userUID).observe(getViewLifecycleOwner(), new Observer<ProfileDetailModel>() {
            @Override
            public void onChanged(ProfileDetailModel profileDetailModel) {
                ProfileModel profileModel = profileDetailModel.profileModel;
                binding.follower.setText(getString(R.string.number_of_follower, profileModel.follower));
                binding.following.setText(getString(R.string.number_of_following, profileModel.following));
                binding.icon.setImageResource(profileModel.icon);
                binding.nickname.setText(profileModel.nickname);
                binding.phoneNumber.setText(profileModel.phoneNumber);
                adapter.submitList(profileDetailModel.list);
                adapter.notifyDataSetChanged();
            }
        });

        if (userUID.equals(shareViewModel.getUser().getUserUID())) {
            binding.button.setText("修改个人资料");
            //TODO setting fragment
        } else {
            profileViewModel.isFollowed(shareViewModel.getUser().getUserUID(), userUID).observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (integer > 0) {
                        binding.button.setText("取消关注");
                    } else {
                        binding.button.setText("关注");
                    }
                    binding.button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Follow follow = new Follow(userUID, shareViewModel.getUser().getUserUID());
                            if (integer > 0) {
                                profileViewModel.unfollow(follow);
                            } else {
                                profileViewModel.follow(follow);
                            }
                        }
                    });
                }
            });
        }

        binding.following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(ProfileFragmentDirections.Follow("正在关注", userUID, getResources().getInteger(R.integer.following)));
            }
        });
        binding.follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(ProfileFragmentDirections.Follow("关注者", userUID, getResources().getInteger(R.integer.follower)));
            }
        });

        return binding.getRoot();
    }
}
