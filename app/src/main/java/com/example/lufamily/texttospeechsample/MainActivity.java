package com.example.lufamily.texttospeechsample;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    TextToSpeech tts;
    int result;
    EditText et;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editTextMessage);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if(status == TextToSpeech.SUCCESS)
                    result = tts.setLanguage(Locale.ENGLISH);    //SET LANGUAGE

            }
        });

    }

    public void onClick(View v)     //BUTTON
    {
        switch (v.getId())
        {
            case R.id.buttonSpeak:
                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA)  //WILL NOT EMITT SOUND IF THERE"S AN ERROR
                {
                    Toast.makeText(getApplicationContext(),"Feauture is not supported", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    text = et.getText().toString();     //GET STRING TO SPEAK
                    tts.speak(text ,TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
