package com.example.rahul.gridview.chat;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.Activity.RealmWithJson;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.PrefManager;
import com.example.rahul.gridview.Utility.Utility;
import com.example.rahul.gridview.chat.adapter.MessageAdapter;
import com.example.rahul.gridview.chat.model.Chat;
import com.example.rahul.gridview.chat.model.User;
import com.example.rahul.gridview.model.LeadEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageeActivity extends BaseActivity implements View.OnClickListener  {

    /*************************************************************
     // Note :  Required empty public constructor For FireBase Handling
     // Here we use Chat class Model for message
     **************************************************************/

    DatabaseReference dbreference;
    FirebaseUser firebaseUser;

    CircleImageView img_profile;
    TextView txtUser;
    PrefManager prefManager;
    User user;    // Receiver User


    ImageButton imgbtnSend;
    EditText etComment;

    RecyclerView rvChat;
    LinearLayout lvParent;

    List<Chat> chatList;
    MessageAdapter mAdapter;

    private ActionModeCallback actionModeCallback;       // apply
    private ActionMode actionMode;

    TextWatcher commentTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().trim().length() == 0) {
                imgbtnSend.setImageDrawable(null);

            } else {
                imgbtnSend.setImageDrawable(ContextCompat.getDrawable(MessageeActivity.this, R.drawable.ic_send));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagee);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, 0, 0, 0);
        toolbar.setContentInsetsAbsolute(0, 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");


        init();
        setListener();

        if (getIntent().getParcelableExtra("USER_DATA") != null) {
            user = getIntent().getParcelableExtra("USER_DATA");
            prefManager = new PrefManager(this);
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            dbreference = FirebaseDatabase.getInstance().getReference("Users").child(user.getId());    // Detail of the Other-user which is Selected for the chat
            // child(firebaseUser.userid : Show the Selected  User Detail

            if (user != null) {
                txtUser.setText("" + user.getUsername());
                if (user.getImageUrl().equalsIgnoreCase("default")) {
                    img_profile.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(MessageeActivity.this).load(user.getImageUrl()).into(img_profile);
                }
            }

            // region Pull data from Firebase to the particular user . {instead we transffered the data from prev Page to current}

            dbreference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
//                        txtUser.setText("" + user.getUsername());
//                        if (user.getImageUrl().equalsIgnoreCase("default")) {
//                            img_profile.setImageResource(R.mipmap.ic_launcher);
//                        } else {
//                            Glide.with(MessageeActivity.this).load(user.getImageUrl()).into(img_profile);
//                        }

                        readMessage(firebaseUser.getUid(), user.getId(), user.getImageUrl());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //endregion
        }

    }


    private void init() {

        lvParent = findViewById(R.id.lvParent);
        img_profile = findViewById(R.id.img_profile);
        txtUser = findViewById(R.id.txtUser);

        imgbtnSend = findViewById(R.id.imgbtnSend);
        etComment = findViewById(R.id.etComment);
        // chatEntityList = new ArrayList<ChatEntity>();

        rvChat = (RecyclerView) findViewById(R.id.rvChat);
        rvChat.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MessageeActivity.this);
        rvChat.setLayoutManager(layoutManager);

        actionModeCallback = new ActionModeCallback();
        chatList = new ArrayList<>();
    }

    private void setListener() {
        imgbtnSend.setOnClickListener(this);
        etComment.addTextChangedListener(commentTextWatcher);
        lvParent.setOnClickListener(this);
    }

    private void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();   // here we added Reference ie other Root to particular user

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("isActive", "1");
        hashMap.put("chatTime", getDateTime());
        reference.child("Chats").push().setValue(hashMap);  //  Chats is a new root for sender and receiver
 //DateFormat.getDateTimeInstance().format(new Date())

    }

    private void readMessage(final String myID, final String userID, final String imageUrl) {
        chatList = new ArrayList<>();
        dbreference = FirebaseDatabase.getInstance().getReference("Chats");
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatList.clear();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);

                    if(chat.getIsActive().equals("1")) {
                        if ((chat.getReceiver().equalsIgnoreCase(myID) && chat.getSender().equalsIgnoreCase(userID))
                                || (chat.getReceiver().equalsIgnoreCase(userID) && chat.getSender().equalsIgnoreCase(myID))) {
                            chatList.add(chat);
                        }
                    }

                    mAdapter = new MessageAdapter(MessageeActivity.this, chatList, imageUrl);
                    rvChat.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void deleteMessages()
    {
        //mAdapter.deleteRecord();
        Toast.makeText(this,"Do u wann delte",Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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

    @Override
    public void onClick(View view) {

        Utility.hideKeyBoard(view, MessageeActivity.this);

        if (view.getId() == R.id.imgbtnSend) {
            if (etComment.getText().toString().trim().length() > 0) {


                sendMessage(firebaseUser.getUid(), user.getId(), etComment.getText().toString().trim());
                etComment.setText("");
                // firebaseUser.getUid() : Sender ID
                //     user.getId() : Receiver ID
            }
        }
    }



    public void onChatLongClicked(Chat chat ,boolean selected)
    {
        // Toast.makeText(this,"Pos" + leadEntity.getCustName(), Toast.LENGTH_SHORT).show();
        if(actionMode == null)
        {
            actionMode = startSupportActionMode(actionModeCallback);
        }

       // toggleSelection(chat,selected);
    }


    private void toggleSelection(Chat chat ,boolean selected)
    {
        if(selected)
        {
            chatList.add(chat);
        }else {
            int indexOfSelected = 0;
            for (int i = 0; i < chatList.size(); i++) {

                if (chatList.get(i).getChatTime().equalsIgnoreCase (chat.getChatTime())) {
                    indexOfSelected = i;
                    break;
                }
            }
            chatList.remove(indexOfSelected);

        }

        int count = chatList.size();
        if(count == 0)
        {
            actionMode.finish();
        }else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }

    }

    //region Action Mode CallBack Interface
    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable Parent layout if action mode is enabled
            lvParent.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

           // chatList.clear();
            lvParent.setEnabled(true);
            actionMode = null;
            rvChat.post(new Runnable() {
                @Override
                public void run() {
                   // mAdapter.resetAll();
                   // mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
    //endregion



}
