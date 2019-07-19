package com.progressio.yourwords;

import android.content.ContentValues;

public class Verbs {
    private String infinitivo;
    private String past_simple;
    private String past_participle;
    private String traduction;


    public Verbs(String infinitivo,String past_simple,String past_participle,String traduction){
        this.infinitivo = infinitivo;
        this.past_simple = past_simple;
        this.past_participle = past_participle;
        this.traduction = traduction;
    }

    public void setInfinitivo(String infinitivo){
        this.infinitivo = infinitivo;
    }

    public void setPastSimple(String past_simple){
        this.past_simple = past_simple;
    }

    public void setPastParticiple(String past_participle){
        this.past_participle = past_participle;
    }

    public void setTraduction(String traduction){
        this.traduction = traduction;
    }

    public String getInfinitivo(){
        return infinitivo;
    }

    public String getPastSimple(){
        return past_simple;
    }

    public String getPastParticiple(){
        return past_participle;
    }

    public String getTraduction(){
        return traduction;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(VerbsContract.VerbEntry.COLUMN_INFINITIVO, infinitivo);
        values.put(VerbsContract.VerbEntry.COLUMN_PAST_SIMPLE, past_simple);
        values.put(VerbsContract.VerbEntry.COLUMN_PAST_PARTICIPLE, past_participle);
        values.put(VerbsContract.VerbEntry.COLUMN_TRADUCTION, traduction);
        return values;
    }
}
