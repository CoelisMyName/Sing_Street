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
import com.alina.singstreet.adapter.ProfileCardAdapter;
import com.alina.singstreet.databinding.FragmentFollowBinding;
import com.alina.singstreet.model.ProfileModel;

import java.util.List;

public class FollowFragment extends Fragment {
    FragmentFollowBinding binding;
    ProfileCardAdapter adapter;
    FollowViewModel followViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follow, container, false);
        followViewModel = new ViewModelProvider(this).get(FollowViewModel.class);
        adapter = new ProfileCardAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);
        binding.appbar.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.appbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });
        adapter.setListener(new ProfileCardAdapter.ProfileCardListener() {
            @Override
            public void click(String userUID) {
                Navigation.findNavController(binding.getRoot()).navigate(FollowFragmentDirections.Profile(userUID));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.appbar.toolbar.setTitle(FollowFragmentArgs.fromBundle(getArguments()).getTitle());
        String userUID = FollowFragmentArgs.fromBundle(getArguments()).getUserUserUID();
        int mode = FollowFragmentArgs.fromBundle(getArguments()).getFollow();
        final int follower = getResources().getInteger(R.integer.follower);
        final int following = getResources().getInteger(R.integer.following);
        if (mode == follower) {
            followViewModel.getFollowerProfileByUserUID(userUID).observe(getViewLifecycleOwner(), new Observer<List<ProfileModel>>() {
                @Override
                public void onChanged(List<ProfileModel> profileModels) {
                    adapter.submitList(profileModels);
                    adapter.notifyDataSetChanged();
                }
            });
        } else if (mode == following) {
            followViewModel.getFollowingProfileByUserUID(userUID).observe(getViewLifecycleOwner(), new Observer<List<ProfileModel>>() {
                @Override
                public void onChanged(List<ProfileModel> profileModels) {
                    adapter.submitList(profileModels);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            Navigation.findNavController(binding.getRoot()).navigateUp();
        }
    }
}
