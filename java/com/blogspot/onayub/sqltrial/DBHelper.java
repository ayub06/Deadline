package com.blogspot.onayub.sqltrial;

/**
 * Created by Shalahudin Al Ayyub on 23/10/2015.
 */

import java. util. ArrayList;

import android. content. ContentValues;
import android.content.Context;
import android. database. Cursor;
import android. database. DatabaseUtils;
import android. database. sqlite. SQLiteOpenHelper;
import android. database. sqlite. SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String DEADLINE_TABLE_NAME = "deadline";
    public static final String DEADLINE_COLUMN_ID = "id";
    public static final String DEADLINE_COLUMN_TITLE = "title";
    public static final String DEADLINE_COLUMN_DATE = "date";
    public static final String DEADLINE_COLUMN_DETAIL = "detail";
    /*public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";*/

    //private HashMap hp;


    public DBHelper(Context context)
    {
        super(context,  DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table deadline " +
                        "(id integer primary key, title text, date text, detail text)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS deadline");
        onCreate(db);
    }


    public boolean insertDeadline(String title, String date, String detail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues. put("title", title);
        contentValues. put("date", date);
        contentValues. put("detail", detail);

        db.insert("deadline", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from deadline where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this. getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DEADLINE_TABLE_NAME);
        return numRows;
    }

    public boolean updateDeadline(Integer id, String title, String date, String detail)
    {
        SQLiteDatabase db = this. getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues. put("title",  title);
        contentValues. put("date",  date);
        contentValues. put("detail",  detail);
        db.update("deadline",  contentValues, "id = ? ", new String[] {Integer.toString(id) } );
        return true;
    }
    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this. getWritableDatabase();
        return db. delete("deadline","id = ? ",new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getDataDeadline(int data)
    {
        ArrayList<String> array_list = new ArrayList<>();
        //hp = new HashMap();
        SQLiteDatabase db = this. getReadableDatabase();
        Cursor res = db. rawQuery( "select * from deadline", null );

        res. moveToFirst();
        switch (data) {
            case 0:
                while (!res.isAfterLast()) {
                array_list.add(res.getString(res.getColumnIndex(DEADLINE_COLUMN_TITLE)));
                res.moveToNext();
            }
                break;

            case 1:
                while (!res.isAfterLast()) {
                array_list.add(res.getString(res.getColumnIndex(DEADLINE_COLUMN_DETAIL)));
                res.moveToNext();
            }
                break;

            default:
                while (!res.isAfterLast()) {
                array_list.add(res.getString(res.getColumnIndex(DEADLINE_COLUMN_ID)));
                res.moveToNext();
            }
                break;
        }

        return array_list;
    }
}