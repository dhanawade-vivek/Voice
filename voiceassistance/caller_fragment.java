package com.example.voiceassistance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class caller_fragment extends DialogFragment {

    private Button caller_fragment;
    private TextToSpeech mTTS;
    String number;

    public caller_fragment(String nu) {
        number = nu;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.caller_frag, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        caller_fragment = (Button) view.findViewById(R.id.fragbtn);
        mTTS = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
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




        caller_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(number.replaceAll(".(?!$)", "$0 "));
                if (number.length() <= 0 ) {
                    speak("No number typed");
                }
                getActivity().getFragmentManager().popBackStack();
            }
        });

        caller_fragment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                speak("Calling" + number.replaceAll(".(?!$)", "$0 "));
                Intent intent = new Intent(Intent.ACTION_CALL);
                String s = "tel:" + number;
                intent.setData(Uri.parse(s));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return false;
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
