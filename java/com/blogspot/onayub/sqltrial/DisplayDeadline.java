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
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDeadline extends AppCompatActivity {

    DBHelper mydb ;
    /*Bundle extras = getIntent().getExtras();
    int Value = extras.getInt("id");*/

    TextView name ;
    TextView phone ;
    TextView email ;
   /* TextView street ;
    TextView place ;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_deadline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        int Value = extras.getInt("id");
        mydb = new DBHelper(this);
        name  = (TextView)  findViewById(R. id. TaskName);

/*
        if(extras !=null)
        {
*/

            if(Value>0){
                Cursor rs = mydb.getData(Value);
                rs. moveToFirst();

                String nam  =rs. getString(rs. getColumnIndex(DBHelper.DEADLINE_COLUMN_TITLE));
                String phon =rs. getString(rs. getColumnIndex(DBHelper.DEADLINE_COLUMN_DATE));
                String emai =rs. getString(rs. getColumnIndex(DBHelper.DEADLINE_COLUMN_DETAIL));
                /*String stree=rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_STREET));
                String plac =rs. getString(rs. getColumnIndex(DBHelper. CONTACTS_COLUMN_CITY));*/

                if (!rs. isClosed()){
                    rs. close();
                }

                //Mencoba menampilkan tittle ke bar
                CollapsingToolbarLayout tb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                tb.setTitle((CharSequence) nam);

                //Tampilkan konten
                String tampilkan = "\t Tittle \t\t\t\t"+nam
                        + "\n\n \t Date \t\t\t\t" + phon
                        + "\n\n\n \t Detail \n\t\t"+ emai
                        /*+ "\n\n \t Detail \t\t\t\t" + stree
                        + "\n\n \t Lecture \t\t\t" + plac*/;
                name. setText((CharSequence) tampilkan);
            }else{
                Toast.makeText(getApplicationContext(), "Error brow",
                        Toast.LENGTH_SHORT).show();
            }
        /*}*/

        //Mengedit Deadline
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = getIntent().getExtras();
                int Value = extras.getInt("id");

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", Value);
                Intent intent = new Intent(getApplicationContext(), NewEditDeadline.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_deadline, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
/*

        AlertDialog. Builder builder = new AlertDialog. Builder(this);
        builder.setMessage(R. string. deleteContact)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mydb.deleteContact(id_To_Update);

                        Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
                        startActivity(intent);
                    }
                })
                . setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog d = builder. create();
        d.setTitle("Are you sure?");
        d.show();*/
        return true;

    }
}

