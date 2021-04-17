package com.example.dashfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class confirmOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        
        fetchItem();
    }

    private void fetchItem() {
        ArrayList<String> item=new ArrayList<>();

        ContentResolver resolver=getContentResolver();

        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] selection= null;
        String[] projection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] selectionArgs=null;
        String sortOrder=null;

        Cursor cursor=resolver.query(uri,selection, String.valueOf(projection),selectionArgs,sortOrder);

        while (cursor.moveToNext()){
            String name= cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            item.add(name+"\n"+ num);


        }

        ((ListView)findViewById(R.id.name)).setAdapter(new ArrayAdapter<>(this,R.layout.order_item));
    }
}