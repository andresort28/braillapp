package com.project.rubikon.braillapp.DB;

/**
 * Created by pregrado on 04/11/2015.
 */
public final class DBTweet {

    //TABLE NAME
    protected static final String TABLE_NAME = "Tweet";

    //TABLE ATTRIBUTES
    protected static final String ATTR_TWEET = "tweet";

    //TABLE QUERYS
    protected static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" +
            DBValues.ATTRIBUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ATTR_TWEET + " TEXT NOT NULL)";

    protected static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
