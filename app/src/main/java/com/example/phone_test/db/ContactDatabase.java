package com.example.phone_test.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.phone_test.db.entity.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    // Link the DAO with database
    public abstract ContactDAO getContactDAO();

}
