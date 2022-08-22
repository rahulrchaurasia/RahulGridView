package com.example.rahul.gridview.Adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Dog;
import com.example.rahul.gridview.model.Person;

import java.util.List;

/**
 * Created by IN-RB on 19-04-2017.
 */

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogItem> {
    Activity mContext;
    List<Dog> DogList;

    public DogAdapter(Activity mContext, List<Dog> dogList) {
        this.mContext = mContext;
        DogList = dogList;
    }

    public class DogItem extends RecyclerView.ViewHolder {

        public TextView txtDog, txtColor, txtAge;

        public DogItem(View itemView) {
            super(itemView);


            txtDog = (TextView) itemView.findViewById(R.id.txtDog);
            txtColor = (TextView) itemView.findViewById(R.id.txtColor);
            txtAge = (TextView) itemView.findViewById(R.id.txtAge);
        }
    }

    @Override
    public DogItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);

        return new DogAdapter.DogItem(itemView);
    }

    @Override
    public void onBindViewHolder(DogItem holder, int position) {

        final  Dog dog = DogList.get(position);

        holder.txtDog.setText(dog.getName());
        holder.txtColor.setText(dog.getColor());
        holder.txtAge.setText(String.valueOf(dog.getAge()));
    }


    @Override
    public int getItemCount() {
        return DogList.size();
    }
}
