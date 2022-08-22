package com.example.rahul.gridview.chat.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.chat.fragment.UserListFragment;
import com.example.rahul.gridview.chat.model.User;

import java.util.List;

/**
 * Created by Rahul on 27/05/2019.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserIteme> {


    Fragment mContext;
    List<User> userList;

    public UserListAdapter(Fragment mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    public class UserIteme extends RecyclerView.ViewHolder {
        public TextView txtUser;
        public ImageView img_profile;
        public RelativeLayout lyParent;

        public UserIteme(View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.txtUser);
            img_profile = itemView.findViewById(R.id.img_profile);
            lyParent = itemView.findViewById(R.id.lyParent);

        }


    }

    @NonNull
    @Override
    public UserListAdapter.UserIteme onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new UserListAdapter.UserIteme(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserIteme holder, int position) {

        final User user = userList.get(position);

        if (user != null) {
            holder.txtUser.setText("" + String.valueOf(user.getUsername()));
            if (user.getImageUrl().equalsIgnoreCase("default")) {
                holder.img_profile.setImageResource(R.mipmap.ic_launcher);
            } else {
                Glide.with(mContext).load(user.getImageUrl()).into(holder.img_profile);
            }

            holder.lyParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((UserListFragment)mContext).redirectTouser( user);
                }
            });
        }

    }


    @Override
    public int getItemCount() {

        return userList.size();
    }
}
