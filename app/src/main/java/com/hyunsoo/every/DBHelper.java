package com.hyunsoo.every;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    static String NAME = "item1.db";
    static SQLiteDatabase.CursorFactory FACTORY = null;
    static String PACKAGE = "com.hyunsoo.every";
    static String DB_ori="item.mp4";
    static String DB = "item.db";
    static int VERSION  = 1;

    private Bitmap bitmap;
    private Context mContext;

    public DBHelper(Context context) {
        super(context, NAME, FACTORY, VERSION);
    }

    // DB가 있나 체크
    public boolean isCheckDB(Context mContext){
        String filePath = "/data/data/com.hyunsoo.every/databases/item1.db";
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }


    // assets의 db파일을 설치된 프로그램의 내부로 복사
    public void copyDB(Context mContext){

        Log.d("MiniAspp", "copyDB");
        AssetManager manager = mContext.getAssets();
        String folderPath = "/data/data/com.hyunsoo.every/databases";
        String filePath = "/data/data/com.hyunsoo.every/databases/item.mp4";
        File folder = new File(folderPath);
        File file = new File(filePath);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            InputStream is = manager.open("item.mp4");
            BufferedInputStream bis = new BufferedInputStream(is);
            if (folder.exists()) {
            } else {
                folder.mkdirs();
            }
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            else{file.createNewFile();}
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int read = -1;
            byte[] buffer = new byte[1024];
            while((read=bis.read(buffer,0,1024))!=-1){
                bos.write(buffer,0,read);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            is.close();

        } catch (IOException e) {
            Log.e("ErrorMessage : ", e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table ITEM (_id integer primary key autoincrement,itemname text, price integer, context text, image BLOB)");
        //copyDB(mContext);
//        try {

//            boolean bResult = isCheckDB(mContext);  // DB가 있는지?
//            Log.i("MiniApp", "DB Check="+bResult);
//            if(!bResult){   // DB가 없으면 복사
//                copyDB(mContext);
//            }else{
//            }
//        } catch (Exception e) {
//        }
//
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            sqLiteDatabase.execSQL("DROP TABLE ITEM");
            onCreate(sqLiteDatabase);
        }
    }

    //데이터베이스에 추가
    public void onInsert(String itemname, int price, String context,byte[] image) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //sqLiteDatabase.execSQL("delete from ITEM"); //전체삭제
        String itemValue = "INSERT INTO ITEM VALUES(null, '" + itemname + "', " + price + ", '" + context + "','" + image + "');";
        sqLiteDatabase.execSQL(itemValue);
        sqLiteDatabase.close();
    }

    public String getResult() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String result = "";

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ITEM", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " : "
                    + cursor.getInt(2)
                    + " : "
                    + cursor.getString(3)
                    + " : "
                    + cursor.getBlob(4)
                    + "\n";
        }
        return result;
    }

    //문자열을 포함한 단어가 데이터베이스 안에 있는지 검색
    public String find(String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT itemname FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY _id DESC", null);
        String get_name = "";
        while(cursor.moveToNext()){
            get_name += cursor.getString(0);
        }
        cursor.close();
        return get_name; //문자열 포함한 데이터베이스 리턴
    }

    //최신순 정렬시
    public String getname_new(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT itemname FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY _id DESC LIMIT 1 OFFSET "+(num-1), null);
        String get_name = "";
        while(cursor.moveToNext()){
            get_name += cursor.getString(0);
        }
        cursor.close();
        return get_name;
    }
    public String getprice_new(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT price FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY _id DESC LIMIT 1 OFFSET "+(num-1), null);
        String get_price = "";
        while(cursor.moveToNext()){
            get_price += cursor.getString(0);
        }
        cursor.close();
        return get_price;
    }

    //낮은가격순 정렬시
    public String getname_price(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT itemname FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY price asc LIMIT 1 OFFSET "+(num-1), null);
        String get_name = "";
        while(cursor.moveToNext()){
            get_name += cursor.getString(0);
        }
        cursor.close();
        return get_name;
    }
    public String getprice_price(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT price FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY price asc LIMIT 1 OFFSET "+(num-1), null);
        String get_price = "";
        while(cursor.moveToNext()){
            get_price += cursor.getString(0);
        }
        cursor.close();
        return get_price;
    }

    //가나다순 정렬시
    public String getname_ko(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT itemname FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY itemname asc LIMIT 1 OFFSET "+(num-1), null);
        String get_name = "";
        while(cursor.moveToNext()){
            get_name += cursor.getString(0);
        }
        cursor.close();
        return get_name;
    }
    public String getprice_ko(int num,String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT price FROM ITEM WHERE itemname LIKE "+"'%"+str+"%'"+" ORDER BY itemname asc LIMIT 1 OFFSET "+(num-1), null);
        String get_price = "";
        while(cursor.moveToNext()){
            get_price += cursor.getString(0);
        }
        cursor.close();
        return get_price;
    }

    //문자열과 일치하는 데이터가 데이터베이스 안에 있을때
    //상품명 리턴
    public String getname(String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT itemname FROM ITEM WHERE itemname = "+"'"+str+"'", null);
        String get_name = "";
        while(cursor.moveToNext()){
            get_name += cursor.getString(0);
        }
        cursor.close();
        return get_name;
    }
    //가격리턴
    public String getprice(String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT price FROM ITEM WHERE itemname = "+"'"+str+"'", null);
        String get_price = "";
        while(cursor.moveToNext()){
            get_price += cursor.getString(0);
        }
        cursor.close();
        return get_price;
    }//내용 리턴
    public String getcontext(String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT context  FROM ITEM WHERE itemname = "+"'"+str+"'", null);
        String get_context = "";
        while(cursor.moveToNext()){
            get_context += cursor.getString(0);
        }
        cursor.close();
        return get_context;
    }
    //바이트 배열 리턴
    public byte[] getbyte(String str){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT image FROM ITEM WHERE itemname = "+"'"+str+"'", null);
        byte[] b= new byte[1024];
        while(cursor.moveToNext()) {
            b = cursor.getBlob(0);
        }
        cursor.close();

        return b;
    }

    //바이트를 비트맵으로
    public Bitmap getimage(byte[] b) {

        bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bitmap;
    }
}
