package hr.algebra.java.edb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SeriesDatabase {

    public static int DBVERSION = 1;
    public static String DBNAME = "SeriesDB";
    public static String DBTABLE = "series";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + DBTABLE + " (_id INTEGER PRIMARY KEY autoincrement, name, genre, rating, year, UNIQUE(name))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DBTABLE);
            onCreate(db);
        }
    }

    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDb;

    public SeriesDatabase(Context context) {
        this.c = context;
    }

    public SeriesDatabase open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqlDb = dbHelper.getWritableDatabase();
        return this;
    }


    public void insert(String text2, String text3, String text4, String text5) {
        if (!isExist(text2)) {
            sqlDb.execSQL("INSERT INTO series (name,genre,rating,year) VALUES('" + text2 + "','" + text3 + "','" + text4 + "','" + text5 + "')");
        }
    }


    public boolean isExist(String n) {
        String query = "SELECT name FROM series WHERE name='" + n + "' LIMIT 1";
        Cursor row = sqlDb.rawQuery(query, null);
        return row.moveToFirst();
    }


    public void update(int id, String text2, String text3, String text4, String text5) {
        sqlDb.execSQL("UPDATE " + DBTABLE + " SET name='" + text2 + "', genre='" + text3 + "', rating='" + text4 + "', year='" + text5 + "'   WHERE _id=" + id);
    }


    public void delete(int id) {
        sqlDb.execSQL("DELETE FROM " + DBTABLE + " WHERE _id=" + id);
    }


    public Cursor fetchAllData() {
        String query = "SELECT * FROM " + DBTABLE;
        Cursor row = sqlDb.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }


    public Cursor fetchdatabyfilter(String inputText, String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM " + DBTABLE;
        if (inputText == null || inputText.length() == 0) {
            row = sqlDb.rawQuery(query, null);
        } else {
            query = "SELECT * FROM " + DBTABLE + " WHERE " + filtercolumn + " like '%" + inputText + "%'";
            row = sqlDb.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
}
