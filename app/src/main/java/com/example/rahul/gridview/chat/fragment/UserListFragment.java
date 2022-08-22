package com.example.rahul.gridview.chat.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.chat.MessageeActivity;
import com.example.rahul.gridview.chat.adapter.UserListAdapter;
import com.example.rahul.gridview.chat.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment {


    RecyclerView rvUser;
    private Context mContext;
    List<User> userList;
    UserListAdapter mAdapter;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        userList = new ArrayList<>();
        init(view);
        readUserData();
    }

    private void init(View view) {
        rvUser = view.findViewById(R.id.rvUser);

        rvUser.setLayoutManager(new LinearLayoutManager(mContext));
        rvUser.setHasFixedSize(true);

    }

    private void readUserData() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference("Users");    // Users : is Main Root , Hence All Child we can retrieved.
        // child(firebaseUser.getUid() : Show the Specific User Detail
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    assert user != null;
                    assert firebaseUser != null;

                    if (!user.getId().equalsIgnoreCase(firebaseUser.getUid())) {
                        userList.add(user);
                    }
                }

                mAdapter = new UserListAdapter(UserListFragment.this, userList);
                rvUser.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void redirectTouser(User user) {
        Intent intent = new Intent(getActivity(), MessageeActivity.class);
        intent.putExtra("USER_DATA", user);
        startActivity(intent);
    }

}
