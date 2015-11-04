package com.project.rubikon.braillapp.DB;

/**
 * Created by pregrado on 29/10/2015.
 */
public final class DBValues {

    //DATABASE NAME
    protected static final String DB_NAME = "BraillApp";

    //DATABASE VERSION
    protected static final int DB_VERSION = 1;

    //GLOBAL ATTRIBUTES
    protected static final String ATTRIBUTE_ID = "id";

    //TABLE TWEET
    protected static final String TABLE_TWEET = "Tweet";
    protected static final String TWEET_ATTRIBUTE_TWEET = "tweet";

    //TABLE TRANSLATION
    protected static final String TABLE_TRANSLATION = "Translation";
    protected static final String TRANSLATION_ATTRIBUTE_TWEET_ID = "tweet_id";
    protected static final String TRANSLATION_ATTRIBUTE_TRADUCCION_BRAILLE = "traduccion_braille";

    protected static final String TABLE_ASCII_CHARACTER = "Ascii_Character";

    protected static final String CREATE_TABLE_TWEET = "CREATE TABLE "+TABLE_TWEET + "(" + ATTRIBUTE_ID + " INTEGER PRIMARY KEY," +TWEET_ATTRIBUTE_TWEET+ " TEXT)";
    protected static final String CREATE_TABLE_TRANSLATION = "CREATE TABLE "+TABLE_TRANSLATION + "(" + TRANSLATION_ATTRIBUTE_TRADUCCION_BRAILLE+ " TEXT," + TRANSLATION_ATTRIBUTE_TWEET_ID +")";

}
