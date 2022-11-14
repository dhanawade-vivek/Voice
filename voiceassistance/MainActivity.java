package com.example.voiceassistance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button call_button;
    private Button mess_button;
    private Button batt_button;
    private Button notes_button;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ((ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS}, 1);
        } else {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    speak("Welcome to the home page click on 4 corners to know more options");  //speak after 1000ms
                }
            }, 500);


            mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = mTTS.setLanguage(Locale.ENGLISH);

                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "Language not supported");
                        }
                    } else {
                        Log.e("TTS", "Initialization error");
                    }
                }
            });

            call_button = (Button) findViewById(R.id.btncall);
            call_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCall();
                    speak("Caller, type the number and long press for more options");
                }
            });

            mess_button = (Button) findViewById(R.id.btnmess);
            mess_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMess();
                    speak("Message");
                }
            });

            batt_button = (Button) findViewById(R.id.btnbat);
            batt_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    openBatt();
                    speak("Battery");
                }
            });
            batt_button = (Button) findViewById(R.id.btnnot);
            batt_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    openNotes();
                    speak("Notes");
                }
            });

        }
    }

    public void openCall() {
        Intent intent = new Intent(this, Caller.class);
        startActivity(intent);

    }

    public void openMess() {
        Intent intent = new Intent(this, Message.class);
        startActivity(intent);
    }
    public void openBatt() {
        Intent intent = new Intent(this, Battery.class);
        startActivity(intent);
    }
    public void openNotes() {
        Intent intent = new Intent(this, Notes_MainActivity.class);
        startActivity(intent);
    }

    private void speak(String s) {
        float pitch = 1;
        float speed = 1;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}

