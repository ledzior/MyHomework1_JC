package com.example.myhomework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class change_sound extends AppCompatActivity {
    int cname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sound);
        setSpinnerItemSelectedListener();
    }


    public void setSpinnerItemSelectedListener(){
        Spinner contacts = (Spinner) findViewById(R.id.spinner);
        contacts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cname = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });

    }


    public void setSoundClick(View view3) {
        Button button = (Button) findViewById(R.id.button4);
        Intent data = new Intent();
        button.getId();
        data.putExtra(MainActivity.SOUND_ID, cname);
        setResult(RESULT_OK, data);
        finish();
    }
}
