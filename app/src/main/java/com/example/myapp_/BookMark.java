package com.example.myapp_;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;

import Database.BMAdapter;
import Database.DataBaseHelper;
import Database.MyBook;

public class BookMark extends AppCompatActivity {

    Context mContext;
    BMAdapter bmAdapter;
    ArrayList<MyBook> myBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);

        mContext = this;

        this.initLoadMyBook();

        ListView listView = (ListView) findViewById(R.id.BM_listview);
        final BMAdapter bmAdapter = new BMAdapter(this, myBooks);
        listView.setAdapter(bmAdapter);

    }

    public List<MyBook> initLoadMyBook() {
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.OpenDatabaseFile();

        myBooks = new ArrayList<MyBook>(databaseHelper.getMyBookData());

        myBooks.add(new MyBook());

        databaseHelper.close();
        return myBooks;

    }
    public void Back(View view) { //액티비티간 데이터 쌓이는 것에 대한,,,
        super.onBackPressed();
        // stopPlay(); //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
        Intent intent = new Intent(getApplicationContext(), Main2.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료

    }

}




