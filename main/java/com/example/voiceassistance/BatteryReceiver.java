package com.example.voiceassistance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;


public class BatteryReceiver extends BroadcastReceiver {
    private TextToSpeech mTTS;

    @Override
    public void onReceive(Context con, Intent intent) {
        TextView statusLabel = ((Battery) con).findViewById(R.id.statusLabel);
        TextView percentageLabel = ((Battery) con).findViewById(R.id.percentageLabel);
        ImageView batteryImage = ((Battery) con).findViewById(R.id.batteryImage);
        RelativeLayout rlayout = ((Battery) con).findViewById(R.id.battery_layout);

        String action = intent.getAction();

        mTTS = new TextToSpeech(con, new TextToSpeech.OnInitListener() {
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

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            // Status
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String message = "";

            switch (status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "Full";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "Charging";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharging";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not charging";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "Unknown";
                    break;
            }
            statusLabel.setText(message);


            // Percentage
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            final int percentage = level * 100 / scale;

            percentageLabel.setText(percentage + "%");


            // Image
            Resources res = con.getResources();

            if (percentage >= 90) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b100));

            } else if (90 > percentage && percentage >= 65) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b75));

            } else if (65 > percentage && percentage >= 40) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b50));

            } else if (40 > percentage && percentage >= 15) {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b25));

            } else {
                batteryImage.setImageDrawable(res.getDrawable(R.drawable.b0));

            }

            final String finalMessage = message;
            rlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speak("status" + finalMessage + "," + percentage + "% battery");
                }
            });

        }
    }

    private void speak(String s) {
        float pitch = 1;
        float speed = 1;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}