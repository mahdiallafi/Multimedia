package com.example.media;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private  int sound1,sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    ////here we check the version
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool=new SoundPool.Builder().setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else
        {
            soundPool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        }
        sound1=soundPool.load(this,R.raw.one,1);
        sound2=soundPool.load(this,R.raw.two,1);

        Button button=(Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MediaSound.class);
                startActivity(intent);
            }
        });
        Button button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, MediaRecord.class);
                startActivity(intent);
            }
        });
    }
    public  void playsound(View v){
       switch (v.getId()){
           case R.id.button:
               soundPool.play(sound1,1,1,0,0,1);
               soundPool.autoPause();

               break;
           case R.id.button2:
               soundPool.play(sound2,1,1,0,0,1);
               break;

       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool=null;
    }
}