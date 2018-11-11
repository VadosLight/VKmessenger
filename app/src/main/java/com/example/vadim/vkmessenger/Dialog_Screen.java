package com.example.vadim.vkmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.vk.sdk.api.*;

import java.util.ArrayList;
import java.util.Locale;

public class Dialog_Screen extends AppCompatActivity {

    private TextView txtResult;
    private TextToSpeech mTTS;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog__screen);
        txtResult = (TextView) findViewById(R.id.txtView_msg);
        mButtonSpeak = findViewById(R.id.btn_Read_msg);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale("ru");
                    int result = mTTS.setLanguage(locale);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");

                }
            }
        });

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        speakPage();
    }

    @Override
    protected void onStart(){
        super.onStart();
        speakPage();
    }

    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);//ВЫбираем язык, который будет восприниматься

        if (intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent, 10);
        }
        else {
            Toast.makeText(this, "Your device don`t support this function", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if( data!=null){
                    ArrayList<String> result =  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtResult.setText(result.get(0));
                }
                break;
        }
    }

    private void speak() {
        String text = txtResult.getText().toString();
        Float pitch = (float)1;
        Float speed = (float)1;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speakPage() {
        String text = "Диалог";
        Float pitch = (float)1;
        Float speed = (float)1;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Back_onClick(View view){
        onBackPressed();
    }

    public void txtView_msg_onClick(View view){
        txtResult = (TextView) findViewById(R.id.txtView_msg);
        String msg = (String) txtResult.getText();


        VKRequest request = new VKRequest("messages.send", VKParameters.from(VKApiConst.USER_ID, 7596910,
                VKApiConst.MESSAGE, msg));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
    }
}