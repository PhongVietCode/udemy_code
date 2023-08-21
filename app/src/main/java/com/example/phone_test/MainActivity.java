package com.example.phone_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phone_test.Usage.Person;
import com.example.phone_test.adapter.ContactAdapter;
import com.example.phone_test.db.ContactDAO;
import com.example.phone_test.db.ContactDatabase;
import com.example.phone_test.db.entity.DatabaseHelper;
import com.example.phone_test.db.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;
    private ContactAdapter adapter;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView recyclerView;
//    private DatabaseHelper db;
    // ROOM Database
    private ContactDatabase contactDatabase;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My contact");

        // Data binding
        TextView namePerson = findViewById(R.id.personName);
        TextView emailPerson = findViewById(R.id.personEmail);

        Person person1 = new Person("Jack","jack5@gmail.com");

        namePerson.setText(person1.getName());
        emailPerson.setText(person1.getEmai());
        // - - RecyclerView
        recyclerView = findViewById(R.id.contactList);

        // - - Callback

         RoomDatabase.Callback callback = new RoomDatabase.Callback() {
             @Override
             public void onCreate(@NonNull SupportSQLiteDatabase db) { // implement everytime the database is create
                 super.onCreate(db);

                 // this will be the default contact available when the app is installed
//                 creatContact("Bill Gates", "billcute@hotmail.com");
//                 creatContact("Colgate", "gateDia@hotmail.com");
//                 creatContact("Obeme", "beme@hotmail.com");
                 Log.d("TAG", "onCreate: database");


             }

             @Override
             public void onOpen(@NonNull SupportSQLiteDatabase db) { // whenever database is open
                 super.onOpen(db);
                 Log.d("TAG", "onOpen: database");
             }
         };

//       db = new DatabaseHelper(this);
        // - - Database
        contactDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ContactDatabase.class,
                "ContactDB")
                .addCallback(callback)
                .allowMainThreadQueries()// allow to query the data on the main stream
                .build();
        // display all the contact from the data base
        DisplayAllContactInBackground();

        adapter = new ContactAdapter(this, contacts, MainActivity.this);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // build alert
                editContact(false, null, -1);
            }
        });

    }


    public void editContact(boolean isUpdate, Contact contact, int position) {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.layout_add_contact, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(view);
        TextView title = view.findViewById(R.id.title);
        EditText name = view.findViewById(R.id.newName);
        EditText email = view.findViewById(R.id.newEmail);

        title.setText(!isUpdate ? "Add new contact":"Update contact");
        if(isUpdate && contact != null){
            name.setText(contact.getName());
            email.setText(contact.getEmail());
        }
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton(isUpdate ? "Update" : "Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton(isUpdate ? "Delete" : "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isUpdate){
                    deleteContact(contact, position);
                }
                else{
                    dialog.cancel();
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(MainActivity.this,"Empty name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else alertDialog.dismiss();
                if(isUpdate && contact != null){
                    updateContact(name.getText().toString(), email.getText().toString(), position);
                }
                else{
                    createContact(name.getText().toString(), email.getText().toString());

                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void createContact(String name, String email) {
        long id = contactDatabase.getContactDAO().insertContact(new Contact(name,email,0));
        Contact contact = contactDatabase.getContactDAO().getContact(id);
        if(contact != null){
            contacts.add(0, contact);
            adapter.notifyDataSetChanged();
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateContact(String name, String email, int position) {
        Contact contact = contacts.get(position);

        contact.setName(name);
        contact.setEmail(email);

        contacts.set(position,contact);
        contactDatabase.getContactDAO().updateContact(contact);
        adapter.notifyDataSetChanged()  ;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteContact(Contact contact, int position) {
        contacts.remove(position);
        contactDatabase.getContactDAO().deleteContact(contact);
        adapter.notifyDataSetChanged();
    }
    private void DisplayAllContactInBackground(){
        // 1 - create a new thread in the background
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() { // create new thread
            @Override
            public void run() {
                // 2 - Do background work
                contacts.addAll(contactDatabase.getContactDAO().getContacts());
                // 3 - Executed after the background work had finished
                handler.post(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}