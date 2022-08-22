package com.example.rahul.gridview.RecyclerHeadFoot;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Generic;

import java.util.ArrayList;

/**
 * Created by IN-RB on 23-10-2017.
 */

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    ArrayList<Generic> generics;
    Context context;

    public HeaderAdapter(Context context, ArrayList<Generic> generics) {
        this.context = context;
        this.generics = generics;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHeader;

        public HeaderViewHolder (View itemView) {
            super (itemView);
            this.txtTitleHeader = (TextView) itemView.findViewById (R.id.txtHeader);
        }
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public GenericViewHolder (View itemView) {
            super (itemView);
            this.txtName = (TextView) itemView.findViewById (R.id.txtName);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.header_item, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.list_item, parent, false);
            return new GenericViewHolder (v);
        }
        return null;
    }

    private Generic getItem (int position) {
        return generics.get (position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.txtTitleHeader.setText ("Header");
            headerHolder.txtTitleHeader.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View view) {
                    Toast.makeText (context, "Clicked Header", Toast.LENGTH_SHORT).show ();
                }
            });
        } else if(holder instanceof GenericViewHolder) {
            Generic currentItem = getItem (position - 1);
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            genericViewHolder.txtName.setText (currentItem.getName ());
            genericViewHolder.txtName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Clicked item" + (position-1), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return generics.size () + 1;
    }

    private boolean isPositionHeader (int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader (position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }



}
