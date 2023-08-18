package com.example.appwearos;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        MaterialButton mybtn  =findViewById(R.id.recordBTN);
        TextView mytxt = findViewById(R.id.titelTXT);
//        mybtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mytxt.setText("Hello Phong");
//            }
//        });
        if(SpeechRecognizer.isRecognitionAvailable(this)) {
            Log.d("Speech", "onCreate: done");
        }
        else{
            Log.d("Install", "onCreate: don't have");
        }
        SpeechRecognizer speechRecognizer = SpeechRecognizer.
                createSpeechRecognizer(this, ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"));
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                Log.d("Speech", "onReadyForSpeech: done");
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onError(int i) {
                if(i == SpeechRecognizer.ERROR_NETWORK){
                    mytxt.setText("Network error");
                }
                else if(i == SpeechRecognizer.ERROR_NO_MATCH || i == SpeechRecognizer.ERROR_SPEECH_TIMEOUT){
                    mytxt.setText("Didn't hear that");
                }
                else if(i == SpeechRecognizer.ERROR_AUDIO){
                    mytxt.setText("Audio error");
                }
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                assert data != null;
                String result = data.get(0);
                mybtn.setText(result);
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                assert data != null;
                String result = data.get(0);
                mybtn.setText(result);
            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en_US");
        //------------_----------
        mybtn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        speechRecognizer.startListening(intent);
                        mytxt.setText("Listening...");
                    break;
                    case MotionEvent.ACTION_UP:
                        speechRecognizer.stopListening();
                        mytxt.setText("Just a moment ^^");
                        break;
                }
                return true;
            }
        });

    }

    private void checkPermissions() {
        if(checkSelfPermission(Utils.AUDIO_PERMISSION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Utils.AUDIO_PERMISSION},Utils.AUDIO_REQUESTCODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Utils.AUDIO_REQUESTCODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Granted permission",Toast.LENGTH_SHORT).show();
            }
        }
    }
}