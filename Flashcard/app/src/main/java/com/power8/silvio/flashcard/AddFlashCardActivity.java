package com.power8.silvio.flashcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Silvio on 2.11.2017..
 */

public class AddFlashCardActivity extends AppCompatActivity {

    private Button showAllButton ;
    private Button addToDatabaseButton;
    private EditText englishText;
    private EditText deutschText;
    private Context context;
    private DatabaseHelper databaseHelper;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flashcard);
        context = this;
        // Create DatabaseHelper instance for insert
        databaseHelper = new DatabaseHelper(context);

        //databaseHelper.insertData("test", "test");

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
        addToDatabaseButton = (Button) findViewById(R.id.addToDatabase);
        addToDatabaseButton.setOnClickListener(new View.OnClickListener()
                                                {
                                                    public void onClick(View v){
                                                        // Insert the data
                                                        englishText = (EditText) findViewById(R.id.englishText);
                                                        deutschText = (EditText) findViewById(R.id.deutschText);
                                                        String englishTextString = englishText.getText().toString();
                                                        String deutschTextString = deutschText.getText().toString();
                                                        if((englishTextString.length() != 0) && (deutschTextString.length() != 0)) {
                                                           boolean insertData = databaseHelper.insertData(englishTextString, deutschTextString);
                                                            if(insertData){
                                                                toastMessage("Data successfully inserted");
                                                            }
                                                        }
                                                        else{
                                                            toastMessage("Something went wrong.");

                                                        }

                                                    }
                                                }
        );
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
