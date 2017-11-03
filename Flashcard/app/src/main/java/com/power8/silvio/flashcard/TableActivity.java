package com.power8.silvio.flashcard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Silvio on 2.11.2017..
 */

public class TableActivity extends AppCompatActivity {

    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        context = this;
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        String[] headerText = {"ID", "ENGLISH", "DEUTSCHE"};
        for(String c : headerText){
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        // Get the data from database and add them to the table
        // Open database for reading

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        // Start the transaction
        db.beginTransaction();

        try{
            String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_DICTIONARY;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0 ){
                while(cursor.moveToNext()){
                    // Read columns data
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String english_word = cursor.getString(cursor.getColumnIndex("english_word"));
                    String deutsch_word = cursor.getString(cursor.getColumnIndex("deutsch_word"));

                    // Data rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText = {id + "", english_word, deutsch_word};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    tableLayout.addView(row);
                }
            }
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
