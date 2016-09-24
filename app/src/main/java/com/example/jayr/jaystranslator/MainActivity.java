package com.example.jayr.jaystranslator;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;



public class MainActivity extends Activity implements OnInitListener {

    private TextToSpeech tts;
    private Spinner spinnerDest;
    private Button btnRecord;
    private Button btnDelete;
    private EditText txtText;
    private EditText txtTranslate;

    //Microsoft.Translator App.Name
    private String MSAppName = "Jays-Translator";
    //Microsoft.Translator secret key
    private String MSSecretKey = "m7253TJrMIhl9I0290+pBLjg60HmxmniyRzIaR9YiY8="; //Jay 2M chars per month for free
    //private String SecretKey = ""; //Antje 2M chars per month for free

    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtText = (EditText) findViewById(R.id.txtText);
        txtTranslate = (EditText) findViewById(R.id.txtTranslate);

        spinnerDest = (Spinner) findViewById(R.id.spinnerDest);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDest = ArrayAdapter.createFromResource(this,
                R.array.languageDest, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDest.setAdapter(adapterDest);


        btnRecord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txtText.setText("");
                txtTranslate.setText("");
            }

        });

        tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.btnSpeak)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                speakOut(((EditText) findViewById(R.id.txtTranslate)).getText().toString());
            }
        });

        ((Button) findViewById(R.id.btnTranslate)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isOnline()){
                    class bgStuff extends AsyncTask<Void, Void, Void>{

                        String translatedText = "";
                        String sTextToTranslate = "";
                        @Override
                        protected Void doInBackground(Void... params) {
                            // TODO Auto-generated method stub
                            try {
                                EditText text = ((EditText) findViewById(R.id.txtText));
                                translatedText = translate(text);

                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                translatedText = e.toString();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            // TODO Auto-generated method stub
                            ((TextView) findViewById(R.id.txtTranslate)).setText(translatedText);
                            super.onPostExecute(result);
                        }

                    }

                    new bgStuff().execute();}
                else{

                }
            }
        });
    }

    public String translate(EditText text) throws Exception{

        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId(MSAppName); //your name
        Translate.setClientSecret(MSSecretKey); //your microsoft acount key

        String translatedText = "";
        String sTextToTranslate= text.getText().toString();
        int iLanguage;
        iLanguage = this.spinnerDest.getSelectedItemPosition();

        switch (iLanguage) {
            case 0: //ENGLISH
                translatedText = Translate.execute(sTextToTranslate, Language.ENGLISH);
                break;
            case 1: //GERMAN
                translatedText = Translate.execute(sTextToTranslate, Language.GERMAN);
                break;
            case 2: //SPANISH
                translatedText = Translate.execute(sTextToTranslate, Language.SPANISH);
                break;
            case 3: //ITALIAN
                translatedText = Translate.execute(sTextToTranslate, Language.ITALIAN);
                break;
            case 4: //FRENCH
                translatedText = Translate.execute(sTextToTranslate, Language.FRENCH);
                break;
            case 5: //PORTUGUESE
                translatedText = Translate.execute(sTextToTranslate, Language.PORTUGUESE);
                break;
            case 6: //RUSSIAN
                translatedText = Translate.execute(sTextToTranslate, Language.RUSSIAN);
                break;
            case 7: //GREEK
                translatedText = Translate.execute(sTextToTranslate, Language.GREEK);
                break;
            case 8: //TURKISH
                translatedText = Translate.execute(sTextToTranslate, Language.TURKISH);
                break;
            case 9: //FINNISH
                translatedText = Translate.execute(sTextToTranslate, Language.FINNISH);
                break;
            case 10: //SWEDISH
                translatedText = Translate.execute(sTextToTranslate, Language.SWEDISH);
                break;
            case 11: //SWEDISH
                translatedText = Translate.execute(sTextToTranslate, Language.NORWEGIAN);
                break;
            case 12: //HINDI
                translatedText = Translate.execute(sTextToTranslate, Language.HINDI);
                break;
            case 13: //HEBREW
                translatedText = Translate.execute(sTextToTranslate, Language.HEBREW);
                break;
            case 14: //CHINESE
                translatedText = Translate.execute(sTextToTranslate, Language.CHINESE_SIMPLIFIED);
                break;
            case 15: //JAPANESE
                translatedText = Translate.execute(sTextToTranslate, Language.JAPANESE);
                break;
            case 16: //JAPANESE
                translatedText = Translate.execute(sTextToTranslate, Language.ARABIC);
                break;
            case 17: //THAI
                translatedText = Translate.execute(sTextToTranslate, Language.THAI);
                break;
            case 18: //INDONESIAN
                translatedText = Translate.execute(sTextToTranslate, Language.INDONESIAN);
                break;
            case 19: //KOREAN
                translatedText = Translate.execute(sTextToTranslate, Language.KOREAN);
                break;

            default: //else
                Toast.makeText(getApplicationContext(), "Fehler: Ausgewählte Sprache unbekannt!", Toast.LENGTH_SHORT).show();
                break;
        }

        return translatedText;
    }


    private void speakOut(String text) {
        int iLanguage;
        int iDummy;
        boolean bSpeak = true;

        iLanguage = this.spinnerDest.getSelectedItemPosition();
        switch (iLanguage) {
            case 0: //ENGLISH
                iDummy = tts.setLanguage(Locale.ENGLISH);
                bSpeak = true;
                break;
            case 1: //GERMAN
                iDummy = tts.setLanguage(Locale.GERMAN);
                bSpeak = true;
                break;
            case 2: //SPANISH
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 3: //ITALIAN
                iDummy = tts.setLanguage(Locale.ITALIAN);
                bSpeak = true;
                break;
            case 4: //FRENCH
                iDummy = tts.setLanguage(Locale.FRENCH);
                bSpeak = true;
                break;
            case 5: //PORTUGUESE
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 6: //RUSSIAN
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 7: //GREEK
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 8: //TURKISH
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 9: //FINNISH
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 10: //SWEDISH
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 11: //NORWEGIAN
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 12: //HINDI
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 13: //HEBREW
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 14: //CHINESE
                iDummy = tts.setLanguage(Locale.SIMPLIFIED_CHINESE);
                bSpeak = true;
                break;
            case 15: //JAPANESE
                iDummy = tts.setLanguage(Locale.JAPANESE);
                bSpeak = true;
                break;
            case 16: //ARABIC
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                 break;
            case 17: //THAI
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 18: //INDONESIAN
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
            case 19: //KOREAN
                Toast.makeText(getApplicationContext(), "Sprachausgabe wird für diese Sprache nicht unterstützt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;

            default: //else
                Toast.makeText(getApplicationContext(), "Fehler: Ausgewählte Sprache unbekannt!", Toast.LENGTH_SHORT).show();
                bSpeak = false;
                break;
        }
        if (bSpeak) {
            if (text.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Übersetze erst deinen Text!", Toast.LENGTH_SHORT).show();
            } else {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }


    protected boolean isOnline() {
        // check Internet connection hered
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo!=null && nInfo.isConnected()){
            return true;
        }else{
            Toast.makeText(MainActivity.this, "Du hast keine Internetverbindung. App kann sich nicht mit 'microsoft.translate' verbinden.", Toast.LENGTH_SHORT).show();
        }

        return false;

    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.GERMAN);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Diese Sprache wird nicht unterstützt.");
            }

        } else {
            Log.e("TTS", "Tts Initialisierung fehlgeschlagen!");
        }
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
        }
        super.onPause();
    }


    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    txtText.setText("");
                    txtTranslate.setText("");
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtText.setText(result.get(0));
                }
                break;
            }

        }
    }

}