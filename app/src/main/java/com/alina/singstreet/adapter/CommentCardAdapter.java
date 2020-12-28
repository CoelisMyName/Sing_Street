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
import com.alina.singstreet.databinding.CommentcardBinding;
import com.alina.singstreet.databinding.SingcardBinding;
import com.alina.singstreet.model.CommentModel;
import com.alina.singstreet.model.SingCardModel;

public class CommentCardAdapter extends ListAdapter<CommentModel, CommentCardAdapter.CommentViewHolder> {
    CommentCardListener listener;

    public CommentCardAdapter(){
        super(new DiffCallback());
    }

    protected CommentCardAdapter(@NonNull DiffUtil.ItemCallback<CommentModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentcardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.commentcard,parent,false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setListener(CommentCardListener listener) {
        this.listener = listener;
    }

    public interface CommentCardListener {
        void clickIcon(String userUID);
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        CommentcardBinding binding;

        public CommentViewHolder(@NonNull CommentcardBinding binding) {
            super(binding.getRoot());
            binding.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.clickIcon(getItem(getAdapterPosition()).userUID);
                    }
                }
            });
            this.binding = binding;
        }

        public void bind(CommentModel commentModel){
            binding.icon.setImageResource(commentModel.icon);
            binding.comment.setText(commentModel.comment);
            binding.nickname.setText(commentModel.nickname);
            binding.rate.setRating(commentModel.rate);
            binding.timestamp.setText(commentModel.timestamp);
        }
    }

    static class DiffCallback extends DiffUtil.ItemCallback<CommentModel> {

        @Override
        public boolean areItemsTheSame(@NonNull CommentModel oldItem, @NonNull CommentModel newItem) {
            return oldItem.commentUID.equals(newItem.commentUID);
        }

        @Override
        public boolean areContentsTheSame(@NonNull CommentModel oldItem, @NonNull CommentModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
