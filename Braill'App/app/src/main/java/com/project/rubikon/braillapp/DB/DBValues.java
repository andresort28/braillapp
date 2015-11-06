package com.project.rubikon.braillapp.DB;

/**
 * Created by pregrado on 29/10/2015.
 */
public final class DBValues {

    //DATABASE NAME
    protected static final String DB_NAME = "BraillApp";

    //DATABASE VERSION
    protected static final int DB_VERSION = 1;

    //TABLE NAME
    protected static final String TABLE_NAME = "Ascii_Character";

    //TABLE ATTRIBUTES
    protected static final String ATTRIBUTE_ID = "id";
    protected static final String ATTR_CHARACTER = "character";
    protected static final String ATTR_BRAILLE = "braille";

    //TABLE QUERYS
    protected static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" +
            ATTRIBUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ATTR_CHARACTER + " TEXT NOT NULL,"+
            ATTR_BRAILLE + " TEXT NOT NULL)";

    protected static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
