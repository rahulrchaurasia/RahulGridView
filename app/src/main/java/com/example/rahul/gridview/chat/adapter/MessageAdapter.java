package com.example.rahul.gridview.chat.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.Activity.RealmWithJson;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.chat.MessageeActivity;
import com.example.rahul.gridview.chat.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by IN-RB on 22-02-2018.
 */


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ChatItem> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    Activity mContext;
    List<Chat> chatList;
    private String imageUri;
    FirebaseUser firebaseUser;

    public MessageAdapter(Activity mContext, List<Chat> chatList, String imageUri) {
        this.mContext = mContext;
        this.chatList = chatList;
        this.imageUri = imageUri;
    }

    public class ChatItem extends RecyclerView.ViewHolder {
        public TextView txtMessage,txtChatTime;
        LinearLayout lvParent;


        public ChatItem(View itemView) {
            super(itemView);

            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtChatTime  = itemView.findViewById(R.id.txtChatTime);
            lvParent = itemView.findViewById(R.id.lvParent);

        }
    }


    @Override
    public MessageAdapter.ChatItem onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);

            return new MessageAdapter.ChatItem(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);

            return new MessageAdapter.ChatItem(itemView);
        }

    }

    @Override
    public void onBindViewHolder(ChatItem holder, int position) {

        final Chat chat = chatList.get(position);

        holder.txtMessage.setText(chat.getMessage());
      //  holder.txtChatTime.setText(chat.getChatTime());

        holder.txtChatTime.setText (getminuteDate(chat.getChatTime()));

        holder.lvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MessageeActivity) mContext).onChatLongClicked(chat,chat.isSelected());

            }
        });
    }


    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chatList.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {

            return MSG_TYPE_LEFT;
        }
    }

    public String getminuteDate(String tempdate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a", Locale.US);
            Date date = format.parse(tempdate);
            format = new SimpleDateFormat("hh:mm a");   // for 24 hours Use : HH   and For 12 Use hh
            String dateString = format.format(date);
            return dateString;
        } catch (Exception ex) {
            return "";
        }


    }
}
