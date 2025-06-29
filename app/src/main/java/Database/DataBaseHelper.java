package Database;

import static Database.DBAdapter.text1_name;
import static Database.DBAdapter.text2;
import static Database.DBAdapter.text2_tel;
import static Database.DBAdapter.text3_address;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.ArrayRes;

import com.example.myapp_.Main3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

/*
    int myIndex2 = ((Main3) Main3.myContext).myIndex; //mainAcitivity 에서 즐겨찾기 버튼 눌렀을 때 nullpoint 뜸
    String myItem = ((Main3) Main3.myContext).myItem;
    String myName = ((Main3) Main3.myContext).myName;

 */


    public static Cursor cursor = null;



    protected static String TAG = "DatabaseHelper";

    private static String databasePath = "";
    private static String databaseName = "NewDB.db";
    private final String tableName = "MyDB";
    private final String tableName2 = "myBook";

    private final Context mContext;
    private static SQLiteDatabase mDatabase;


    public DataBaseHelper(Context context) {
        super(context, databaseName, null, 1);

        if (Build.VERSION.SDK_INT >= 17) {
            databasePath = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            databasePath = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.mContext = context;
    }


    public boolean OpenDatabaseFile() throws SQLException {

        if (!CheckDatabaseFileExist()) {
            CreateDatabase();
        }

        String mPath = databasePath + databaseName;
        try {
            mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (SQLException sqlException) {
            Log.e(TAG, "[ERROR]" + "Can't Open Database");
        }
        return mDatabase != null;
    }

    // 데이터베이스 파일 존재 여부 확인
    public boolean CheckDatabaseFileExist() {
        File file = new File(databasePath + databaseName);
        return file.exists();
    }

    // Database 만들기
    public void CreateDatabase() throws SQLException {

        this.getReadableDatabase();
        this.close();

        try {
            CopyDatabaseFile();
            Log.e(TAG, "[SUCCESS] " + databaseName + " are Created");
        } catch (IOException ioException) {
            // Error Message
            Log.e(TAG, "[ERROR] " + "Unable to create " + databaseName);
            throw new Error(TAG);
        }
    }


    // 데이터베이스 복사
    public void CopyDatabaseFile() throws IOException {


        InputStream inputStream = mContext.getAssets().open(databaseName);
        String outputFileName = databasePath + databaseName;
        OutputStream outputStream = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    @Override
    public void onCreate (SQLiteDatabase db){

    }

    public List getTableData() {
        int myIndex2 = ((Main3) Main3.myContext).myIndex;

        try {
            // 테이블 정보를 저장할 List
            List mList = new ArrayList();

            // 쿼리
            String sql = "SELECT name, tel, address FROM " + tableName + " where position = " + myIndex2;
            //SELECT name from test where position like '% myIndex2 %'; -- position 값이 여러개 일 때

            // 테이블 데이터를 읽기 위한 Cursor
            Cursor mCursor = mDatabase.rawQuery(sql, null);

            // 테이블 끝까지 읽기
            if (mCursor != null) {

                // 다음 Row로 이동
                while (mCursor.moveToNext()) {

                    // 해당 Row 저장
                    MyData myData = new MyData();

                    myData.setName(mCursor.getString(0));
                    myData.setTel(mCursor.getString(1));
                    myData.setAddress(mCursor.getString(2));


                    // List에 해당 Row 추가
                    mList.add(myData);
                }

            }
            return mList;

        } catch (SQLException mSQLException) {
            // Error Message
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }

    }

    public List getTabaleData2() {  //spinner select query

        int myIndex2 = ((Main3) Main3.myContext).myIndex;
        String myItem = ((Main3) Main3.myContext).myItem;

        try {
            // 테이블 정보를 저장할 List
            List mList = new ArrayList();

            // 쿼리;
            String sql = "SELECT name, tel, address FROM " + tableName + " where address like '%" + myItem + "%' AND position =" + myIndex2;

            // 테이블 데이터를 읽기 위한 Cursor
            Cursor mCursor = mDatabase.rawQuery(sql, null);

            // 테이블 끝까지 읽기
            if (mCursor != null) {

                // 다음 Row로 이동
                while (mCursor.moveToNext()) {

                    // 해당 Row 저장
                    MyData myData = new MyData();

                    myData.setName(mCursor.getString(0));
                    myData.setTel(mCursor.getString(1));
                    myData.setAddress(mCursor.getString(2));


                    // List에 해당 Row 추가
                    mList.add(myData);
                }

            }
            return mList;

        } catch (SQLException mSQLException) {
            // Error Message
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }
    }
    public List getTableData3() {
        String myName = ((Main3) Main3.myContext).myName;

        try {
            // 테이블 정보를 저장할 List
            List mList = new ArrayList();

            // 쿼리
            String sql = "SELECT address FROM " + tableName + " where name = " + myName;

            // 테이블 데이터를 읽기 위한 Cursor
            Cursor mCursor = mDatabase.rawQuery(sql, null);

            // 테이블 끝까지 읽기
            if (mCursor != null) {

                // 다음 Row로 이동
                while (mCursor.moveToNext()) {

                    // 해당 Row 저장
                    MyData myData = new MyData();

                    myData.setName(mCursor.getString(0));
                    myData.setTel(mCursor.getString(1));
                    myData.setAddress(mCursor.getString(2));


                    // List에 해당 Row 추가
                    mList.add(myData);
                }

            }
            return mList;

        } catch (SQLException mSQLException) {
            // Error Message
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }

    }

    public List putMyBookData() {  //북마크 구현

        try {
            // 테이블 정보를 저장할 List
            List mList = new ArrayList();

            // 쿼리;
            String sql = "insert into " + tableName2 + " values ( \' " +
                    text1_name + "\',\'" +
                    text2_tel + "\',\'" +
                    text3_address + "\')";

            // 테이블 데이터를 읽기 위한 Cursor
            //Cursor mCursor = mDatabase.rawQuery(sql, null);
            cursor = mDatabase.rawQuery(sql, null);

                // 테이블 끝까지 읽기
                if (cursor != null) {


                        // 다음 Row로 이동
                    while (cursor.moveToNext()) {

                        // 해당 Row 저장
                        MyBook myBook = new MyBook();

                        myBook.setName(cursor.getString(0));
                        myBook.setTel(cursor.getString(1));
                        myBook.setAddress(cursor.getString(2));

                        // List에 해당 Row 추가
                        mList.add(myBook);

                    }

                }

                return mList;
        } catch (SQLException mSQLException) {
            // Error Message
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }






    }

    public List getMyBookData() {  //북마크 구현

        try {
            // 테이블 정보를 저장할 List
            List mList = new ArrayList();

            // 쿼리;
            String sql = "select * from " + tableName2;

            // 테이블 데이터를 읽기 위한 Cursor
            Cursor mCursor = mDatabase.rawQuery(sql, null);

         //   cursor = mDatabase.rawQuery(sql, null);


                // 테이블 끝까지 읽기
            if (mCursor != null) {

                // 다음 Row로 이동
                while (mCursor.moveToNext()) {

                    // 해당 Row 저장
                    MyBook myBook = new MyBook();

                    myBook.setName(mCursor.getString(0));
                    myBook.setTel(mCursor.getString(1));
                    myBook.setAddress(mCursor.getString(2));


                    // List에 해당 Row 추가
                    mList.add(myBook);
                }

            }
            return mList;

        } catch (SQLException mSQLException) {
            // Error Message
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }
    }
/*
    public boolean checkName(){ //null error

        String sql = "select name from myBook where name = " + "\'" + text1_name + "\'" ;
        Cursor cursor = mDatabase.rawQuery(sql, null);
        if (cursor != null) {

            // 다음 Row로 이동
            while (cursor.moveToNext()) {
                int cursorCount = cursor.getCount();
                cursor.close();
                mDatabase.close();
        }
            if (cursorCount == 0) {
                return false;
            }
            return true;
        }
    }

 */

        public void CloseDatabaseFile () {
            if (mDatabase != null) {
                mDatabase.close();
            }
        }

        @Override
        public synchronized void close () {
            CloseDatabaseFile();
            super.close();
        }


        @Override
        public void onUpgrade (SQLiteDatabase mDatabase,int i, int i1){

        }
/*
public boolean checkName(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select name from"+ tableName2 +" where name = " + text1_name;
        Cursor cursor = db.rawQuery(sql, null);

    int cursorCount = cursor.getCount();
    cursor.close();
    db.close();
    if (cursorCount > 0) {
        return true;
    }
    return false;

}
 */
}



