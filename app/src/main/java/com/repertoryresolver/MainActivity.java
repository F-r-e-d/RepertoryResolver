package com.repertoryresolver;


import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL = "content://com.repertoryprovider.Repertory/friends";
    private Uri friends = Uri.parse(URL);
    private ListView mListView;

    private RepertoryAdapter adapter;
    private Repertory mRepertory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listView_contacts);

        mListView.setOnItemClickListener (listener);
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
                mRepertory = new Repertory();
                mRepertory.setName(c.getString(c.getColumnIndex("name")));
                mRepertory.setNumber(c.getString(c.getColumnIndex("number")));
                mRepertory.setId(c.getInt(c.getColumnIndex("id")));
                listContacts.add(mRepertory);

            } while (c.moveToNext());
            setListView(MainActivity.this, listContacts, mListView);
        }
    }

    public void deleteRepertoryByIndex(View view, int id) {

        Uri friend_id = ContentUris.withAppendedId(Uri.parse("content://com.repertoryprovider.Repertory/friends"), id);

        getContentResolver().delete(friend_id, null, null);

    }


    public void setListView(Context context, List list, ListView listView){
        adapter = new RepertoryAdapter(context, list);
        listView.setAdapter(adapter);

    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long position) {
            Uri friends_id = Uri.parse("content://com.repertoryprovider.Repertory/friends/" + view.getTag());

            getContentResolver().delete(friends_id, null, null);
            showAllRepertory(view);

            Toast.makeText(MainActivity.this, "Contact supprimé", Toast.LENGTH_LONG).show();
        }


    };

}
