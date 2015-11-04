package com.project.rubikon.braillapp.DB;

/**
 * Created by pregrado on 04/11/2015.
 */
public final class DBTranslation {

    //TABLE NAME
    protected static final String TABLE_NAME = "Translation";

    //TABLE ATTRIBUTES
    protected static final String ATTR_TWEET_ID = "tweet_id";
    protected static final String ATTR_ASCII_CHARACTER_ID = "tweet_id";

    protected static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" +
            ATTR_TWEET_ID +" INTEGER" +
            " FOREIGN KEY ("+ ATTR_TWEET_ID + ") REFERENCES "+DBTweet.TABLE_NAME + "(" + DBValues.ATTRIBUTE_ID + "),"+
            ATTR_ASCII_CHARACTER_ID + " INTEGER," +
            " FOREIGN KEY ("+ ATTR_ASCII_CHARACTER_ID + ") REFERENCES "+DBAsciiCharacter.TABLE_NAME + "(" + DBValues.ATTRIBUTE_ID + "))";

    protected static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
