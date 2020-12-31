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
import com.alina.singstreet.databinding.ProfilecardBinding;
import com.alina.singstreet.model.ProfileModel;

public class ProfileCardAdapter extends ListAdapter<ProfileModel, ProfileCardAdapter.ProfileViewHolder> {
    ProfileCardListener listener;

    public ProfileCardAdapter() {
        super(new DiffCallback());
    }

    protected ProfileCardAdapter(@NonNull DiffUtil.ItemCallback<ProfileModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.profilecard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setListener(ProfileCardListener listener) {
        this.listener = listener;
    }

    public interface ProfileCardListener {
        void click(String userUID);
    }

    static class DiffCallback extends DiffUtil.ItemCallback<ProfileModel> {

        @Override
        public boolean areItemsTheSame(@NonNull ProfileModel oldItem, @NonNull ProfileModel newItem) {
            return oldItem.userUID.equals(newItem.userUID);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProfileModel oldItem, @NonNull ProfileModel newItem) {
            return oldItem.equals(newItem);
        }
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        ProfilecardBinding binding;

        public ProfileViewHolder(@NonNull ProfilecardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.click(getItem(getAdapterPosition()).userUID);
                    }
                }
            });
        }

        public void bind(ProfileModel profileModel) {
            binding.icon.setImageResource(profileModel.icon);
            binding.nickname.setText(profileModel.nickname);
            binding.phoneNumber.setText(profileModel.phoneNumber);
        }
    }
}
