package javier.ponsoda.uv.es;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import javier.ponsoda.uv.es.ReportContract;

public class SqLiteHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Reports.db";

    public SqLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReportContract.FeedEntry.TABLA_REPORTS + " (" +
                    ReportContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ReportContract.FeedEntry.COLUMN_NAME + " TEXT," +
                    ReportContract.FeedEntry.COLUMN_DESCRIPTION + " TEXT,"+
                    ReportContract.FeedEntry.COLUMN_STATION + " TEXT,"+
                    ReportContract.FeedEntry.COLUMN_STATUS + " TEXT,"+
                    ReportContract.FeedEntry.COLUMN_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReportContract.FeedEntry.TABLA_REPORTS;

    public long insertReport (String nombre,String descripcion,String estacion, String estado, String tipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("estacion", estacion);
        values.put("estado", estado);
        values.put("tipo", tipo);

        long newRowId = db.insert(ReportContract.FeedEntry.TABLA_REPORTS, null, values);
        return newRowId;
    }
    public boolean updateReport (long id, String nombre,String descripcion,String estacion, String estado, String tipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("estacion", estacion);
        values.put("estado", estado);
        values.put("tipo", tipo);

        String selection = ReportContract.FeedEntry._ID + " = " + id;

        db.update(ReportContract.FeedEntry.TABLA_REPORTS, values, selection,null);
        return true;
    }
    public boolean deleteReport (long id){
        SQLiteDatabase db = this.getWritableDatabase();
        ///AL IGUAL FALTA EL ID EN EL REPORT CONTRACT EH
        String selection = ReportContract.FeedEntry._ID + " = " + id;
        db.delete(ReportContract.FeedEntry.TABLA_REPORTS, selection,null);
        return true;
    }
    public Cursor findReportByBikeStation (String estacion){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select * from "+ ReportContract.FeedEntry.TABLA_REPORTS + " where "+  ReportContract.FeedEntry.COLUMN_STATION +" = " + estacion, null );

        return res;
    }
    public Cursor findReportByID (long ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select * from "+ ReportContract.FeedEntry.TABLA_REPORTS + " where "+  ReportContract.FeedEntry._ID +" = " + ID, null );

        return res;
    }
}

