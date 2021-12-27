package com.example.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MediaSound extends AppCompatActivity {

    MediaPlayer player;
    Button play,pause,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_sound);
        play=findViewById(R.id.button3);
        pause=findViewById(R.id.button4);
        stop=findViewById(R.id.button5);
       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(player==null){
                   player=MediaPlayer.create(MediaSound.this,R.raw.two);
              player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                  @Override
                  public void onCompletion(MediaPlayer mediaPlayer) {
                      stopMusic();
                  }
              });
               }
               player.start();
           }
       });
      pause.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(player !=null){
                  player.pause();
              }
          }
      });
      stop.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          stopMusic();
          }
      });
    }
    private  void stopMusic(){
        if(player !=null){
            player.release();
            player=null;
            Toast.makeText(MediaSound.this, "the song released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();

    }
}