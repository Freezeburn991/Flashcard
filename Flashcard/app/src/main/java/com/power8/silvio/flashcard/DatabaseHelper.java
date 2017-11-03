package com.power8.silvio.flashcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Silvio on 2.11.2017..
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "dictionarydb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_DICTIONARY = "dictionary";
    public static final String CREATE_TABLE_DICTIONARY= "CREATE TABLE IF NOT EXISTS "+ TABLE_DICTIONARY+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, english_word TEXT NULL, deutsch_word TEXT NULL)";
    public static final String DELETE_TABLE_DICTIONARY="DROP TABLE IF EXISTS " + TABLE_DICTIONARY;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_DICTIONARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_DICTIONARY);
        onCreate(db);
    }

    public void insertData(String english_word, String deutsch_word){
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction
        db.beginTransaction();
        ContentValues values;

        try{
            values = new ContentValues();
            values.put("english_word", english_word);
            values.put("deutsch_word", deutsch_word);
            // Insert row
            long i = db.insert(TABLE_DICTIONARY, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully
            db.setTransactionSuccessful();
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        finally {
            db.endTransaction();
            db.close();
        }
    }
}
