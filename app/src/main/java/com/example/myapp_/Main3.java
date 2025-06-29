package com.example.myapp_;    //DBTEST LIST 출력

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;



import java.util.ArrayList;
import java.util.List;

import Database.DBAdapter;
import Database.DataBaseHelper;
import Database.MyData;

public class Main3 extends AppCompatActivity {

    public static Context myContext;
    public int myIndex;
    public String myItem;
    public String myName;

    Context mContext;


    private ActivityResultLauncher<Intent> resultLauncher;

    ArrayList<MyData> DataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);

        myIndex = 0;

        myContext = this;
        int prePosition = getIntent().getIntExtra("position", 0);

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent mIntent = result.getData();
                            //           int position = myIntent.getIntExtra("position", 1);
                            // String position = myIntent.getStringExtra("position");
                        }
                    }
                });


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.location, R.layout.custom_spinner_list_item);
        spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        spinner1.setAdapter(spinnerAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    myItem = (String) spinner1.getSelectedItem();
                    initLocationDBTESTDatabase();

                    ListView listView = (ListView) findViewById(R.id.Alistview);
                    DBAdapter dbAdapter = new DBAdapter(Main3.this, DataList);

                    listView.setAdapter(dbAdapter);

                    //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter MapAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ListView Alistview = (ListView) findViewById(R.id.Alistview);

        Alistview.setAdapter(MapAdapter);


        Alistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int arg0, long id) {


                Intent mIntent = new Intent(getApplicationContext(), Main4.class);
                mIntent.putExtra("myAddress", DataList.get(arg0).address);
                mIntent.putExtra("myName", DataList.get(arg0).name);

              /* String sss = "";
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sss)); */
                resultLauncher.launch(mIntent);

            }
        });


        if (prePosition != 0) {

            //  for (prePosition = 1; prePosition < 19; prePosition++) {

            myIndex = prePosition++;

            this.initLoadDBTESTDatabase();

            ListView listView = (ListView) findViewById(R.id.Alistview);
            final DBAdapter dbAdapter = new DBAdapter(this, DataList);

            listView.setAdapter(dbAdapter);


            //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } else {  //prePosition ==0
            myIndex = 0;

            this.initLoadDBTESTDatabase();

            ListView listView = (ListView) findViewById(R.id.Alistview);
            final DBAdapter dbAdapter = new DBAdapter(this, DataList);

            listView.setAdapter(dbAdapter);


        }

    }


    public List<MyData> initLoadDBTESTDatabase() {
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.OpenDatabaseFile();

        DataList = new ArrayList<MyData>(databaseHelper.getTableData());

        DataList.add(new MyData());

        databaseHelper.close();
        return DataList;
    }


    public List<MyData> initLocationDBTESTDatabase() {
        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.OpenDatabaseFile();

        DataList = new ArrayList<MyData>(databaseHelper.getTabaleData2());

        DataList.add(new MyData());

        databaseHelper.close();
        return DataList;
    }


    public void mNext3(View view) {
        Intent intent = new Intent(getApplicationContext(), BookMark.class);
        startActivity(intent);

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

