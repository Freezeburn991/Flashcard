package com.power8.silvio.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Silvio on 2.11.2017..
 */

public class AddFlashCardActivity extends AppCompatActivity {

    private Button showAllButton ;
    private Context context;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flashcard);
        context = this;
        // Create DatabaseHelper instance for insert
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // Insert the data
        databaseHelper.insertData("Word", "Wort");
        databaseHelper.insertData("test", "test");

        onClickButtonListener();
    }

    public void onClickButtonListener(){
        showAllButton = (Button) findViewById(R.id.showAll);
        showAllButton.setOnClickListener(new View.OnClickListener()
                {
                        public void onClick(View v){
                            Intent intent = new Intent(AddFlashCardActivity.this, TableActivity.class);
                            startActivity(intent);

        }
                }
        );
    }
}
