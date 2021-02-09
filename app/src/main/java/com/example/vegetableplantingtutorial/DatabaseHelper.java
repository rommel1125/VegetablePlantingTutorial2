package com.example.vegetableplantingtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Vegetable.db";

    public static final String TABLE_NAME = "Vegetable_table";
    public static final String COLUMN_VEGETABLE_ID = "ID";                            //  0
    public static final String COLUMN_VEGETABLE_NAME = "NAME";                          //  1
    public static final String COLUMN_VEGETABLE_DESCRIPTION = "DESCRIPTION";                   //  2
    public static final String COLUMN_VEGETABLE_URL = "VEGETABLE_URL";  //  3
    public static final String COLUMN_CATEGORY_FK = "category_id";      //  4
    public static final String COLUMN_VEGETABLE_IMAGE = "image";        //  5

    public static final String CATEGORY_TABLE = "categories";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGOR_NAME = "name";

    //for the plan table
    public static final String TABLE_PLAN = "Plan_Table";
    public static final String COLUMN_PLAN_ID = "plan_id";
    public static final String COLUMN_PLAN_VEGETABLE_NAME = "vegetable_name";
    public static final String COLUMN_PLAN_DATE = "note_date";
    public static final String COLUMN_DATE_PLANTED = "date_planted";
    public static final String COLUMN_PLAN_NOTE = "note";
    public static final String SQL_CREATE_TABLE_PLAN = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAN + " ("
            + COLUMN_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PLAN_VEGETABLE_NAME + " TEXT, "
            + COLUMN_DATE_PLANTED + " TEXT, "
            + COLUMN_PLAN_NOTE + "TEXT, "
            + COLUMN_PLAN_DATE + "TEXT "
            + ");";


    public static final String ALTER_VEGETABLE_TABLE_1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " +
            "" + COLUMN_VEGETABLE_URL + " TEXT";
    public static final String ALTER_VEGETABLE_TABLE_2 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " +
            "" + COLUMN_CATEGORY_FK + " INTEGER";
    public static final String ADD_IMAGE_COLUMN = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_VEGETABLE_IMAGE + " BLOB";

    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "" + CATEGORY_TABLE + " " +
            "(" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + CATEGOR_NAME + " TEXT)";


    public static final int SCHEMA_VERSION = 4;
//
//    Context cont;
//
//    private ByteArrayOutputStream byteArrayOutputStream;
//    private byte[] byteArray;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "" + COLUMN_VEGETABLE_NAME + " TEXT, " +
//                "" + COLUMN_VEGETABLE_DESCRIPTION + " TEXT)");
        db.execSQL(SQL_CREATE_TABLE_PLAN);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLAN);
//        if(oldVersion < 2) {
//            db.execSQL(ALTER_VEGETABLE_TABLE_1);
//        }
//        if(oldVersion < 3) {
//            db.execSQL(CREATE_CATEGORY_TABLE);
//            db.execSQL(ALTER_VEGETABLE_TABLE_2);
//        }
//        if(oldVersion < 4) {
//            db.execSQL(ADD_IMAGE_COLUMN);
//            Log.d("Success: ", "Successfully added image column in vegetables table!");
//        }
    }
    public boolean insertDataPlan(String name, String datePlanted, String note, String dateNote){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("datePlanted", datePlanted);
        contentValues.put("note", note);
        contentValues.put("dateNote", dateNote);
        long result=database.insert(TABLE_PLAN,null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor viewDataPlan(String pangalanVege, String dateTanim){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_PLAN+" WHERE "+COLUMN_PLAN_VEGETABLE_NAME+" = "+pangalanVege+" AND "+COLUMN_PLAN_DATE+" = "+dateTanim;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

//    public VegetableModel fetchVegetableById(String id) {
//
//        VegetableModel vegetable = null;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT " +
//                TABLE_NAME + "." + COLUMN_VEGETABLE_ID + ", " +   //  vegetable id
//                TABLE_NAME + "." + COLUMN_VEGETABLE_NAME + ", " +   //  vegetable name
//                TABLE_NAME + "." + COLUMN_VEGETABLE_DESCRIPTION + ", " +   //  vegetable description
//                TABLE_NAME + "." + COLUMN_VEGETABLE_URL + ", " +
//                TABLE_NAME + "." + COLUMN_CATEGORY_FK + ", " +
//                CATEGORY_TABLE + "." + CATEGOR_NAME + ", " +
//                TABLE_NAME + "." + COLUMN_VEGETABLE_IMAGE +
//                " FROM " + TABLE_NAME +
//                " LEFT JOIN " + CATEGORY_TABLE +
//                " ON " +
//                TABLE_NAME + "." + COLUMN_CATEGORY_FK + " = " + CATEGORY_TABLE + "." + CATEGORY_ID +
//                " WHERE " + TABLE_NAME + "." + COLUMN_VEGETABLE_ID + " = ?";
//
//        Cursor result = db.rawQuery(query, new String [] { id });
//
//        if(result.moveToFirst()) {
//            int vegetableId = result.getInt(0);
//            String name = result.getString(1);
//            String description = result.getString(2);
//            String tutorialURI = result.getString(3);
//            int categoryId = result.getInt(4);
//            String categoryName = result.getString(5);
//
//            byte [] imageByte = result.getBlob(6);
//            Bitmap vegetableImage = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//            vegetable = new VegetableModel(vegetableId, name, description, tutorialURI, categoryId, categoryName, vegetableImage);
//        }
//        return vegetable;
//    }
//
//
//    public ArrayList<VegetableModel> fetchVegetableByCategory(String category_id) {
//
//        ArrayList<VegetableModel> vegetables = new ArrayList<>();
//        VegetableModel model;
//
//        try {
//
//            SQLiteDatabase db = this.getReadableDatabase();
//            String query = "SELECT "+ COLUMN_VEGETABLE_ID +", "+ COLUMN_VEGETABLE_NAME +" FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY_FK + " = ?";
//            Cursor result = db.rawQuery(query, new String[] { category_id });
//
//            while(result.moveToNext()) {
//                int id = result.getInt(result.getColumnIndex(COLUMN_VEGETABLE_ID));
//                String name = result.getString(result.getColumnIndex(COLUMN_VEGETABLE_NAME));
////                String description = result.getString(result.getColumnIndex(COL_3));
////                String uri = result.getString(result.getColumnIndex(COLUMN_VEGETABLE_URL));
////
////                byte [] imageByte = result.getBlob(result.getColumnIndex(COLUMN_VEGETABLE_IMAGE));
////                Bitmap image = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//
//                model = new VegetableModel(id, name, null, null, 0, null, null);
//                vegetables.add(model);
//            }
//            db.close();
//        }
//        catch(Exception e) {
//            Log.d("Error", e.toString());
//        }
//
//        return vegetables;
//    }
//
//
//    public ArrayList<CategoryModel> fetchCategories() {
//
//        ArrayList<CategoryModel> categories = new ArrayList<>();
//        CategoryModel category;
//
//        String query = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " +
//                CATEGORY_ID + " < 5";
//
//        try {
//
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor result = db.rawQuery(query, null);
//
//            while(result.moveToNext()) {
//
//                category = new CategoryModel(result.getString(0), result.getString(1));
//                categories.add(category);
//            }
//            db.close();
//        }
//        catch(Exception e) {
//            Log.d("Error", e.toString());
//        }
//
//        return categories;
//    }
//
//
//    public boolean addData(String name, String des){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_VEGETABLE_NAME, name);
//        contentValues.put(COLUMN_VEGETABLE_DESCRIPTION, des);
//
//        Log.d(TAG, "addData: Adding "+name+" to "+TABLE_NAME);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//        if (result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
////    public Cursor getData(int id){
////        SQLiteDatabase db = this.getWritableDatabase();
////        String query = "SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE "+COL_1+" = "+id;
////        String query = "SELECT "+COL_1+", "+COL_2+", "+COL_3+", "+COLUMN_VEGETABLE_URL+" FROM "+TABLE_NAME+" WHERE "+COL_1+" = "+id;
////        Cursor cursor = db.rawQuery( query,null);
////        return cursor;
////    }
//
//    public void inputTutorialURL() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv;
//        Helper h = new Helper();
//
//        for(int i = 0; i < 10; i++) {
//
//            cv = new ContentValues();
//            cv.put(COLUMN_VEGETABLE_URL, h.getUri(i+1));
//            db.update(TABLE_NAME, cv, "ID=?", new String[] { String.valueOf(i+1) });
//        }
//
//        db.close();
//    }
//
//    public void populateCategoryTable() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(CATEGOR_NAME, "JAN - MAR");
//        db.insert(CATEGORY_TABLE, null, cv);
//
//        cv = new ContentValues();
//        cv.put(CATEGOR_NAME, "APR - JUN");
//        db.insert(CATEGORY_TABLE, null, cv);
//
//        cv = new ContentValues();
//        cv.put(CATEGOR_NAME, "JUL - SEP");
//        db.insert(CATEGORY_TABLE, null, cv);
//
//        cv = new ContentValues();
//        cv.put(CATEGOR_NAME, "OCT - DEC");
//        db.insert(CATEGORY_TABLE, null, cv);
//
//        Log.d("TAG", "Category Populated");
//
//        db.close();
//    }
//
//    public void updateVVegetableCategory() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv;
////        Jan - Mar =   1
////        Apr - Jun =   2
////        Jul - Sep =   3
////        Oct - Dec =   4
//
//
////        1-Eggplant    =   2
////        2-Kamatis     =   3
////        3-Repolyo      =   1
////        4-Sitaw       =   3
////        5-Squash      =   2
////        6-malunggay   =   1
////        7-kangkong    =   2
////        8-Lettuce      =   2
////        9-upo         =   4
////        10-ampalaya   =   4
//
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "2");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "1" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "3");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "2" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "1");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "3" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "3");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "4" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "2");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "5" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "1");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "6" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "2");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "7" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "2");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "8" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "4");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "9" });
//
//        cv = new ContentValues();
//        cv.put(COLUMN_CATEGORY_FK, "4");
//        db.update(TABLE_NAME, cv, "ID=?", new String[] { "10" });
//
//        db.close();
//    }
//
//    public void updateVegetableImage(String vegetableId, Bitmap image) {
//
//        if(vegetableId == null)
//            return;
//
//        try {
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues cv = new ContentValues();
//
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//            byteArray = byteArrayOutputStream.toByteArray();
//
//            cv.put(COLUMN_VEGETABLE_IMAGE, byteArray);
//            if(vegetableId != null) {
//                long result = db.update(TABLE_NAME, cv, "ID=?", new String[] { vegetableId });
//
//                if(result != -1) {
//                    Log.d("Success", "Updated the image of VEGETABLE_ID = " + vegetableId);
//                    db.close();
//                }
//                else {
//                    Log.d("Error", "Could not update the vegetable image");
//                }
//
//            }
//            db.close();
//        }
//        catch(Exception e) {
//            Toast.makeText(cont, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
}
