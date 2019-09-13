package com.example.myhomework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class change_contact extends AppCompatActivity {
    int cname;
    private int selected_contact=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact);
        Intent recived_intent=getIntent();
        Integer contact_id = recived_intent.getIntExtra(MainActivity.CONTACT_ID,0);

    }
    public void onRadioButtonClicked(View view){
        boolean checked=((RadioButton)view).isChecked();
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        if(checked){
            switch (view.getId()){
                case R.id.contact1:
                    selected_contact = 0;
                    break;
                case R.id.contact2:
                    selected_contact = 1;
                    break;
                case R.id.contact3:
                    selected_contact = 2;
                    break;
                case R.id.contact4:
                    selected_contact = 3;
                    break;
                case R.id.contact5:
                    selected_contact = 4;
                    break;
            }
        }
    }



    public void setContactClick(View view2){
        Button button=(Button)findViewById(R.id.button2);
        Intent data=new Intent();
        button.getId();
        data.putExtra(MainActivity.CONTACT_ID, selected_contact);
        setResult(RESULT_OK,data);
        finish();
    }
}
