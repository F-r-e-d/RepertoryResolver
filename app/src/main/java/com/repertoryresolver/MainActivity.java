package com.repertoryresolver;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.example.logan_gonet.resolver_repertoryapp.R;

public class MainActivity extends AppCompatActivity {

    private String URL = "content://com.repertoryprovider.Repertory/friends";
    private Uri friends = Uri.parse(URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void deleteAllRepertory (View view) {
// delete all the records and the table of the database provider
        int count = getContentResolver().delete(friends, null, null);
        String countNum = "Repertory App: "+ count +" records are deleted.";
        Toast.makeText(getBaseContext(),
                countNum, Toast.LENGTH_LONG).show();

    }
    public void addRepertory(View view) {
// Add a new repertory record
        ContentValues values = new ContentValues();
        String nameTxt = ((EditText)findViewById(R.id.name)).getText().toString();
        values.put("name", nameTxt);
        values.put("number",
                ((EditText)findViewById(R.id.number)).getText().toString());
        Uri uri = getContentResolver().insert(friends, values);
        Toast.makeText(getBaseContext(),
                "Contact : " + nameTxt + " inserted!", Toast.
                        LENGTH_LONG).show();

        ((EditText)findViewById(R.id.name)).setText("");
        ((EditText)findViewById(R.id.number)).setText("");
    }

    public void showAllRepertory(View view) {
// Show all the repertorys sorted by friend’s name
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = " Contacts :";
        if (c == null || !c.moveToFirst()) {
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).
                    show();
        }else{
            do{

                result = result + "\n" + c.getString(c.getColumnIndex("name")) + "\n"+
                        " with id " + c.getString(c.getColumnIndex("id")) + "\n"+
                        " has number: " + c.getString(c.getColumnIndex("number"));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}
