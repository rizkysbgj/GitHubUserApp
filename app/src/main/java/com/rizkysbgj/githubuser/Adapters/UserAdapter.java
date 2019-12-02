package com.rizkysbgj.githubuser.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rizkysbgj.githubuser.Classes.User;
import com.rizkysbgj.githubuser.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ListViewHolder> {
    private ArrayList<User> userList;
    private Context context;

    private boolean isLoadingAdded = false;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_user,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ListViewHolder holder, int position) {
        User user = userList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar_url())
                .apply(new RequestOptions())
                .into(holder.imgPhoto);

        holder.tvName.setText(user.getLogin());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public void add(User r) {
        userList.add(r);
        notifyItemInserted(userList.size() - 1);
    }

    public void addAll(ArrayList<User> userList) {
        for (User result : userList) {
            add(result);
        }
    }

    private User getItem(int position) {
        return userList.get(position);
    }
}
