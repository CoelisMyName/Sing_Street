package com.alina.singstreet.view.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alina.singstreet.R;
import com.alina.singstreet.databinding.SingcardBinding;
import com.alina.singstreet.model.SingCardModel;

public class HomeAdapter extends ListAdapter<SingCardModel, HomeAdapter.SingViewHolder> {

    public HomeAdapter(){
        super(new DiffCallback());

    }

    @NonNull
    @Override
    public SingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingcardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.singcard,parent,false);
        return new SingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SingViewHolder holder, int position) {
        SingCardModel item = getItem(position);
        holder.bind(item);
    }

    static class SingViewHolder extends RecyclerView.ViewHolder{
        SingcardBinding binding;

        public SingViewHolder(SingcardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SingCardModel item) {

        }
    }

    static class DiffCallback extends DiffUtil.ItemCallback<SingCardModel> {

        @Override
        public boolean areItemsTheSame(@NonNull SingCardModel oldItem, @NonNull SingCardModel newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SingCardModel oldItem, @NonNull SingCardModel newItem) {
            return false;
        }
    }

    public static interface OnSingCardListener{
        void clickIcon(String uuid);
    }
}
