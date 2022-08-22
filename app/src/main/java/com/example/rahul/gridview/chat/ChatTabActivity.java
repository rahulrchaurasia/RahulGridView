package com.example.rahul.gridview.chat;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.rahul.gridview.Activity.ProfileActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.PrefManager;
import com.example.rahul.gridview.chat.adapter.ChatPagerAdapter;
import com.example.rahul.gridview.chat.fragment.ChatDisplayFragment;
import com.example.rahul.gridview.chat.fragment.UserListFragment;
import com.example.rahul.gridview.chat.model.User;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatTabActivity extends AppCompatActivity {

    private String CHATS = "Chats";
    private String USERS = "Users";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    PrefManager prefManager;

    DatabaseReference dbreference;
    FirebaseUser firebaseUser;

    CircleImageView img_profile;
    TextView txtUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_tab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0,0,0,0);
        toolbar.setContentInsetsAbsolute(0,0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        initialize();
        setupViewPager(viewPager);


        prefManager = new PrefManager(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();                                           // Initializing FireBase User
        dbreference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());    // Initializing Db with Users as root { root cam be check im Firebase /Database : database Section}
                                                                                                            // child(firebaseUser.getUid() : Show the Specific User Detail
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    txtUser.setText("" + user.getUsername());
                    if (user.getImageUrl().equalsIgnoreCase("default")) {
                        img_profile.setImageResource(R.mipmap.ic_launcher);
                    } else {
                        Glide.with(ChatTabActivity.this).load(user.getImageUrl()).into(img_profile);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void initialize() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        img_profile = findViewById(R.id.img_profile);
        txtUser = findViewById(R.id.txtUser);


        // setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ChatPagerAdapter adapter = new ChatPagerAdapter(ChatTabActivity.this.getSupportFragmentManager());


        adapter.addFrag(new ChatDisplayFragment(), CHATS);
        adapter.addFrag(new UserListFragment(), USERS);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_shetting:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // finish();
        supportFinishAfterTransition();
        super.onBackPressed();
    }

}
