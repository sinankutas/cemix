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

import com.arneca.evyap.api.response.cmx.NewSayimModel;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.api.response.cmx.TanimlarResultModel;

import java.util.ArrayList;

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
                        "(id text, kod text, ad text, anagrup_kod text, beden text, beden_kodu text, renk text, renk_id text, pkadet text, dvz text, satis_fiyat text, d1 text, d14 text, d89 text, src text,renk_kodu_x text)"
        );

        sqLiteDatabase.execSQL(
                "create table '"+Const.TANIM_TABLE_NAME_NEW_SAYIM+"' " +
                        "( id INTEGER PRIMARY KEY AUTOINCREMENT, description text, idx text, sube_code text)"
        );
    }

    public boolean insertRecord (String id, String kod, String ad, String anagrup_kod, String beden,
                                  String beden_kodu, String renk, String renk_id, String pkadet, String dvz, String satis_fiyat,
                                  String d1, String d14, String d89, String src,String renk_kodu_x) {
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
        contentValues.put(Const.RENK_KODU_X, renk_kodu_x);
        db.insert(Const.TANIM_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertNewSayim (String desc, String idx, String sube_code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
    //    contentValues.put(Const.ID, id);
        contentValues.put(Const.DESCRIPTION, desc);
        contentValues.put(Const.IDX, idx);
        contentValues.put(Const.SUBE_CODE, sube_code);
        db.insert(Const.TANIM_TABLE_NAME_NEW_SAYIM, null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Const.TANIM_TABLE_NAME);
        return numRows;
    }

    public int numberOfSayimRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Const.TANIM_TABLE_NAME_NEW_SAYIM);
        return numRows;
    }


    public Integer deleteRecord (String src) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Const.TANIM_TABLE_NAME,
                "'"+Const.SRC+"' = ? ",
                new String[] { src });
    }

    public Integer deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Const.TANIM_TABLE_NAME, null, null);
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

    public ArrayList<NewSayimModel> getAllNewSayim(){
        ArrayList <NewSayimModel> newSayimModels = new ArrayList<>();
        NewSayimModel newSayimModel = new NewSayimModel();
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery("select * from " + Const.TANIM_TABLE_NAME + " like '"+Const.SRC+"' % '" +src + "'" , null);

        String query = "SELECT * FROM " +  Const.TANIM_TABLE_NAME_NEW_SAYIM;
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            newSayimModel = new NewSayimModel();
            newSayimModel.setId(cursor.getInt(cursor.getColumnIndex(Const.ID)));
            newSayimModel.setDesc(cursor.getString(cursor.getColumnIndex(Const.DESCRIPTION)));
            newSayimModel.setIdx(cursor.getString(cursor.getColumnIndex(Const.IDX)));
            newSayimModel.setSubeCode(cursor.getString(cursor.getColumnIndex(Const.SUBE_CODE)));

            newSayimModels.add(newSayimModel);
            //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return newSayimModels;
    }


    // ROOT DATA FOR TABS
    public ArrayList<TanimlarResultModel> getRecordWithGroupRBMatris(String beden_kodu,String stok_kodu){
        ArrayList <TanimlarResultModel> tanimlar = new ArrayList<>();
        TanimlarResultModel tanim = new TanimlarResultModel();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT s.kod , s.kod || '' || s.beden_kodu  || '' || s.renk_id AS id " +
                "  ,renk_id, renk,renk_kodu_x,  d1, d14, d89" +
                "  FROM tanim s" +
                "  WHERE beden_kodu = '"+beden_kodu+"'" +
                "  AND kod = '"+stok_kodu+"'" +
                "  order by kod, beden_kodu, renk_id";
        // String query2 = "SELECT kod, ad, anagrup_kod as anagrup, beden, satis_fiyat as fiyat" +"  FROM tanim " +"  WHERE beden_kodu = '639'" +"  GROUP BY kod, ad, anagrup_kod, beden, satis_fiyat";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            tanim = new TanimlarResultModel();
            tanim.setId(cursor.getString(cursor.getColumnIndex(Const.ID)));
            tanim.setKod(cursor.getString(cursor.getColumnIndex(Const.KOD)));
        //    tanim.setAd(cursor.getString(cursor.getColumnIndex(Const.AD)));
       //     tanim.setAnagrup_kod(cursor.getString(cursor.getColumnIndex(Const.ANAGRUP_KOD)));
        //    tanim.setBeden(cursor.getString(cursor.getColumnIndex(Const.BEDEN)));
            //    tanim.setBeden_id(cursor.getString(cursor.getColumnIndex(Const.BEDEN_ID)));
            tanim.setRenk(cursor.getString(cursor.getColumnIndex(Const.RENK)));
            tanim.setRenk_id(cursor.getString(cursor.getColumnIndex(Const.RENK_ID)));
            tanim.setRenk_kodu_x(cursor.getString(cursor.getColumnIndex(Const.RENK_KODU_X)));

            //   tanim.setPkadet(cursor.getString(cursor.getColumnIndex(Const.PKADET)));
            //    tanim.setDvz(cursor.getString(cursor.getColumnIndex(Const.DVZ)));
           // tanim.setSatis_fiyat(cursor.getString(cursor.getColumnIndex(Const.SATIS_FIYAT)));
            tanim.setD1(cursor.getString(cursor.getColumnIndex(Const.D1)));
            tanim.setD14(cursor.getString(cursor.getColumnIndex(Const.D14)));
            tanim.setD89(cursor.getString(cursor.getColumnIndex(Const.D89)));
            //    tanim.setSrc(cursor.getString(cursor.getColumnIndex(Const.SRC)));


            tanimlar.add(tanim);
            //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return tanimlar;
    }

    // ROOT DATA FOR TABS
    public ArrayList<TanimlarResultModel> getRecordWithGroupByBeden(String beden_kodu){
        ArrayList <TanimlarResultModel> tanimlar = new ArrayList<>();
        TanimlarResultModel tanim = new TanimlarResultModel();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT kod, ad, anagrup_kod, beden, satis_fiyat " +
                "  FROM tanim" +
                "  WHERE beden_kodu = '"+beden_kodu+"'" +
                "  GROUP BY kod, ad, anagrup_kod, beden, satis_fiyat";
       // String query2 = "SELECT kod, ad, anagrup_kod as anagrup, beden, satis_fiyat as fiyat" +"  FROM tanim " +"  WHERE beden_kodu = '639'" +"  GROUP BY kod, ad, anagrup_kod, beden, satis_fiyat";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            tanim = new TanimlarResultModel();
            //   tanim.setId(cursor.getString(cursor.getColumnIndex(Const.ID)));
            tanim.setKod(cursor.getString(cursor.getColumnIndex(Const.KOD)));
            tanim.setAd(cursor.getString(cursor.getColumnIndex(Const.AD)));
            tanim.setAnagrup_kod(cursor.getString(cursor.getColumnIndex(Const.ANAGRUP_KOD)));
            tanim.setBeden(cursor.getString(cursor.getColumnIndex(Const.BEDEN)));
            //    tanim.setBeden_id(cursor.getString(cursor.getColumnIndex(Const.BEDEN_ID)));
            //     tanim.setRenk(cursor.getString(cursor.getColumnIndex(Const.RENK)));
            //     tanim.setRenk_id(cursor.getString(cursor.getColumnIndex(Const.RENK_ID)));
            //   tanim.setPkadet(cursor.getString(cursor.getColumnIndex(Const.PKADET)));
            //    tanim.setDvz(cursor.getString(cursor.getColumnIndex(Const.DVZ)));
            tanim.setSatis_fiyat(cursor.getString(cursor.getColumnIndex(Const.SATIS_FIYAT)));
            //   tanim.setD1(cursor.getString(cursor.getColumnIndex(Const.D1)));
            //   tanim.setD14(cursor.getString(cursor.getColumnIndex(Const.D14)));
            //   tanim.setD89(cursor.getString(cursor.getColumnIndex(Const.D89)));
            //    tanim.setSrc(cursor.getString(cursor.getColumnIndex(Const.SRC)));


            tanimlar.add(tanim);
            //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return tanimlar;
    }

    public ArrayList<TanimlarResultModel> getRecordWithGroupBy(String src){
        ArrayList <TanimlarResultModel> tanimlar = new ArrayList<>();
        TanimlarResultModel tanim = new TanimlarResultModel();
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery("select * from " + Const.TANIM_TABLE_NAME + " like '"+Const.SRC+"' % '" +src + "'" , null);
        //  String query = "select kod, ad, anagrup_kod, beden, satis_fiyat, dvz, beden_kodu as beden_id from tanim s where s.src like '%"+src+"%' group by kod, ad, anagrup_kod, beden, satis_fiyat, dvz";
        String query = "select  kod, ad, anagrup_kod, beden,pkadet, satis_fiyat, dvz,beden_kodu as beden_id from tanim s where s.src like '%"+src+"%' group by kod, ad, anagrup_kod, beden,beden_kodu,pkadet, satis_fiyat, dvz";
    /*    String query = "select "+  Const.KOD +", "+  Const.AD +", "+  Const.ANAGRUP_KOD +", "+  Const.BEDEN +", "+  Const.SATIS_FIYAT +", "+  Const.DVZ +", "+  Const.BEDEN_KODU +" as "+  Const.BEDEN_KODU +" from "+Const.TANIM_TABLE_NAME+" s" +
                "where s.src like '%"+src+"%'" +
                "group by "+Const.KOD +", "+Const.AD +", "+Const.ANAGRUP_KOD +", "+Const.BEDEN +", "+Const.SATIS_FIYAT +", "+Const.DVZ +"";
*/
     //   String query = "SELECT * FROM " +  Const.TANIM_TABLE_NAME + " WHERE " +Const.SRC + " LIKE '%" + src + "%'" ;
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            tanim = new TanimlarResultModel();
            //   tanim.setId(cursor.getString(cursor.getColumnIndex(Const.ID)));
            tanim.setKod(cursor.getString(cursor.getColumnIndex(Const.KOD)));
            tanim.setAd(cursor.getString(cursor.getColumnIndex(Const.AD)));
            tanim.setAnagrup_kod(cursor.getString(cursor.getColumnIndex(Const.ANAGRUP_KOD)));
            tanim.setBeden(cursor.getString(cursor.getColumnIndex(Const.BEDEN)));
            tanim.setBeden_id(cursor.getString(cursor.getColumnIndex(Const.BEDEN_ID)));
       //     tanim.setRenk(cursor.getString(cursor.getColumnIndex(Const.RENK)));
       //     tanim.setRenk_id(cursor.getString(cursor.getColumnIndex(Const.RENK_ID)));
            tanim.setPkadet(cursor.getString(cursor.getColumnIndex(Const.PKADET)));
            tanim.setDvz(cursor.getString(cursor.getColumnIndex(Const.DVZ)));
            tanim.setSatis_fiyat(cursor.getString(cursor.getColumnIndex(Const.SATIS_FIYAT)));
            //     tanim.setD1(cursor.getString(cursor.getColumnIndex(Const.D1)));
            //    tanim.setD14(cursor.getString(cursor.getColumnIndex(Const.D14)));
            //   tanim.setD89(cursor.getString(cursor.getColumnIndex(Const.D89)));
            //    tanim.setSrc(cursor.getString(cursor.getColumnIndex(Const.SRC)));


            tanimlar.add(tanim);
            //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return tanimlar;
    }

    public ArrayList<TanimlarResultModel> getRecord(String src){
        ArrayList <TanimlarResultModel> tanimlar = new ArrayList<>();
        TanimlarResultModel tanim = new TanimlarResultModel();
        SQLiteDatabase db = this.getReadableDatabase();
       // Cursor cursor = db.rawQuery("select * from " + Const.TANIM_TABLE_NAME + " like '"+Const.SRC+"' % '" +src + "'" , null);

        String query = "SELECT * FROM " +  Const.TANIM_TABLE_NAME + " WHERE " +
                Const.SRC + " LIKE '%" + src + "%'" ;
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            tanim = new TanimlarResultModel();
            tanim.setId(cursor.getString(cursor.getColumnIndex(Const.ID)));
            tanim.setKod(cursor.getString(cursor.getColumnIndex(Const.KOD)));
            tanim.setAd(cursor.getString(cursor.getColumnIndex(Const.AD)));
            tanim.setAnagrup_kod(cursor.getString(cursor.getColumnIndex(Const.ANAGRUP_KOD)));
            tanim.setBeden(cursor.getString(cursor.getColumnIndex(Const.BEDEN)));
            tanim.setBeden_kodu(cursor.getString(cursor.getColumnIndex(Const.BEDEN_KODU)));
            tanim.setRenk(cursor.getString(cursor.getColumnIndex(Const.RENK)));
            tanim.setRenk_id(cursor.getString(cursor.getColumnIndex(Const.RENK_ID)));
            tanim.setPkadet(cursor.getString(cursor.getColumnIndex(Const.PKADET)));
            tanim.setDvz(cursor.getString(cursor.getColumnIndex(Const.DVZ)));
            tanim.setSatis_fiyat(cursor.getString(cursor.getColumnIndex(Const.SATIS_FIYAT)));
            tanim.setD1(cursor.getString(cursor.getColumnIndex(Const.D1)));
            tanim.setD14(cursor.getString(cursor.getColumnIndex(Const.D14)));
            tanim.setD89(cursor.getString(cursor.getColumnIndex(Const.D89)));
            tanim.setSrc(cursor.getString(cursor.getColumnIndex(Const.SRC)));


            tanimlar.add(tanim);
          //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return tanimlar;
    }

    public ArrayList<TanimlarResultModel> getAllRecords() {
        ArrayList <TanimlarResultModel> tanimlar = new ArrayList<>();
        TanimlarResultModel tanim = new TanimlarResultModel();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from  '"+Const.TANIM_TABLE_NAME+"'", null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            tanim = new TanimlarResultModel();
            tanim.setId(cursor.getString(cursor.getColumnIndex(Const.ID)));
            tanim.setKod(cursor.getString(cursor.getColumnIndex(Const.KOD)));
            tanim.setAd(cursor.getString(cursor.getColumnIndex(Const.AD)));
            tanim.setAnagrup_kod(cursor.getString(cursor.getColumnIndex(Const.ANAGRUP_KOD)));
            tanim.setBeden(cursor.getString(cursor.getColumnIndex(Const.BEDEN)));
            tanim.setBeden_kodu(cursor.getString(cursor.getColumnIndex(Const.BEDEN_KODU)));
            tanim.setRenk(cursor.getString(cursor.getColumnIndex(Const.RENK)));
            tanim.setRenk_id(cursor.getString(cursor.getColumnIndex(Const.RENK_ID)));
            tanim.setPkadet(cursor.getString(cursor.getColumnIndex(Const.PKADET)));
            tanim.setDvz(cursor.getString(cursor.getColumnIndex(Const.DVZ)));
            tanim.setSatis_fiyat(cursor.getString(cursor.getColumnIndex(Const.SATIS_FIYAT)));
            tanim.setD1(cursor.getString(cursor.getColumnIndex(Const.D1)));
            tanim.setD14(cursor.getString(cursor.getColumnIndex(Const.D14)));
            tanim.setD89(cursor.getString(cursor.getColumnIndex(Const.D89)));
            tanim.setSrc(cursor.getString(cursor.getColumnIndex(Const.SRC)));


            tanimlar.add(tanim);
            //  tanimlar.set(cursor.getString(cursor.getColumnIndex(Const.AD)));
            cursor.moveToNext();
        }
        return tanimlar;
    }

    public Cursor getAllRecordsCursor(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from '"+Const.TANIM_TABLE_NAME+"' ", null );
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    //    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tanim");
     //   onCreate(sqLiteDatabase);
    }
}
