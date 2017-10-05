package com.example.wiesiek.beatroullete2;

/**
 * Created by Wiesiek on 2017-08-03.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Wiesiek on 2017-06-13.
 */

public class ManagerDatabase {

    DatabaseClass databaseClass;

    public ManagerDatabase (Context context){
        databaseClass= new DatabaseClass(context);

    }

    // trzeba bedzie zrobic delete jednej kolumny
    //ponieważ każde koło to będzie jedna kolumna
    public void DeleteColumn(String columna){
        SQLiteDatabase db = databaseClass.getWritableDatabase();
//        db.delete(DatabaseClass.TABLE_NAME, null, null);
        ContentValues values = new ContentValues();
        values.put(columna, "");
        db.update(DatabaseClass.TABLE_NAME, values, null, null);
    }



    public void DeleteData (){
        SQLiteDatabase db = databaseClass.getWritableDatabase();
        db.delete(DatabaseClass.TABLE_NAME, null, null);

    }


    public String ReturnAllDataAsAString(String columna) {
        SQLiteDatabase db = databaseClass.getWritableDatabase();
        String [] columns = {columna};

        Cursor cursor = db.query(DatabaseClass.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String cursorResult =cursor.getString(cursor.getColumnIndex(columna));
            if (!(cursor.equals(""))){
            stringBuffer.append(cursorResult);
            }
//            Log.d("info", cursor.getString(cursor.getColumnIndex(DatabaseClass.Col)));

        }
        String result = stringBuffer.toString();
        for (int i=0;i<result.length();i++){
            if (result.charAt(i)>='a' && result.charAt(i) <='z'){
                result= result.substring(0,i)+result.substring(i+1,result.length());
                i=i-1;
            }
        }
        return result;
    }
//

    public void AddData (String textOf_of_editText, String columna){
        SQLiteDatabase db = databaseClass.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columna,textOf_of_editText);
        long num = db.insert(DatabaseClass.TABLE_NAME,null,contentValues);

        if (num >0){
            Log.d("info", "succes");
        }else {
            Log.d("info", "mistake ");
        }

    }


    static class DatabaseClass extends SQLiteOpenHelper{

        private static final String DATABASE_NAME ="NotesDatabase";
        public static final String TABLE_NAME ="MainTable";
        private static final int VERSION =1;
        public static final String ID_COLUMN ="ID_Name";
        public static final String Col ="FirstColumn";
        public static final String Col2 ="SecondColumn";
        public static final String Col3 ="ThirdColumn";
        public static final String Col4 ="Column4";
        public static final String Col5 ="Column5";
        public static final String Col6 ="Column6";
        public static final String Col7 ="Column7";
        public static final String Col8 ="Column8";
        public static final String Col9 ="Column9";
        public static final String Col10 ="Column10";
        public static final String Col11 ="Column11";
        public static final String Col12 ="Column12";
        public Context context;


        public DatabaseClass(Context context) {

            super(context, DATABASE_NAME, null, VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String query = "CREATE TABLE "+ TABLE_NAME + " ("
                    +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Col + " TEXT, "
                    + Col2 + " TEXT, "
                    + Col3 + " TEXT, "
                    + Col4 + " TEXT, "
                    + Col5 + " TEXT, "
                    + Col6 + " TEXT, "
                    + Col7 + " TEXT, "
                    + Col8 + " TEXT, "
                    + Col9 + " TEXT, "
                    + Col10 + " TEXT, "
                    + Col11 + " TEXT, "
                    + Col12 + " TEXT "
                    + ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
    }
}






//    public String ReturnAllDataAsAStringCol2() {
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
//        String [] columns = {DatabaseClass.Col2};
//        Cursor cursor = db.query(DatabaseClass.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer stringBuffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//
//            stringBuffer.append(cursor.getString(cursor.getColumnIndex(DatabaseClass.Col2)));
////            Log.d("info", cursor.getString(cursor.getColumnIndex(DatabaseClass.Col2)));
//
//        }
//        String result = stringBuffer.toString();
//        //jak zrobić żeby aby letter
//        for (int i=0;i<result.length();i++) {
//            if (result.charAt(i) >= 'a' && result.charAt(i) <= 'z') {
//                result = result.substring(0, i) + result.substring(i + 1, result.length());
//                i = i - 1;
//            }
//        }
//        return result;
//    }
//    public String ReturnAllDataAsAStringCol3() {
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
//        String [] columns = {DatabaseClass.Col3};
//        Cursor cursor = db.query(DatabaseClass.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer stringBuffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//
//            stringBuffer.append(cursor.getString(cursor.getColumnIndex(DatabaseClass.Col3)));
////            Log.d("info", cursor.getString(cursor.getColumnIndex(DatabaseClass.Col2)));
//
//        }
//        String result = stringBuffer.toString();
//        //jak zrobić żeby aby letter
//        for (int i=0;i<result.length();i++) {
//            if (result.charAt(i) >= 'a' && result.charAt(i) <= 'z') {
//                result = result.substring(0, i) + result.substring(i + 1, result.length());
//                i = i - 1;
//            }
//        }
//        return result;
//    }
//
//    public void DeleteColumn2 (){
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
////        db.delete(DatabaseClass.TABLE_NAME, null, null);
//        ContentValues values = new ContentValues();
//        values.put(DatabaseClass.Col2, "");
//        db.update(DatabaseClass.TABLE_NAME, values, null, null);
//    }
//    public void DeleteColumn3 (){
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
////        db.delete(DatabaseClass.TABLE_NAME, null, null);
//        ContentValues values = new ContentValues();
//        values.put(DatabaseClass.Col3, "");
//        db.update(DatabaseClass.TABLE_NAME, values, null, null);
//    }

//    public void AddDataCol2 (String f){
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseClass.Col2,f);
//        long num = db.insert(DatabaseClass.TABLE_NAME,null,contentValues);
//
//        if (num >0){
//            Log.d("info", "succes");
//        }else {
//            Log.d("info", "mistake ");
//        }
//
//    }
//    public void AddDataCol3 (String textOf_of_editText){
//        SQLiteDatabase db = databaseClass.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseClass.Col3,textOf_of_editText);
//        long num = db.insert(DatabaseClass.TABLE_NAME,null,contentValues);
//
//        if (num >0){
//            Log.d("info", "succes");
//        }else {
//            Log.d("info", "mistake ");
//        }
//
//    }
