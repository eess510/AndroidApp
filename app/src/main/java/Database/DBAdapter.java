package Database;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp_.R;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter extends BaseAdapter {

    Context mContext;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;


    LayoutInflater layoutInflater;
    ArrayList<MyData> myData;
    //ArrayList<MyBook> myBook = new ArrayList<>();
    ArrayList<MyBook> myBookList;


    String a_test;
    public static String text1_name, text2_tel, text3_address;
    public static String text2;


    public DBAdapter(Context context, ArrayList<MyData> data) {

        mContext = context;
        myData = data;
        layoutInflater = LayoutInflater.from(mContext);


    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyData getItem(int position) {
        return myData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = layoutInflater.inflate(R.layout.item_list, null);


        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView tel = (TextView) view.findViewById(R.id.txtTel);
        TextView address = (TextView) view.findViewById(R.id.txtAddress);

        Button btnStar = (Button) view.findViewById(R.id.imageButton);

        name.setText(myData.get(position).getName());
        tel.setText(myData.get(position).getTel());
        address.setText(myData.get(position).getAddress());


        btnStar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  if(checkName(cursor,text1_name) {
                text2 = myData.get(position).getName();

                if (btnStar != null) {

                    btnStar.setSelected(!btnStar.isSelected());


                  /*  if (myBookList.size() == 0) {
                        new AlertDialog.Builder(mContext)
                                .setMessage("즐겨찾기에 추가되었습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Toast.makeText(mContext.getApplicationContext(), "확인 누름", Toast.LENGTH_SHORT).show();
                                        //name , tel , address 의 정보를 data table 에 저장

                                        text1_name = myData.get(position).name;
                                        text2_tel = myData.get(position).tel;
                                        text3_address = myData.get(position).address;

                                        putMyBookData();
                                    }

                                }).show();
                    }

                   */


                    //  else if(myBookList.size() > 0){


                    //   MyBook m = myBookList.get().getName();


                   //if (check().equals(text2)) {
                    DataBaseHelper databaseHelper = new DataBaseHelper(mContext.getApplicationContext());
                    databaseHelper.OpenDatabaseFile();

                    myBookList = new ArrayList<MyBook>(databaseHelper.getMyBookData());

                    for (int i = 0; i < myBookList.size(); i++) {
                        for (int j = 0; j < i; j++) {
                            if (myBookList.get(i).getName().equals(text2)) {  // 중복 검사
                                Toast.makeText(mContext.getApplicationContext(), "이미 저장된 정보입니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                        databaseHelper.close();

                        new AlertDialog.Builder(mContext)
                                .setMessage("")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Toast.makeText(mContext.getApplicationContext(), "확인 누름", Toast.LENGTH_SHORT).show();
                                        //name , tel , address 의 정보를 data table 에 저장

                                        text1_name = myData.get(position).name;
                                        text2_tel = myData.get(position).tel;
                                        text3_address = myData.get(position).address;

                                        putMyBookData();
                                    }

                                }).show();
                    } else {
                        Toast.makeText(mContext.getApplicationContext(), "이미 저장된 정보입니다.", Toast.LENGTH_SHORT).show();
                    }


                    /*
                    for ( int i=0; i < myBookList.size(); i++) { //mybooklist 저장된 name 값이 순서대로 모두 출력됨
                        MyBook m = myBookList.get(i);
                        Toast.makeText(mContext.getApplicationContext(), myBookList.get(i).getName(), Toast.LENGTH_SHORT).show();
                    }

                     */


            }
        });


        // view.setFocusable(false);

        return view;
    }


    public class ViewHolder {
        TextView name, tel, address;
        Button btnStar;

    }

    public List<MyBook> putMyBookData() {
        DataBaseHelper databaseHelper = new DataBaseHelper(mContext.getApplicationContext());
        databaseHelper.OpenDatabaseFile();

        myBookList = new ArrayList<MyBook>(databaseHelper.putMyBookData());

        myBookList.add(new MyBook());
      /*  else
        {
            new AlertDialog.Builder(mContext)
                    .setMessage("이미 저장된 장소입니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }

                    })   .show();
        }

       */
        databaseHelper.close();
        return myBookList;
    }


    /*    public boolean checkName(){
       DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext.getApplicationContext());
       dataBaseHelper.OpenDatabaseFile();
       SQLiteDatabase mDb = dataBaseHelper.getWritableDatabase();

       // myBookList 에 저장된 정보에 있는지


        String sql = "select name from myBook where name = " + "\'" + text2 + "\'" ;
        Cursor cursor = mDb.rawQuery(sql, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        mDb.close();

        if(cursorCount == 0) {
            return false;
        }
        return true;

     */

    /*
            for ( int i=0; i < myBookList.size(); i++){
                MyBook m = myBookList.get(i);
                 if (m.getName().indexOf(text2)!= 1) {
                    Toast.makeText(mContext.getApplicationContext(), "이미 저장된 정보입니다.", Toast.LENGTH_SHORT).show();
                }


            }

     */
    public void check() {
        DataBaseHelper databaseHelper = new DataBaseHelper(mContext.getApplicationContext());
        databaseHelper.OpenDatabaseFile();

        myBookList = new ArrayList<MyBook>(databaseHelper.getMyBookData());

        for (int i = 0; i < myBookList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (myBookList.get(i).equals(myBookList.get(j))) {  // 중복 검사
                    Toast.makeText(mContext.getApplicationContext(), "이미 저장된 정보입니다.", Toast.LENGTH_SHORT).show();
                }
            }
            databaseHelper.close();
        }
    }
}














