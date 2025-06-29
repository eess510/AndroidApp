package com.example.myapp_;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Database.MyData;


public class Main4 extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    public static Context mContext;

    private Geocoder geocoder;

    String str;
    ArrayList<MyData> DataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        ImageButton imagebtn2 = (ImageButton)findViewById(R.id.imagebtn2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void mmBack(View view) { //액티비티간 데이터 쌓이는 것에 대한,,,

        super.onBackPressed();
        // stopPlay(); //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.mMap = googleMap;
        geocoder = new Geocoder(this);

        this.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        String str = getIntent().getStringExtra("myAddress");
        String mName = getIntent().getStringExtra("myName");

        List<Address> list = null;

        try {
            list = geocoder.getFromLocationName(
                    str,
                    10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getApplicationContext(), "해당되는 주소 정보를 찾지 못했습니다.", Toast.LENGTH_LONG).show();
            } else {
                Address addr = list.get(0);
                double Lat = addr.getLatitude();
                double Lng = addr.getLongitude();


                LatLng position = new LatLng(Lat, Lng);

                this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));


                MarkerOptions mOptions = new MarkerOptions();
                mOptions.position(position);
                mOptions.title(mName);
                mOptions.snippet(str);
                mMap.addMarker(mOptions).showInfoWindow();


            }

         //   mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);


        }

    }

    }



    /* 여기부터
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);


        String str = getIntent().getStringExtra("myName");

        List<Address> list = null;

        try {
            list = geocoder.getFromLocationName(
                    str,
                    10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getApplicationContext(), "해당되는 주소 정보를 찾지 못했습니다.", Toast.LENGTH_LONG).show();
            } else {
                Address addr = list.get(0);
                double Lat = addr.getLatitude();
                double Lng = addr.getLongitude();


                LatLng position = new LatLng(Lat, Lng);
            }

            MarkerOptions mOptions = new MarkerOptions();
            mOptions.title("마커 좌표");
        }
    }
} 여기까지 */



/*
    public List<MyData> initNameDBTESTDatabase() {

        DataBaseHelper databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.OpenDatabaseFile();


        DataList = new ArrayList<MyData>(databaseHelper.getTableData3());
        DataList.add(new MyData());


        databaseHelper.close();
        return DataList;


    }*/





/*
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        final Geocoder mgeocoder = new Geocoder(this);

        mMap = googleMap;

        List<Address> list = null;

        try{
            list = mgeocoder.getFromLocationName(
                    str,
                    10);
        }catch (IOException e){
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getApplicationContext(),"sorry ",Toast.LENGTH_LONG).show();
            }
            else
            {
                Address addr = list.get(0);
                double lat = addr.getLatitude() ;
                double lng = addr.getLongitude();
                String sss = String.format("geo:%f,%f", lat, lng);



                LatLng re = new LatLng(lat,lng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(re);
                markerOptions.title("서울");
                markerOptions.snippet("한국의 수도");
                mMap.addMarker(markerOptions);

               /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sss));
                startActivity(intent);


            }

        }*/


