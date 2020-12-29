package com.alina.singstreet.view.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alina.singstreet.R;
import com.alina.singstreet.ShareViewModel;
import com.alina.singstreet.adapter.SingCardAdapter;
import com.alina.singstreet.databinding.FragmentSearchBinding;
import com.alina.singstreet.model.SingCardModel;

import java.util.List;

public class SearchPostFragment extends Fragment {
    FragmentSearchBinding binding;
    SearchViewModel searchViewModel;
    ShareViewModel shareViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.appbar.toolbar.setTitle("搜索已关注用户动态");
        binding.appbar.toolbar.setNavigationIcon(R.drawable.ic_round_clear_24);
        binding.appbar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigateUp();
            }
        });
        binding.appbar.toolbar.inflateMenu(R.menu.menu_search);
        SearchView searchView = (SearchView) binding.appbar.toolbar.getMenu().findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewModel.setQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchViewModel.setQuery(newText);
                return true;
            }
        });

        SingCardAdapter adapter = new SingCardAdapter();
        adapter.setListener(new SingCardAdapter.SingCardListener() {
            @Override
            public void clickIcon(String userUID) {
                Navigation.findNavController(binding.getRoot()).navigate(SearchPostFragmentDirections.Profile(userUID));
            }

            @Override
            public void clickSingCard(String postUID) {
                Navigation.findNavController(binding.getRoot()).navigate(SearchPostFragmentDirections.Post(postUID));
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recycler.setAdapter(adapter);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(layoutManager);
        searchViewModel.getQuery().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                LiveData<List<SingCardModel>> liveData = searchViewModel.searchSingCard(shareViewModel.getUser().getUserUID(), s);
                liveData.observe(getViewLifecycleOwner(), new Observer<List<SingCardModel>>() {
                    @Override
                    public void onChanged(List<SingCardModel> singCardModels) {
                        adapter.submitList(singCardModels);
                        adapter.notifyDataSetChanged();
                        liveData.removeObservers(getViewLifecycleOwner());
                    }
                });
            }
        });
        return binding.getRoot();
    }
}
