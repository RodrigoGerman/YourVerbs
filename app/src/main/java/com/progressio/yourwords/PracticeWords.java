package com.progressio.yourwords;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import android.view.View;
import android.widget.Toast;

public class PracticeWords extends AppCompatActivity {

    private Database db;

    private Verbs verbs;

    private int aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_words);
        db = new Database(this);
    }

    public void setClear(View v){

        setColor(Color.GRAY);

        switch (aux){
            case 1:
                ((EditText) findViewById(R.id.simple_past)).setText("");
                ((EditText) findViewById(R.id.participle_past)).setText("");
                ((EditText) findViewById(R.id.traduction)).setText("");
                break;
            case 2:
                ((EditText) findViewById(R.id.infinitivo)).setText("");
                ((EditText) findViewById(R.id.participle_past)).setText("");
                ((EditText) findViewById(R.id.traduction)).setText("");
                break;
            case 3:
                ((EditText) findViewById(R.id.infinitivo)).setText("");
                ((EditText) findViewById(R.id.simple_past)).setText("");
                ((EditText) findViewById(R.id.traduction)).setText("");
                break;
            case 4:
                ((EditText) findViewById(R.id.infinitivo)).setText("");
                ((EditText) findViewById(R.id.simple_past)).setText("");
                ((EditText) findViewById(R.id.participle_past)).setText("");
                break;
            default:
                ((EditText) findViewById(R.id.infinitivo)).setText("");
                ((EditText) findViewById(R.id.simple_past)).setText("");
                ((EditText) findViewById(R.id.participle_past)).setText("");
                ((EditText) findViewById(R.id.traduction)).setText("");
        }
    }

    public void practiceNewVerb(View v){

        setColor(Color.GRAY);

        aux = -1;
        setClear(v);

        int id = (int)(Math.random() * db.getSizeBD()) + 1;

        verbs = db.getVerb(id);

        if(verbs != null) {

            aux = (int)(Math.random() * 4) + 1;

            switch (aux){
                case 1:
                    ((EditText) findViewById(R.id.infinitivo)).setText(verbs.getInfinitivo());
                    break;
                case 2:
                    ((EditText) findViewById(R.id.simple_past)).setText(verbs.getPastSimple());
                    break;
                case 3:
                    ((EditText) findViewById(R.id.participle_past)).setText(verbs.getPastParticiple());
                    break;
                case 4:
                    ((EditText) findViewById(R.id.traduction)).setText(verbs.getTraduction());
                    break;
                default:
                    setClear(v);
            }
        }
        else {
            Toast t = Toast.makeText(this, "No existen datos", Toast.LENGTH_SHORT);
            t.show();
        }
    }

    private void setColor(int color){
        ((EditText) findViewById(R.id.infinitivo)).setTextColor(color);
        ((EditText) findViewById(R.id.simple_past)).setTextColor(color);
        ((EditText) findViewById(R.id.participle_past)).setTextColor(color);
        ((EditText) findViewById(R.id.traduction)).setTextColor(color);
    }

    public void checkVerb(View v){

        String infinitivo,simple_past,participle_past,traduction;

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

            if (db.equalsVerb(verbs)) {
                Toast t = Toast.makeText(this, "El verbo es correcto.", Toast.LENGTH_SHORT);
                t.show();
                setColor(Color.BLUE);

            } else {
                Toast t = Toast.makeText(this, "El verbo esta incorrecto", Toast.LENGTH_SHORT);
                t.show();

                setColor(Color.RED);

                switch (aux){
                    case 1:
                        ((EditText) findViewById(R.id.infinitivo)).setTextColor(Color.BLUE);
                        break;
                    case 2:
                        ((EditText) findViewById(R.id.simple_past)).setTextColor(Color.BLUE);
                        break;
                    case 3:
                        ((EditText) findViewById(R.id.participle_past)).setTextColor(Color.BLUE);
                        break;
                    case 4:
                        ((EditText) findViewById(R.id.traduction)).setTextColor(Color.BLUE);
                        break;
                    default:
                        setColor(Color.RED);
                        break;
                }
            }
        }
    }
}
