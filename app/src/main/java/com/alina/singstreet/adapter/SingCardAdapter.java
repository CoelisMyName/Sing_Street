package com.alina.singstreet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alina.singstreet.R;
import com.alina.singstreet.databinding.SingcardBinding;
import com.alina.singstreet.model.SingCardModel;

public class SingCardAdapter extends ListAdapter<SingCardModel, SingCardAdapter.SingViewHolder> {
    SingCardListener listener;

    public SingCardAdapter() {
        super(new DiffCallback());
    }

    @NonNull
    @Override
    public SingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingcardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.singcard, parent, false);
        return new SingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SingViewHolder holder, int position) {
        SingCardModel item = getItem(position);
        holder.bind(item);
    }

    public void setListener(SingCardListener listener) {
        this.listener = listener;
    }

    public interface SingCardListener {
        void clickIcon(String userUID);

        void clickSingCard(String postUID);
    }

    static class DiffCallback extends DiffUtil.ItemCallback<SingCardModel> {

        @Override
        public boolean areItemsTheSame(@NonNull SingCardModel oldItem, @NonNull SingCardModel newItem) {
            return oldItem.postUID.equals(newItem.postUID);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SingCardModel oldItem, @NonNull SingCardModel newItem) {
            return oldItem.equals(newItem);
        }
    }

    class SingViewHolder extends RecyclerView.ViewHolder {
        SingcardBinding binding;

        public SingViewHolder(@NonNull SingcardBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.clickSingCard(getItem(getAdapterPosition()).postUID);
                    }
                }
            });
            binding.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.clickIcon(getItem(getAdapterPosition()).userUID);
                    }
                }
            });
            this.binding = binding;
        }

        public void bind(SingCardModel item) {
            binding.icon.setImageResource(item.icon);
            binding.timestamp.setText(item.timestamp);
            binding.rate.setRating(item.rate);
            binding.nickname.setText(item.nickname);
            binding.description.setText(item.description);
            binding.title.setText(item.title);
            binding.song.setText(item.song);
        }
    }
}
