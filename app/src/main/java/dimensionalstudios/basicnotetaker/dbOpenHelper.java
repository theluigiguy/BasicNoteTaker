package dimensionalstudios.basicnotetaker;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Runs commands related to database creation / upgrade
 * Created by David on 4/8/2017.
 */

public class dbOpenHelper extends SQLiteOpenHelper {

    //Constants for database name and version
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_NOTES = "notes";
    //must be _id
    public static final String NOTE_ID = "_id";
    //value that user sees
    public static final String NOTE_TEXT = "noteText";
    public static final String NOTE_CREATED = "noteCreated";

    public static final String[] ALL_COLUMNS = {NOTE_ID, NOTE_TEXT, NOTE_CREATED};

    //SQL to create table
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NOTES + " (" +
            NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_TEXT + " TEXT, " +
            NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP" + ")";

    public dbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //Called the first time the class is instantiated
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    //When database version changed and user opens app for the first time
    //Rebuilds
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);
        onCreate(db);
    }
}
