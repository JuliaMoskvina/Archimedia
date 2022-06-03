package ru.myitschool.vsu.g5.moskvina_y_m.archimedia.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;


public class DataBase {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Olympiads";
    private static final String TABLE_NAME_2="Materials";

    private static final String OLYMPIADS_ID="olympiad_id";
    private static final String MATERIALS_ID="material_id";
    private static final String OLYMPIADS_NAME="olympiad_name";
    private static final String MATERIALS_NAME="material_name";
    private static final String ID_FROM_OLYMPIADS="id_from_olympiads";
    private static final String U_NAME="u_name";
    private static final String SUBJECT_ID="subject_id";
    private static final String URL="url";
    private static final String UNIVERSITY = "university";
    private static final String CONTENT = "content";
    private static final String DATE="date";

    private static final int NUM_OLYMPIADS_ID = 0;
    private static final int NUM_OlYMPIADS_NAME = 1;
    private static final int NUM_URL = 2;
    private static final int NUM_UNIVERSITY = 3;
    private static final int NUM_SUBJECT_ID= 4;
    private static final int NUM_DATE = 5;

    private static final int NUM_MATERIALS_ID = 0;
    private static final int NUM_MATERIALS_NAME = 1;
    private static final int NUM_ID_FROM_OLYMPIADS=2;
    private static final int NUM_CONTENT = 3;
    private static final int NUM_U_NAME=4;


    private SQLiteDatabase mDataBase;

    public DataBase (Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    //Insert
    public long insert_olympiad(String olympiads_name, String university, String url, int subject_id, long date) {
        ContentValues cv=new ContentValues();
        cv.put(OLYMPIADS_NAME, olympiads_name);
        cv.put(UNIVERSITY, university);
        cv.put(URL, url);
        cv.put(SUBJECT_ID,subject_id);
        cv.put(DATE, date);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public long insert_material(String materials_name, String content, long id_from_olympiads, String u_name) {
        ContentValues cv=new ContentValues();
        cv.put(MATERIALS_NAME, materials_name);
        cv.put(CONTENT, content);
        cv.put(ID_FROM_OLYMPIADS,id_from_olympiads);
        cv.put(U_NAME, u_name);
        return mDataBase.insert(TABLE_NAME_2, null, cv);
    }

    // Update


    public int updateOlympiads(OlympiadsDB odb) {
        ContentValues cv=new ContentValues();
        cv.put(OLYMPIADS_NAME, odb.getName());
        cv.put(URL, odb.getUrl());
        cv.put(UNIVERSITY, odb.getUniversity());
        cv.put(DATE,odb.getDate());
        cv.put(SUBJECT_ID, odb.getSubject_id());
        return mDataBase.update(TABLE_NAME, cv, OLYMPIADS_ID + " = ?",new String[] { String.valueOf(odb.getId())});
    }

    public int updateMaterials(MaterialsDB mdb) {
        ContentValues cv=new ContentValues();
        cv.put(MATERIALS_NAME, mdb.getName());
        cv.put(CONTENT, mdb.getContent());
        cv.put(U_NAME, mdb.getU_name());
        return mDataBase.update(TABLE_NAME_2, cv, MATERIALS_ID + " = ?",new String[] { String.valueOf(mdb.getId())});
    }

    //DeleteAll

    public void deleteAllOlympiads() {
        mDataBase.delete(TABLE_NAME, null, null);
    }
    public void deleteAllMaterials(){
        mDataBase.delete(TABLE_NAME_2, null, null);
    }

    //Delete
    public void deleteOlympiads(long id) {
        mDataBase.delete(TABLE_NAME, OLYMPIADS_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public void deleteMaterials(long id) {
        mDataBase.delete(TABLE_NAME_2, MATERIALS_ID + " = ?", new String[] { String.valueOf(id) });
    }

    //Select
    public OlympiadsDB selectOlympiads(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, OLYMPIADS_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();

        String olympiads_name = mCursor.getString(NUM_OlYMPIADS_NAME);
        String url = mCursor.getString(NUM_URL);
        String university = mCursor.getString(NUM_UNIVERSITY);
        int subject_id = mCursor.getInt(NUM_SUBJECT_ID);
        long date = mCursor.getLong(NUM_DATE);
       return new OlympiadsDB(id, olympiads_name, url, date, university, subject_id);

    }

    public MaterialsDB selectMaterials(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME_2, null, MATERIALS_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        String name = mCursor.getString(NUM_MATERIALS_NAME);
        String content = mCursor.getString(NUM_CONTENT);
        int id_from_olympiad = mCursor.getInt(NUM_ID_FROM_OLYMPIADS);
        String u_name = mCursor.getString(NUM_U_NAME);

        return new MaterialsDB(id, name, content, id_from_olympiad, u_name);



    }

    //SelectAll




    public ArrayList<OlympiadsDB> selectAllOlympiads() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<OlympiadsDB> arrO = new ArrayList<OlympiadsDB>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_OLYMPIADS_ID);
                String name = mCursor.getString(NUM_OlYMPIADS_NAME);
                String url = mCursor.getString(NUM_URL);
                String university = mCursor.getString(NUM_UNIVERSITY);
                long date=mCursor.getLong(NUM_DATE);
                int subj_id = mCursor.getInt(NUM_SUBJECT_ID);
                arrO.add(new OlympiadsDB(id, name, url, date, university, subj_id));
            } while (mCursor.moveToNext());
        }
        return arrO;
    }

    public ArrayList<MaterialsDB> selectAllMaterials(long olymp_id, String material_name) {
       Cursor mCursor = mDataBase.query(TABLE_NAME_2, null, ID_FROM_OLYMPIADS+"=? AND " + MATERIALS_NAME+"=?", new String[]{String.valueOf(olymp_id), material_name}, null, null, null);

        ArrayList<MaterialsDB> arrm = new ArrayList<MaterialsDB>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_MATERIALS_ID);
                String name = mCursor.getString(NUM_MATERIALS_NAME);
                String content = mCursor.getString(NUM_CONTENT);
                int id_from_olympiads = mCursor.getInt(NUM_ID_FROM_OLYMPIADS);
                String u_name = mCursor.getString(NUM_U_NAME);
                arrm.add(new MaterialsDB(id, name, content, id_from_olympiads, u_name));
            } while (mCursor.moveToNext());
        }
        return arrm;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query_olympiads = "CREATE TABLE " + TABLE_NAME + " (" +
                    OLYMPIADS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    OLYMPIADS_NAME+ " TEXT, " +
                    URL + " TEXT, " +
                    UNIVERSITY + " TEXT,"+
                    SUBJECT_ID + " INTEGER,"+
                    DATE+" LONG);";
            String query_materials = "CREATE TABLE " + TABLE_NAME_2 + " (" +
                    MATERIALS_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MATERIALS_NAME+ " TEXT, " +
                    ID_FROM_OLYMPIADS +" INTEGER,"+
                    CONTENT+" TEXT," +
                    U_NAME+" TEXT,"+
                    "FOREIGN KEY("+ID_FROM_OLYMPIADS+") REFERENCES "+TABLE_NAME+"("+OLYMPIADS_ID+")"+
                    ");";


            db.execSQL(query_olympiads);
            db.execSQL(query_materials);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
            onCreate(db);
        }
    }

}

