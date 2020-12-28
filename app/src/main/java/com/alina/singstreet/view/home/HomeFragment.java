package com.alina.singstreet.view.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import com.alina.singstreet.adapter.SingCardAdapter;
import com.alina.singstreet.databinding.FragmentHomeBinding;
import com.alina.singstreet.databinding.HeaderBinding;
import com.alina.singstreet.model.ProfileModel;
import com.alina.singstreet.model.SingCardModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Vector;

public class HomeFragment extends Fragment {
    static final int PERMISSIONS = 1;
    FragmentHomeBinding binding;
    HeaderBinding headerBinding;
    ShareViewModel shareViewModel;
    HomeViewModel homeViewModel;
    ActionBarDrawerToggle drawerToggle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.appbar.toolbar.setTitle(R.string.app_name);
        drawerToggle = new ActionBarDrawerToggle(requireActivity(), binding.drawer, binding.appbar.toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        binding.drawer.addDrawerListener(drawerToggle);
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.logout) {
                    shareViewModel.logout();
                }
                if (item.getItemId() == R.id.profile) {
                    Navigation.findNavController(binding.getRoot()).navigate(HomeFragmentDirections.Profile(shareViewModel.getUser().getUserUID()));
                }
                //if(item.getItemId())
                return false;
            }
        });

        headerBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header, binding.navView, false);
        binding.navView.addHeaderView(headerBinding.getRoot());


        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        shareViewModel.getProfileByUserUID(shareViewModel.getUser().getUserUID()).observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                headerBinding.follower.setText(getString(R.string.number_of_follower, profileModel.follower));
                headerBinding.following.setText(getString(R.string.number_of_following, profileModel.following));
                headerBinding.icon.setImageResource(profileModel.icon);
                headerBinding.nickname.setText(profileModel.nickname);
                headerBinding.phoneNumber.setText(profileModel.phoneNumber);
            }
        });

        SingCardAdapter adapter = new SingCardAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);

        adapter.setListener(new SingCardAdapter.SingCardListener() {
            @Override
            public void clickIcon(String userUID) {
                Navigation.findNavController(binding.getRoot()).navigate(HomeFragmentDirections.Profile(userUID));
            }

            @Override
            public void clickSingCard(String postUID) {
                Navigation.findNavController(binding.getRoot()).navigate(HomeFragmentDirections.Post(postUID));
            }
        });

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getSingCardByUserUID(shareViewModel.getUser().getUserUID()).observe(getViewLifecycleOwner(), new Observer<List<SingCardModel>>() {
            @Override
            public void onChanged(List<SingCardModel> singCardModels) {
                adapter.submitList(singCardModels);
                adapter.notifyDataSetChanged();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.drawer.removeDrawerListener(drawerToggle);
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
            startSing();
        }
    }

    public void startSing() {
        Vector<String> vector = new Vector<>();
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            vector.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            vector.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            vector.add(Manifest.permission.RECORD_AUDIO);
        }

        if (vector.size() > 0) {
            String[] permissions = vector.toArray(new String[vector.size()]);
            ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSIONS);
        } else {
            Navigation.findNavController(binding.getRoot()).navigate(R.id._sing);
        }
    }
}
