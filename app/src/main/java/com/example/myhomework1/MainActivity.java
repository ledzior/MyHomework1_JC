package com.example.myhomework1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String SOUND_ID="sound_id";
    public static final String CONTACT_ID="contact_id";
    public static final int BUTTON_REQUEST1=1;
    public static final int BUTTON_REQUEST2=2;
    private int current_contact;
    private int current_sound;
    MediaPlayer mp;
    int assigne []=new int[5];
    public static Uri[] data = new Uri[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button1=(Button)findViewById(R.id.change_contact);
        final Button button2=(Button)findViewById(R.id.change_sound);
        if (current_contact==0) current_sound=0;
        else if (current_contact==1) current_sound=1;
        else if (current_contact==2) current_sound=2;
        assigne[0]=0;
        assigne[1]=1;
        assigne[2]=2;
        assigne[3]=3;
        assigne[4]=4;
        data[0]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.mario);
        data[1]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring01);
        data[2]  =  Uri.parse("android.resource://"  + getPackageName()  +  "/"  + R.raw.ring02);
        data[3]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring03);
        data[4]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring04);
        data[5]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ringd);
        final FloatingActionButton play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mp.isPlaying()) {
                    try {
                        mp.prepare();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start();
                    play.setImageResource(android.R.drawable.ic_media_pause);
                }
                else {
                    mp.stop();
                    play.setImageResource(android.R.drawable.ic_media_play);
                }
            }});

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pickSound = new Intent(getApplicationContext(),change_sound.class);
                pickSound.putExtra(SOUND_ID,current_sound);
                startActivityForResult(pickSound,BUTTON_REQUEST1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pickContact=new Intent(getApplicationContext(),change_contact.class);
                pickContact.putExtra(CONTACT_ID,current_contact);
                startActivityForResult(pickContact,BUTTON_REQUEST2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==RESULT_OK){
            if(requestCode==BUTTON_REQUEST1)
            {
                current_sound=data.getIntExtra(SOUND_ID,0);
                assigne[current_contact]=current_sound;
            }
            else if(requestCode==BUTTON_REQUEST2)
            {
                ImageView im =(ImageView) findViewById(R.id.contact_image);
                Random r =new Random();
                Integer[] images={R.drawable.caterpie, R.drawable.charmander, R.drawable.eevee, R.drawable.pikachu, R.drawable.sandshrew};
                im.setImageResource(images[r.nextInt(images.length)]);
                TextView name = (TextView) findViewById(R.id.contact_name);
                current_contact=data.getIntExtra(CONTACT_ID,0);
                String[] stringArray = getResources().getStringArray(R.array.Contacts);
                name.setText(stringArray[current_contact]);
                current_sound=assigne[current_contact];
            }

        }
        else if(resultCode==RESULT_CANCELED){
            Toast.makeText(getApplicationContext(),getText(R.string.back_message),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mp.release();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(mp.isPlaying()) mp.stop();
        final FloatingActionButton play = findViewById(R.id.play);
        play.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        try
        {
            current_sound=assigne[current_contact];
            String[] stringArray = getResources().getStringArray(R.array.Sounds);
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(this,data[current_sound]);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    final FloatingActionButton play = findViewById(R.id.play);
                    play.setImageResource(android.R.drawable.ic_media_play);
                }
            });
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
