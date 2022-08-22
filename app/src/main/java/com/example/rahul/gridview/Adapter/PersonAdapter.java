package com.example.rahul.gridview.Adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Dog;
import com.example.rahul.gridview.model.Person;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by IN-RB on 18-04-2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonItem> {

    Activity mContext;
    List<Person> PersonList;
    DogAdapter mAdapter;
    private final Realm realm  = Realm.getDefaultInstance();

    public PersonAdapter(Activity context, List<Person> PersonList) {

        this.mContext = context;
        this.PersonList = PersonList;
    }

    public class PersonItem extends RecyclerView.ViewHolder {

        public TextView txtDept, txtName, txtEmail;
        public LinearLayout lvPerson;
        public  RecyclerView rvDog;

        public PersonItem(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDept = (TextView) itemView.findViewById(R.id.txtDept);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            lvPerson = (LinearLayout) itemView.findViewById(R.id.lvPerson);
            rvDog  = (RecyclerView) itemView.findViewById(R.id.rvDog);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
            rvDog.setLayoutManager(layoutManager);
        }
    }

    @Override
    public PersonItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);

        return new PersonAdapter.PersonItem(itemView);
    }

    @Override

    public void onBindViewHolder(final PersonItem holder, int position) {

        final Person person = PersonList.get(position);
        List<Dog> DogList = person.getDogs();

        holder.txtName.setText(person.getName());
        holder.txtDept.setText(person.getDept());
        holder.txtEmail.setText(person.getEmail());


        mAdapter = new DogAdapter(mContext,DogList);
        holder.rvDog.setAdapter(mAdapter);

        holder.lvPerson.setOnLongClickListener(new View.OnLongClickListener() {

            String strPerName =  person.getName();
            @Override
            public boolean onLongClick(View v) {

                try {
                    final RealmResults<Person> results = realm.where(Person.class).equalTo("name",person.getName()).findAll();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {


                            results.deleteAllFromRealm();
                        }
                    });

                }catch (Exception ex)
                {
                    Toast.makeText(mContext, " Error .."+ ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

               notifyItemRemoved(holder.getAdapterPosition());


               Toast.makeText(mContext,  strPerName +" is selected..", Toast.LENGTH_SHORT).show();


                return false;
            }
        });


    }


    @Override
    public int getItemCount() {

        return PersonList.size();
    }

    public void filer(String strSearch)
    {
//        List<Person> lstPersonfilter = realm.where(Person.class)
//                                        .contains("name","rahul").findAll();
        List<Person> lstPersonfilter = realm.where(Person.class).contains("dogs.name",strSearch, Case.INSENSITIVE).findAll();

        PersonList = lstPersonfilter;
        notifyDataSetChanged();
    }

    public void findAll(List<Person> lstPerson)
    {
        PersonList = lstPerson;
        notifyDataSetChanged();
    }
}
