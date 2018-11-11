package com.example.vadim.vkmessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Friends_Screen extends Activity{

    private TextView txtResult;
    private TextToSpeech mTTS;
    private ImageButton mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends__screen);
        txtResult = (TextView) findViewById(R.id.txtView_Name_User);
        mButtonSpeak = findViewById(R.id.btn_Read);

        speakPage("Страница друзья");

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
    }

    @Override
    protected void onStart(){
        super.onStart();
        speakPage("Страница друзья");
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

    public void speakPage(String text) {

        Float pitch = (float)1;
        Float speed = (float)1;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Back_onClick(View view){
        onBackPressed();
    }

}

/*public class Friends_Screen extends Activity{

    private  ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends__screen);

        listView = (ListView) findViewById(R.id.listView);
        final VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,
                "first_name", "last_name"));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList list = (VKList) response.parsedModel;
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(Friends_Screen.this,
                        android.R.layout.simple_expandable_list_item_1, list);

                listView.setAdapter(arrayAdapter);
            }
        });
    }


}*/




























