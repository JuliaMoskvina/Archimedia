package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class DataBase {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Olympiads";
    private static final String TABLE_NAME_2="Materials";

    private static final String OLYMPIADS_ID="Olympiad_ID";
    private static final String MATERIALS_ID="Material_ID";
    private static final String OLYMPIADS_NAME="Olympiad_NAME";
    private static final String MATERIALS_NAME="Material_NAME";
    private static final String ID_FROM_OLYMPIADS="ID_From_Olympiads";
    private static final String SUBJECT_ID="SUBJECT_ID";
    private static final String URL="Url";
    private static final String UNIVERSITY = "University";
    private static final String CONTENT = "Content";
    private static final String DATE="Date";

    private static final int NUM_OLYMPIADS_ID = 0;
    private static final int NUM_OlYMPIADS_NAME = 1;
    private static final int NUM_URL = 2;
    private static final int NUM_UNIVERSITY = 3;
    private static final int NUM_SUBJECT_ID= 4;

    private static final int NUM_MATERIALS_ID = 0;
    private static final int NUM_MATERIALS_NAME = 1;
    private static final int NUM_ID_FROM_OLYMPIADS=2;


    private SQLiteDatabase mDataBase;

    public DataBase (Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }
    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query_olympiads = "CREATE TABLE " + TABLE_NAME + " (" +
                    OLYMPIADS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    OLYMPIADS_NAME+ " STEXT, " +
                    URL + " TEXT, " +
                    UNIVERSITY + " TEXT,"+
                    SUBJECT_ID + "INTEGER,"+
                    DATE+" DATE);";
            String query_materials = "CREATE TABLE " + TABLE_NAME + " (" +
                    MATERIALS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MATERIALS_NAME+ " TEXT, " +
                    ID_FROM_OLYMPIADS +"INTEGER,"+
                    CONTENT+" TEXT);";


            db.execSQL(query_olympiads);
            db.execSQL(query_materials);
        }
        public long insert(String olympiads_name, String university, String url, int subject_id, Date date, String materials_name, int id_from_olympiads, String content) {
            ContentValues cv=new ContentValues();
            cv.put(COLUMN_TEAMHOME, teamhouse);
            cv.put(COLUMN_TEAMGUAST, teamguest);
            cv.put(COLUMN_GOALSHOME, goalshouse);
            cv.put(COLUMN_GOALSGUAST,goalsguest);
            return mDataBase.insert(TABLE_NAME, null, cv);
        }

        public int update(Matches md) {
            ContentValues cv=new ContentValues();
            cv.put(COLUMN_TEAMHOME, md.getTeamhouse());
            cv.put(COLUMN_TEAMGUAST, md.getTeamguest());
            cv.put(COLUMN_GOALSHOME, md.getGoalshouse());
            cv.put(COLUMN_GOALSGUAST,md.getGoalsguest());
            return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
        }

        public void deleteAll() {
            mDataBase.delete(TABLE_NAME, null, null);
        }

        public void delete(long id) {
            mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
        }

        public Matches select(long id) {
            Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

            mCursor.moveToFirst();
            String TeamHome = mCursor.getString(NUM_COLUMN_TEAMHOME);
            String TeamGuest = mCursor.getString(NUM_COLUMN_TEAMGUAST);
            int GoalsHome = mCursor.getInt(NUM_COLUMN_GOALSHOME);
            int GoalsGuest=mCursor.getInt(NUM_COLUMN_GOALSGUEST);
            return new Matches(id, TeamHome, TeamGuest, GoalsHome,GoalsGuest);
        }

        public ArrayList<Matches> selectAll() {
            Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

            ArrayList<Matches> arr = new ArrayList<Matches>();
            mCursor.moveToFirst();
            if (!mCursor.isAfterLast()) {
                do {
                    long id = mCursor.getLong(NUM_COLUMN_ID);
                    String TeamHome = mCursor.getString(NUM_COLUMN_TEAMHOME);
                    String TeamGuest = mCursor.getString(NUM_COLUMN_TEAMGUAST);
                    int GoalsHome = mCursor.getInt(NUM_COLUMN_GOALSHOME);
                    int GoalsGuest=mCursor.getInt(NUM_COLUMN_GOALSGUEST);
                    arr.add(new Matches(id, TeamHome, TeamGuest, GoalsHome,GoalsGuest));
                } while (mCursor.moveToNext());
            }
            return arr;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME_2);
            onCreate(db);
        }
    }

}

