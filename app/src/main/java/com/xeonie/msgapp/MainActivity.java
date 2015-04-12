package com.xeonie.msgapp;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readMessages();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void readMessages(){
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(c);
        String[] body = new String[c.getCount()];
        String[] sender = new String[c.getCount()];
        Long[] date = new Long[c.getCount()];

        String[] names = c.getColumnNames();
        int count = 0;
        if(c.moveToFirst()){
            for(int i=0;i<c.getCount();i++){
                //if(c.getString(c.getColumnIndexOrThrow("address")).contains("EKARTL")){
                body[i]= c.getString(c.getColumnIndexOrThrow("body"));
                sender[i]=c.getString(c.getColumnIndexOrThrow("address"));
                date[i]=Long.parseLong(c.getString(c.getColumnIndexOrThrow("date")));
                c.moveToNext();
                //}
            }
        }
//        Log.d(LOG_TAG, ">>> " + count);
//        for(String name:body){
//            Log.d(LOG_TAG, ">>>" + name);
//        }
//        Log.d(LOG_TAG, ">>>"+body[1]);
//        Log.d(LOG_TAG, ">>>"+number[1]);
        for(int i = 0; i < body.length; i++){
//            if(number[i].contains("HLTKRT"))
                System.out.println(sender[i]+" == "+ DateFormat.format("EEEE, MMMM dd, yyyy h:mm:ss aa", new Date(date[i]))+" == "+body[i]);
            }
        System.out.println("Done!!!");
    }
}
