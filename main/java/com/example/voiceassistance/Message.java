package com.example.voiceassistance;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;

public class Message extends AppCompatActivity {


    Context context;
    //CardView cardview;
    LayoutParams layoutparams;
    LayoutParams layoutparams2;
    TextView textview;
    LinearLayout relativeLayout;
    ImageView imageview1;

    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

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

        context = getApplicationContext();

        relativeLayout = (LinearLayout) findViewById(R.id.linearlayout1);

        Cursor cursor = getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI,new String[] { Telephony.Sms.Inbox.BODY },null, null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);

        int totalSMS = cursor.getCount();
        String msgData[] = new String[totalSMS];
        if (cursor.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                msgData[i]=cursor.getString(0) +"\n";
                cursor.moveToNext();
            }
        } else {
            //throw new RuntimeException("You have no SMS in Inbox");
            Toast.makeText(getApplicationContext(), "You have no SMS in Inbox", Toast.LENGTH_SHORT).show();
            speak("You have no SMS in Inbox");
            finish();
        }
        //txtView.setText(msgData);
        for (int i = 0; i < totalSMS; i++) {
            CreateCardViewProgrammatically(msgData[i]);
        }

    }

    public void CreateCardViewProgrammatically(final String data){

        final CardView cardview = new CardView(context);

        layoutparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        layoutparams.setMargins(0,20,70,20);

        cardview.setLayoutParams(layoutparams);

        cardview.setRadius(15);

        cardview.setPadding(25, 25, 25, 25);
        cardview.setCardBackgroundColor(getResources().getColor(R.color.white));

        cardview.setMaxCardElevation(30);

        cardview.setMaxCardElevation(6);

        textview = new TextView(context);

        textview.setLayoutParams(layoutparams);

        textview.setText(data);

        textview.setMaxEms(20);

        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);

        textview.setTextColor(Color.BLACK);

        textview.setPadding(15,20,20,20);

        textview.setGravity(Gravity.LEFT);

        layoutparams2 = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT

        );

        layoutparams2.setMargins(620,10,0,10);

        imageview1 = new ImageView(context);

        imageview1.setImageResource(R.drawable.del2);

        imageview1.setLayoutParams(layoutparams2);

        imageview1.setPadding(25,0,0,0);

        cardview.addView(textview);

        cardview.addView(imageview1);

        relativeLayout.addView(cardview);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String toSpeak = data;
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                speak(data);
            }
        });

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String toSpeak = data;
                relativeLayout.removeView(cardview);
                mTTS.stop();
                //t1.shutdown();
            }
        });
    }

    private void speak(String s) {
        float pitch = 1;
        float speed = 1;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
    }

}