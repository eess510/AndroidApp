package com.example.myapp_;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class Main2 extends AppCompatActivity {
    public static Context mContext;

    EditText editText;
        private ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        mContext = this;

        ArrayAdapter artistAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.artist, R.layout.simple_layout);
        ListView listView1 = (ListView) findViewById(R.id.listview1);
        editText = (EditText)findViewById(R.id.Search);
        listView1.setAdapter(artistAdapter);
        listView1.setTextFilterEnabled(true);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listView1.setFilterText(editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editText.getText().length() == 0) {
                    listView1.clearTextFilter();
                }

            }
        });
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent myIntent = result.getData();
                 //           int position = myIntent.getIntExtra("position", 1);
                           // String position = myIntent.getStringExtra("position");

                        }
                    }
                });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position, long id) {

                Intent myIntent = new Intent(getApplicationContext(), Main3.class);
                myIntent.putExtra("position", position);
                resultLauncher.launch(myIntent);

            /*
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), Main3.class);

                    startActivity(intent);
                }*/
            }
        });


    }
    public void mBack(View view){ //액티비티간 데이터 쌓이는 것에 대한,,,
        super.onBackPressed();
       // stopPlay(); //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료

    //    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
     //   startActivity(intent);
    }

}
        



