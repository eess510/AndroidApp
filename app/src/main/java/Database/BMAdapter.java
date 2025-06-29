package Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.myapp_.R;

import java.util.ArrayList;

public class BMAdapter extends BaseAdapter {
    Context mContext;

    LayoutInflater layoutInflater;
    ArrayList<MyBook> myBooks;
   // ArrayList<MyBook> myBooks = new ArrayList<MyBook>(); //이게 원래
   //public static List<MyBook> myBooks = new ArrayList<>();
   // private List<MyBook> myBooks; //mList


    public BMAdapter(Context context, ArrayList<MyBook> myBooks) {
       // layoutInflater = LayoutInflater.from(mContext);
        this.mContext = context;
        this.myBooks = myBooks;

    }


    @Override
    public int getCount() { return myBooks.size();}

    @Override
    public MyBook getItem(int position) {
        return myBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mContext = parent.getContext();
        MyBook myBook = myBooks.get(position);

        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.bmitem_list, null);

        TextView name = (TextView) view.findViewById(R.id.name1);
        TextView tel = (TextView) view.findViewById(R.id.tel1);
        TextView address = (TextView) view.findViewById(R.id.address1);


        name.setText(myBooks.get(position).getName());
        tel.setText(myBooks.get(position).getTel());
        address.setText(myBooks.get(position).getAddress());

        return view;

    }
/*
    public static List getBM() {
        SharedPreferences pref = null;
        MyBook myBookMark = new MyBook();

        List mList = new ArrayList<MyBook>();
        List<String> myList = new ArrayList<String>(pref.getStringSet("set", null));


        myBookMark.setName(myList.get(0));
        myBookMark.setAddress(myList.get(1));


        mList.add(new MyBook());
        return mList;
    }

 */

}
