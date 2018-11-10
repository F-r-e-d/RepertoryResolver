package com.repertoryresolver;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "content://com.repertoryprovider.Repertory/friends";
    private Uri friends = Uri.parse(URL);
    private ListView mListView;
    private RepertoryAdapter mRepertoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listView_contacts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void showAllRepertory(View view) {
// Show all the repertorys sorted by friend’s name
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = " Contacts :";
        List<Repertory> listContacts = new ArrayList<>();
        if (c == null || !c.moveToFirst()) {
            setListView(MainActivity.this, listContacts, mListView);
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).show();
        }else{
            do{
                Repertory mRepertory = new Repertory();
                mRepertory.setName(c.getString(c.getColumnIndex("name")));
                mRepertory.setNumber(c.getString(c.getColumnIndex("number")));
                listContacts.add(mRepertory);

            } while (c.moveToNext());
            setListView(MainActivity.this, listContacts, mListView);
        }
    }

    public void setListView(Context context, List list, ListView listView){
        RepertoryAdapter adapter = new RepertoryAdapter(context, list);
        listView.setAdapter(adapter);

    }

}
