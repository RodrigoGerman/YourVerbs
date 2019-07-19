package com.progressio.yourwords;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Serializable;

public class Database extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Verbs";

    private Verbs verbs;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + VerbsContract.VerbEntry.TABLE_NAME + " ("
                + VerbsContract.VerbEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VerbsContract.VerbEntry.COLUMN_INFINITIVO + " TEXT NOT NULL,"
                + VerbsContract.VerbEntry.COLUMN_PAST_SIMPLE + " TEXT NOT NULL,"
                + VerbsContract.VerbEntry.COLUMN_PAST_PARTICIPLE + " TEXT NOT NULL,"
                + VerbsContract.VerbEntry.COLUMN_TRADUCTION + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VerbsContract.VerbEntry.TABLE_NAME);
        onCreate(db);
    }


    public void addVerb(Verbs verbs){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(VerbsContract.VerbEntry.TABLE_NAME, null, verbs.toContentValues());
        sqLiteDatabase.close();
    }


    public boolean searchVerb(Verbs verbs){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor search = sqLiteDatabase.rawQuery("select * from Verbs where infinitivo =" + "'" + verbs.getInfinitivo() + "'",null);
        if(search.getCount() > 0)
            return true;
        return false;
    }


    public boolean equalsVerb(Verbs verbs){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor count = sqLiteDatabase.rawQuery(
                "select * from Verbs " + "where infinitivo =" + "'" + verbs.getInfinitivo() + "'" +
                        " and past_simple =" + "'" + verbs.getPastSimple() + "'" +
                         " and past_participle =" + "'" + verbs.getPastParticiple() + "'" +
                            " and traduction =" + "'" + verbs.getTraduction() + "'" , null);

        if (count.getCount() > 0)
            return true;
        return false;
    }

    public Verbs getVerb(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor fila = sqLiteDatabase.rawQuery(
                "select infinitivo,past_simple,past_participle,traduction from Verbs where id= " + Integer.toString(id), null);

        if (fila.moveToFirst())
            verbs = new Verbs(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3));
        else
            verbs = null;

        sqLiteDatabase.close();
        return verbs;
    }

    public int getSizeBD(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Verbs",null);
        return  c.getCount();
    }

    public void deleteTable(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Delete from Verbs",null);
    }
}


