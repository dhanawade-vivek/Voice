package com.example.voiceassistance;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Locale;

public class Caller extends AppCompatActivity {
    private ImageButton btn_back;
    private Button call_log_button;
    private TextToSpeech mTTS;
    LinearLayout linearLayout;


    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnStar,btnHash;
    public EditText ed1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);

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

        btn_back = (ImageButton) findViewById(R.id.btnback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        btn_back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.liner_caller);
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });



        call_log_button = (Button) findViewById(R.id.btn_call_log);
        call_log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCallLog();
            }
        });

        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        btn3=(Button) findViewById(R.id.btn3);
        btn4=(Button) findViewById(R.id.btn4);
        btn5=(Button) findViewById(R.id.btn5);
        btn6=(Button) findViewById(R.id.btn6);
        btn7=(Button) findViewById(R.id.btn7);
        btn8=(Button) findViewById(R.id.btn8);
        btn9=(Button) findViewById(R.id.btn9);
        btnStar=(Button) findViewById(R.id.btnStar);
        btnHash=(Button) findViewById(R.id.btnHash);
        btn0=(Button) findViewById(R.id.btn0);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"1");
                speak("1");
            }
        });
        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"2");
                speak("2");
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"3");
                speak("3");
            }
        });
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"4");
                speak("4");
            }
        });
        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"5");
                speak("5");
            }
        });
        btn5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"6");
                speak("6");
            }
        });
        btn6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"7");
                speak("7");
            }
        });
        btn7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"8");
                speak("8");
            }
        });
        btn8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"9");
                speak("9");
            }
        });
        btn9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"0");
                speak("0");
            }
        });
        btn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"*");
                speak("*");
            }
        });
        btnStar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
        btnHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText()+"#");
                speak("#");
            }
        });
        btnHash.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                caller_fragment caller_fragment = new caller_fragment(ed1.getText().toString());
                caller_fragment.show(getSupportFragmentManager(),"My Fragment");
                speak("Tap once to speak the number," +
                        " Long press to call,");
                return true;
            }
        });
    }

    public void openHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        ActivityCompat.finishAffinity(Caller.this);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void OpenCallLog() {
        Intent intent = new Intent(this,CallLog.class);
        startActivity(intent);
//        ActivityCompat.finishAffinity(Caller.this);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


    private void speak(String s) {
        float pitch = 1;
        float speed = 1;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
    }

    public void clear_edit_text() {
        ed1.setText(" ");
    }

}