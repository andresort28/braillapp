package com.project.rubikon.braillapp.DB;

/**
 * Created by pregrado on 04/11/2015.
 */
public final class DBAsciiCharacter {

    //TABLE NAME
    protected static final String TABLE_NAME = "Ascii_Character";

    //TABLE ATTRIBUTES
    protected static final String ATTR_CHARACTER = "character";
    protected static final String ATTR_BRAILLE = "braille";


    //TABLE QUERYS
    protected static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" +
            DBValues.ATTRIBUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ATTR_CHARACTER + " TEXT NOT NULL,"+
            ATTR_BRAILLE + " TEXT NOT NULL)";

    protected static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
