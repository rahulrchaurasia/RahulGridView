package com.example.rahul.gridview.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.model.Book;
import com.example.rahul.gridview.model.Dog;
import com.example.rahul.gridview.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

import static com.example.rahul.gridview.R.id.fab;

public class RealmDemo extends BaseActivity implements View.OnClickListener {



    private  Realm realm;
    TextInputEditText etname,etDept,etEmail,etDogName,etDogColor ,etAge;
    Button btnSubmit;
    private LayoutInflater inflater;
    RealmList<Dog> ListDog ;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        realm  = Realm.getDefaultInstance();

        ListDog = new RealmList<Dog>();  // Initalize

        etname = (TextInputEditText) findViewById(R.id.et_name);
        etDept = (TextInputEditText)findViewById(R.id.et_dept);
        etEmail = (TextInputEditText) findViewById(R.id.et_email);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.fab)
        {
            getDogData();
        }
        else if(v.getId() == R.id.btnSubmit){

            if (TextUtils.isEmpty(etname.getText().toString())) {
                etname.setError("Enter Person Name");
                etname.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etDept.getText().toString())) {
                etDept.setError("Enter Department");
                etDept.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(etEmail.getText().toString())) {
                etEmail.setError("Enter Email");
                etEmail.requestFocus();
                return;
            }
            getPersonData();
        }

    }

    //region notUsed
    public List<Book> getBookList()
    {
        List<Book> bookList = realm.where(Book.class).findAll();

        return bookList;

    }

    public List<Person> getPersonList()
    {

        List<Person> prsonList = realm.where(Person.class).findAll();

        if(prsonList.size() >1) {
            String strName = prsonList.get(0).getName();
            String strdep = prsonList.get(0).getDept();
            String strEm = prsonList.get(0).getEmail();

            RealmList<Dog> dogList = prsonList.get(0).getDogs();

            for (Dog objDog : dogList) {

                String DogName = objDog.getName();
                String DogColo = objDog.getColor();
                int age = objDog.getAge();
            }

        }

        return prsonList;

    }

    //endregion

    public void getDogData()
    {

        inflater = RealmDemo.this.getLayoutInflater();
        View content = inflater.inflate(R.layout.emp_item, null);

        etDogName = (TextInputEditText) content.findViewById(R.id.etDogName);

        etDogColor = (TextInputEditText)content.findViewById(R.id.etDogColor);

        etAge = (TextInputEditText)content.findViewById(R.id.etAge);

        AlertDialog.Builder builder = new AlertDialog.Builder(RealmDemo.this);
        builder.setCancelable(false);
        builder.setView(content)
                .setTitle("Add Dog")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       if(validateDog()) {
                           Dog dog = new Dog();
                           dog.setName(etDogName.getText().toString());
                           if (etAge.getText().toString().trim().length() > 0) {
                               dog.setAge(Integer.valueOf(etAge.getText().toString()));
                           } else {
                               dog.setAge(0);
                           }
                           dog.setColor(etDogColor.getText().toString());
                           ListDog.add(dog);

                       }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getPersonData(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               // realm.deleteAll();
                Person person  = realm.createObject(Person.class);
                person.setName(etname.getText().toString());
                person.setDept(etDept.getText().toString());
                person.setEmail(etEmail.getText().toString());

                for(Dog objDog : ListDog) {
                    person.dogs.add(objDog);
                }

               // realm.copyToRealmOrUpdate(person);      //Since Object Declare inside the Asynctask no need to call realm.copyToRealmOrUpdate(person)

            }
        });


      //region comment
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//
//                Book book  = realm.createObject(Book.class);
//                book.setAuthor(etname.getText().toString());
//                book.setDescription(etDept.getText().toString());
//                book.setTitle(etEmail.getText().toString());
//
//                //realm.copyToRealmOrUpdate(book);
//
//            }
//        });

        //endregion

        Snackbar.make(etname, "Record save successfully..", Snackbar.LENGTH_SHORT).show();

        clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.realm_menu , menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_view:
                setTitle(R.string.add_emp);
                intent = new Intent(RealmDemo.this, RealmDataDisplay.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private boolean validateDog()
    {

        if (TextUtils.isEmpty(etDogName.getText().toString())) {
            etDogName.setError("Enter Dog Name");
            etDogName.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(etDogColor.getText().toString())) {
            etDogColor.setError("Enter Dog Color");
            etDogColor.requestFocus();
            return false;
        }
        else  if (TextUtils.isEmpty(etAge.getText().toString())) {
            etAge.setError("Enter Age");
            etAge.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }
    private void clear()
    {
        etname.setText("");
        etDept.setText("");
        etEmail.setText("");
    }

    TextWatcher EditTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {



                if ((s.length() > 1) ) {
                   // etDogName.setBackgroundResource(getResources().getDrawable(R.drawable.edittextoth));
                    etDogName.setBackgroundResource(R.drawable.edittextoth);
                } else {
                    etDogName.setBackgroundResource(R.drawable.edittext_style);
                }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
