package com.example.phone_test.db.entity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phone_test.db.entity.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="My_base";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1 - execute SQL database
        db.execSQL(Contact.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 1 - update table - delete the old table
        db.execSQL("DROP TABLE IF EXISTS "  +Contact.TABLE_NAME);

        // 2 - create another table
        onCreate(db);
    }
    // insert method - input name && email
    // return id
    public long insertContact(String name, String email){
        // 1 -  get table to insert data
        SQLiteDatabase db = this.getWritableDatabase();

        // 2 - create contentValue to store data to table
        ContentValues contentValues = new ContentValues();

        contentValues.put(Contact.COLUMN_NAME, name);
        contentValues.put(Contact.COLUMN_EMAIL, email);

        // 3 - the insert method will return long id
        long id = db.insert(Contact.TABLE_NAME,null,contentValues);
         db.close();
         return id;
    }

     // get Contact from Firebase - input the id
    // return contact object
    public Contact getContact(long id){
        // 1 - Get the database
        SQLiteDatabase db = this.getReadableDatabase();

        // 2 - Create a cursor to find the contact
        @SuppressLint("Recycle")
        Cursor cursor = db.query(Contact.TABLE_NAME, // query in which table
                new String[]{ // get which column
                    Contact.COLUMN_ID,
                    Contact.COLUMN_NAME,
                    Contact.COLUMN_EMAIL
                },
                Contact.COLUMN_ID + "=?", // search by =, "?" will be replaced by the String.valueOf(id)
                new String[]{ // id
                        String.valueOf(id)
                },
                null,null,null,null);
        if(cursor != null) // data found
            cursor.moveToFirst();
        // 3 -  extract data from the cursor by getString,getInt -> then extract by column
        assert cursor != null;
        Contact contact = new Contact(
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID))
        );
        cursor.close();
        return contact;
    }

    // get all the data from the base - input none
    // return array of contact
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts = new ArrayList<>();

        // 1 - Create command line to query the data
        // Meaning: select all from the table order by id descendant
        String selectQuery = "SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY "+
                Contact.COLUMN_ID + " DESC";
        // 2 - query the data base using Selectsql string
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));
                contacts.add(contact);
            }while (cursor.moveToNext());
        }
        db.close();
        return contacts;
    }

    // update contact to the base - input Contact with new information
    // return id
    public int UpdateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        // 1 - create ContentValue to store to the base
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.COLUMN_NAME, contact.getName());
        contentValues.put(Contact.COLUMN_EMAIL, contact.getEmail());

        // 2 - update the row that has the same id as whereArg
        return db.update(Contact.TABLE_NAME, contentValues,Contact.COLUMN_ID + "=?",new String[]{
                String.valueOf(contact.getId())
        });
    }
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        // like the update method
        db.delete(Contact.TABLE_NAME,Contact.COLUMN_ID + " =?",new String[]{
                String.valueOf(contact.getId())
        });
        db.close();
    }
}
