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
 * Created by IN-RB on 12-10-2017.
 */

public class FooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    ArrayList<Generic> generics;
    Context context;


    public FooterAdapter(Context context, ArrayList<Generic> generics) {
        this.context = context;
        this.generics = generics;
    }



    public class FooterViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitleFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            this.txtTitleFooter = (TextView) itemView.findViewById (R.id.txtFooter);
        }
    }

    public class GenericViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        public GenericViewHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById (R.id.txtName);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_FOOTER)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item,parent,false);
            return new FooterViewHolder(view);

        }else if(viewType ==TYPE_ITEM){

            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            return new GenericViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof FooterViewHolder){
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.txtTitleFooter.setText ("Footer");
            footerHolder.txtTitleFooter.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View view) {
                    Toast.makeText (context, "Clicked Footer", Toast.LENGTH_SHORT).show ();
                }
            });
        }else if(holder instanceof GenericViewHolder){
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
            genericViewHolder.txtName.setText (generics.get(position).getName ());
            genericViewHolder.txtName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Clicked item" + (position), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return generics.size() + 1;
    }

    //need to override this method for set diff type in RecyclerView
    @Override
    public int getItemViewType(int position) {

        if(isPositionFooter(position))
        {
            return  TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position)
    {
        return position == generics.size () ;
    }
}
