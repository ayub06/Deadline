package com.blogspot.onayub.sqltrial;

/**
 * VIEW PART
 * Activity : activity_display_contact.xml
 * Show if : Menekan salah satu Task
 * Description : Bagian ini hanya menampilkan kontak saja, tidak mengedit
 * Available menu : Tersedia menu DELETE dan EDIT
 */


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayDeadline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_deadline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int from_Where_I_Am_Coming = 0;
        DBHelper mydb ;
        TextView name ;
        int id_To_Update=0;
        name  = (TextView)  findViewById(R. id. TaskName);
        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        final int Value = extras.getInt("id");

        if(extras !=null)
        {

            if(Value>0){

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs. moveToFirst();

                String nam  =rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_NAME));
                String phon =rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_PHONE));
                String emai =rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_EMAIL));
                String stree=rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_STREET));
                String plac =rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_CITY));

                if (!rs. isClosed()){
                    rs. close();
                }

                String tampilkan = nam + "\n" + phon + "\n" + emai + "\n" + stree + "\n" + plac;
                name. setText((CharSequence) tampilkan);
            }
        }

        //Mengedit Deadline
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int id_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", Value);
                Intent intent = new Intent(getApplicationContext(), NewEditDeadline.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

    }
}

