package com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeakActivity extends AppCompatActivity {

    EditText et_text;
    SeekBar sb_pitch,sb_speed;
    Button btn_speak;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speak);

        et_text = findViewById(R.id.et_papa_text);
        sb_pitch = findViewById(R.id.sb_pitch);
        sb_speed = findViewById(R.id.sb_speed);
        btn_speak = findViewById(R.id.btn_speak);

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakText();
            }
        });

        textToSpeech = new TextToSpeech(TextToSpeakActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS)
                {
                    int result = textToSpeech.setLanguage(Locale.US);

                    if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA)
                    {
                        Toast.makeText(TextToSpeakActivity.this,
                                "This Language is Not Supported",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        speakText();
                    }
                }
                else
                {
                    Toast.makeText(TextToSpeakActivity.this,
                            "Failed to Initialize",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void speakText() {

        String text = et_text.getText().toString();
        if("".equals(text))
        {
            text = "Please enter your text to speak";
        }

        float pitch =(float) sb_pitch.getProgress()/50;
        textToSpeech.setPitch(pitch);

        float speed =(float) sb_speed.getProgress()/50;
        textToSpeech.setSpeechRate(speed);

        if(sb_pitch.getProgress()<10)
        {
            setProgress(10);

            textToSpeech.setPitch(pitch);
        }


        if(sb_speed.getProgress()<10)
        {
            setProgress(10);

            textToSpeech.setSpeechRate(speed);
        }

        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);


    }
}