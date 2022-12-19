package com.example.virtual_wardrobe.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtual_wardrobe.R;
import com.example.virtual_wardrobe.model.User;
import com.example.virtual_wardrobe.screens.profile.ProfileType;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    OnClick onClick;

    private final List<User> list;

    public UsersAdapter(List<User> list,OnClick clickInterface) {
        this.list = list;
        this.onClick = clickInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.friend_card, parent, false))
        {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = list.get(position);
        TextView status = holder.itemView.findViewById(R.id.user_status);
        TextView name = holder.itemView.findViewById(R.id.user_name);
        name.setText(user.username);
        status.setText(user.mail);
        holder.itemView.setOnClickListener(view -> onClick.onClickUserCard(user));


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClick{
        void onClickUserCard(User user);
        void onClickAddUser(User user);
    }
}

