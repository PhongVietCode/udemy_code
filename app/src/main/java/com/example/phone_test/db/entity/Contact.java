package com.example.phone_test.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
    // 1 - Table constant for no Room database
    public static final String TABLE_NAME = "my_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME= "NAME";
    public static final String COLUMN_EMAIL ="EMAIL";

    // 2 - Class Variables
    @ColumnInfo(name = "contact_name")
    private String name;
    @ColumnInfo(name = "contact_email")
    private String email;
    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true) // auto increase id by 1
    private int id;

    // 3- Constructor
    @Ignore
    public Contact(){

    }
    public Contact(String name, String email,  int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // SQLite query
   public static final String CREATE_TABLE =
           "CREATE TABLE "+ TABLE_NAME + "("
                   + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + COLUMN_NAME + " TEXT, "
                    + COLUMN_EMAIL + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                   +")";
}
