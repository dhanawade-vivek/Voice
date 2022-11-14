package com.example.voiceassistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class CallLog extends AppCompatActivity {
    private ImageButton btn_back;
    private Button call_button;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextToSpeech mTTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        if ((ContextCompat.checkSelfPermission(CallLog.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(CallLog.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(CallLog.this,
                    new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.CALL_PHONE}, 1);
        } else {
            final ArrayList<ExampleItem> exampleList = new ArrayList<>();
            Cursor managerCursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null,  android.provider.CallLog.Calls.LAST_MODIFIED + " DESC");
            int number = managerCursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
            int type = managerCursor.getColumnIndex(android.provider.CallLog.Calls.TYPE);
            while(managerCursor.moveToNext()) {
                String phNumber = managerCursor.getString(number);
                String callType = managerCursor.getString(type);
                int dircode = Integer.parseInt( callType );
                switch( dircode ) {
                    case android.provider.CallLog.Calls.INCOMING_TYPE:
                        exampleList.add(new ExampleItem(R.drawable.ic_baseline_call_received_24,phNumber));
                        break;

                    case android.provider.CallLog.Calls.OUTGOING_TYPE:
                        exampleList.add(new ExampleItem(R.drawable.ic_baseline_call_made_24,phNumber));
                        break;

                    case android.provider.CallLog.Calls.MISSED_TYPE:
                        exampleList.add(new ExampleItem(R.drawable.ic_baseline_call_missed_24,phNumber));
                        break;
                }
            }
            managerCursor.close();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    speak("Call log, touch to know number, long press to call");  //speak after 1000ms
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
                    }
                    else {
                        Log.e("TTS", "Initialization error");
                    }
                }
            });

            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new ExampleAdapter(exampleList);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    String s = exampleList.get(position).getmText().replaceAll(".(?!$)", "$0 ");
                    speak(s);
                }
            });

            mAdapter.setOnItemLongClickListener(new ExampleAdapter.OnItemLongClickListener() {
                @Override
                public void onLongItemClick(int position) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    String hash = exampleList.get(position).getmText();
                    String s = "tel:" + hash;
                    intent.setData(Uri.parse(s));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

        btn_back = (ImageButton) findViewById(R.id.btnback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        call_button = (Button) findViewById(R.id.btn_keypad);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCall();
            }
        });

    }
    public void openHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        ActivityCompat.finishAffinity(CallLog.this);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    public void openCall() {
        Intent intent = new Intent(this, Caller.class);
        startActivity(intent);
//        ActivityCompat.finishAffinity(CallLog.this);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }

    private void speak(String s) {
        float pitch = 1;
        float speed = 1;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
    }

}