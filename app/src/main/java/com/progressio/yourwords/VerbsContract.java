package com.progressio.yourwords;

import android.provider.BaseColumns;

public class VerbsContract {

    private VerbsContract() {}

    public static abstract class VerbEntry implements BaseColumns{
        public static final String TABLE_NAME ="Verbs";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_INFINITIVO = "infinitivo";
        public static final String COLUMN_PAST_SIMPLE = "past_simple";
        public static final String COLUMN_PAST_PARTICIPLE = "past_participle";
        public static final String COLUMN_TRADUCTION = "traduction";
    }
}
