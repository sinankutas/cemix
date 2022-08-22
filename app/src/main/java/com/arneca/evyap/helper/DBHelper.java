package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 8/22/22.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * id : 18878
 * kod : 6187
 * ad : 8-12 BELİ VE PAÇA LASTİKLİ RENKLİ KIZ KOT PANTOLON
 * anagrup_kod : 8-12 KIZ KOT PANTOLON
 * beden :
 * beden_kodu :
 * renk :
 * renk_id : 0
 * pkadet : 5
 * dvz : USD
 * satis_fiyat : 0
 * d1 : 0
 * d14 : 0
 * d89 : 0
 * src : 6187_8-12 BELI VE PACA LASTIKLI RENKLI KIZ KOT PANTOLON_8681150061879
 *
 * */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Const.DATABASE_NAME , null, 1);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table '"+Const.TANIM_TABLE_NAME+"' " +
                        "(id text, kod text, ad text, anagrup_kod text, beden text, beden_kodu text, renk text, renk_id text, pkadet text, dvz text, satis_fiyat text, d1 text, d14 text, d89 text, src text)"
        );
    }

    public boolean insertContact (String id, String kod, String ad, String anagrup_kod, String beden,
                                  String beden_kodu, String renk, String renk_id, String pkadet, String dvz, String satis_fiyat,
                                  String d1, String d14, String d89, String src) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Const.ID, id);
        contentValues.put(Const.KOD, kod);
        contentValues.put(Const.AD, ad);
        contentValues.put(Const.ANAGRUP_KOD, anagrup_kod);
        contentValues.put(Const.BEDEN, beden);
        contentValues.put(Const.BEDEN_KODU, beden_kodu);
        contentValues.put(Const.RENK, renk);
        contentValues.put(Const.RENK_ID, renk_id);
        contentValues.put(Const.PKADET, pkadet);
        contentValues.put(Const.DVZ, dvz);
        contentValues.put(Const.SATIS_FIYAT, satis_fiyat);
        contentValues.put(Const.D1, d1);
        contentValues.put(Const.D14, d14);
        contentValues.put(Const.D89, d89);
        contentValues.put(Const.SRC, src);

        db.insert(Const.TANIM_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Const.TANIM_TABLE_NAME);
        return numRows;
    }


    public Integer deleteRecord (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Const.TANIM_TABLE_NAME,
                "id = ? ",
                new String[] { id });
    }

    public boolean updateRecords  (String id, String kod, String ad, String anagrup_kod, String beden,
                                   String beden_kodu, String renk, String renk_id, String pkadet, String dvz, String satis_fiyat,
                                   String d1, String d14, String d89, String src) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Const.ID, id);
        contentValues.put(Const.KOD, kod);
        contentValues.put(Const.AD, ad);
        contentValues.put(Const.ANAGRUP_KOD, anagrup_kod);
        contentValues.put(Const.BEDEN, beden);
        contentValues.put(Const.BEDEN_KODU, beden_kodu);
        contentValues.put(Const.RENK, renk);
        contentValues.put(Const.RENK_ID, renk_id);
        contentValues.put(Const.PKADET, pkadet);
        contentValues.put(Const.DVZ, dvz);
        contentValues.put(Const.SATIS_FIYAT, satis_fiyat);
        contentValues.put(Const.D1, d1);
        contentValues.put(Const.D14, d14);
        contentValues.put(Const.D89, d89);
        contentValues.put(Const.SRC, src);
        db.update(Const.TANIM_TABLE_NAME, contentValues, "id = ? ", new String[] { id } );
        return true;
    }

    public ArrayList<String> getAllRecords() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Const.AD)));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getAllRecordsCursor(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from '"+Const.TANIM_TABLE_NAME+"' ", null );
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
}
