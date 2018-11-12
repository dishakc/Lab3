package com.example.apple.dbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHealper mDatabaseHealper;
    private Button add;
    private Button view;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= (EditText) findViewById(R.id.name);
        add= (Button) findViewById(R.id.add);
        view= (Button) findViewById(R.id.view);
        mDatabaseHealper=new DatabaseHealper(this);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newEntry=name.getText().toString();
                if(name.length() !=0){
                    AddData(newEntry);
                    name.setText("");
                }else{
                    toastMessage("Enter Data");
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData=mDatabaseHealper.addData(newEntry);
        if(insertData){
            toastMessage("Data Succesfully Inserted");
        }else{     toastMessage("Something Went Wrong"); }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
