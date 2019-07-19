package com.progressio.yourwords;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NewWord extends AppCompatActivity {

    private String infinitivo,simple_past,participle_past,traduction;

    private Database db;

    private Verbs verbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        db = new Database(this);
    }

    public void setClear(View v){
        ((EditText) findViewById(R.id.infinitivo)).setText("");
        ((EditText) findViewById(R.id.simple_past)).setText("");
        ((EditText) findViewById(R.id.participle_past)).setText("");
        ((EditText) findViewById(R.id.traduction)).setText("");
    }

    public void newVerb(View v){
        infinitivo = ((EditText)findViewById(R.id.infinitivo)).getText().toString();
        simple_past = ((EditText)findViewById(R.id.simple_past)).getText().toString();
        participle_past = ((EditText)findViewById(R.id.participle_past)).getText().toString();
        traduction = ((EditText)findViewById(R.id.traduction)).getText().toString();

        if( infinitivo.matches("") || simple_past.matches("") || participle_past.matches("") || traduction.matches("")){
            Toast t = Toast.makeText(this, "No puede introducir valores nulos.", Toast.LENGTH_SHORT);
            t.show();
        }
        else {

            verbs = new Verbs(infinitivo, simple_past, participle_past, traduction);

            if (db.searchVerb(verbs)) {
                Toast t = Toast.makeText(this, "El verbo ya está registrado.", Toast.LENGTH_SHORT);
                t.show();

            } else {
                db.addVerb(verbs);
                Toast t = Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_SHORT);
                t.show();
            }
        }

        setClear(v);
    }
}
