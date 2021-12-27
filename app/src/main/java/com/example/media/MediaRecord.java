package com.example.media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MediaRecord extends AppCompatActivity {

    Button record,stoprecord,playrecord;
    MediaRecorder mediaRecord;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
    record=findViewById(R.id.button7);
    stoprecord=findViewById(R.id.button8);
    playrecord=findViewById(R.id.button9);
    ///here we check for the record
        if(MicrophoneOn()){
            Microphonepermission();
            System.out.println("here"+MicrophoneOn());
        }
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaRecord=new MediaRecorder();
                   mediaRecord.setAudioSource(MediaRecorder.AudioSource.MIC);
                   mediaRecord.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                   mediaRecord.setOutputFile(getRecordFile());
                   mediaRecord.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                   mediaRecord.prepare();
                   mediaRecord.start();
                    Toast.makeText(MediaRecord.this, "Recording is start", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MediaRecord.this, "Recording cant", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stoprecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecord.stop();
               mediaRecord.reset();
                mediaRecord.release();
                mediaRecord=null;
                Toast.makeText(MediaRecord.this, "Recording is stop", Toast.LENGTH_SHORT).show();
            }
        });
        playrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                mediaPlayer=new MediaPlayer();
                mediaPlayer.setDataSource(getRecordFile());
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(MediaRecord.this, "Recording is play", Toast.LENGTH_SHORT).show();
            }catch(IllegalStateException| IOException e){
                e.printStackTrace();
                Toast.makeText(MediaRecord.this, "play is not play", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean MicrophoneOn(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }else
        {
            return false;
        }
    }
    private void Microphonepermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.RECORD_AUDIO
            },0);
        }
    }
    private String getRecordFile(){
        ContextWrapper contextWrapper=new ContextWrapper(getApplicationContext());
        File musicDirectory=contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file=new File(musicDirectory,"testRecord.mp3");
        return file.getPath();
    }
}