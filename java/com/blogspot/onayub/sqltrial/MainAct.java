package com.blogspot.onayub.sqltrial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj ;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Daftar Deadline yang telah dibuat
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllCotacts();
        ArrayAdapter arrayAdapter=new
                ArrayAdapter(this,R.layout.list_style, R.id.title_label,  array_list);

        obj = (ListView) findViewById(R. id. listView1);
        obj.setDividerHeight(8);
        obj.setAdapter(arrayAdapter);


        //Coba LongClick
        obj.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Long Click!!!!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //Memilih salah satu deadline
        obj . setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2+1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new Intent(getApplicationContext(), DisplayDeadline.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        //Menambah Deadline
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dataBundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), NewEditDeadline. class);
                intent. putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater(). inflate(R. menu. menu_main,  menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        super. onOptionsItemSelected(item);
        switch(item. getItemId())
        {
            case R.id. item1: Bundle dataBundle = new Bundle();
                dataBundle. putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(), NewEditDeadline. class);
                intent. putExtras(dataBundle);
                startActivity(intent);
                return true;
            default:
                return super. onOptionsItemSelected(item);
        }
    }*/

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}