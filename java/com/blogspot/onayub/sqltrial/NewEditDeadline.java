package com.blogspot.onayub.sqltrial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class NewEditDeadline extends AppCompatActivity {

    public static Button dateButton;
    String taskOrEvent;
    String stringDeadlineDate;
    String descriptionText;
    Date deadlineDate;
    long dDay;
    Date date = new Date();
    Toast errorToast;
    private TextInputLayout inputTaskLayout;
    private EditText inputTask;
    private Button moreLessButton;
    private EditText inputDescription;
    private ImageView descriptionIcon;

    static int year;
    static int month;
    static int day;

    /*----------------------------------------------*/

    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newedit_deadline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputTask = (EditText)findViewById(R.id.input_task);
        dateButton = (Button)findViewById(R.id.date_button);
        moreLessButton = (Button)findViewById(R.id.more_less_button);
        inputDescription = (EditText)findViewById(R.id.input_description);
        descriptionIcon = (ImageView)findViewById(R.id.description_icon);

        /*----------------------------------------------*/

        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        CollapsingToolbarLayout tb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout2);

        //JIKA EDIT DEADLINE
        if(extras !=null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                tb.setTitle((CharSequence)"Edit Deadline");

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                taskOrEvent         = rs.getString(rs.getColumnIndex(DBHelper.DEADLINE_COLUMN_TITLE));
                stringDeadlineDate  = rs.getString(rs.getColumnIndex(DBHelper.DEADLINE_COLUMN_DATE));
                descriptionText     = rs.getString(rs.getColumnIndex(DBHelper.DEADLINE_COLUMN_DETAIL));

                inputTask.setText((CharSequence) taskOrEvent);
                inputTask.setFocusable(true);
                inputTask.setCursorVisible(true);
                inputTask.setClickable(true);

                dateButton.setText((CharSequence) stringDeadlineDate);

                inputDescription.setText((CharSequence) descriptionText);
                inputDescription.setFocusable(true);
                inputDescription.setCursorVisible(true);
                inputDescription.setClickable(true);
            }else {
                tb.setTitle((CharSequence)"New Deadline");
            }
        }



        year = 0;
        month = 0;
        day = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
        dateButton.setText(dateFormat.format(date));

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
                inputTask.setEnabled(false);
                inputTask.setEnabled(true);
            }
        });

        moreLessButton.setText("more");
        inputDescription.setVisibility(View.INVISIBLE);
        descriptionIcon.setVisibility(View.INVISIBLE);

        moreLessButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(moreLessButton.getText().toString() == "more"){
                    inputDescription.setVisibility(View.VISIBLE);
                    descriptionIcon.setVisibility(View.VISIBLE);
                    inputDescription.requestFocus();
                    inputDescription.setEnabled(false);
                    inputDescription.setEnabled(true);
                    moreLessButton.setText("less");

                }
                else if(moreLessButton.getText().toString() == "less"){
                    inputDescription.setVisibility(View.INVISIBLE);
                    descriptionIcon.setVisibility(View.INVISIBLE);
                    moreLessButton.setText("more");
                    inputTask.setEnabled(false);
                    inputTask.setEnabled(true);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R. menu.display_contact, menu);
            }
            else{
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    /*public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Contact:
                *//*Button b = (Button) findViewById(R.id.button);*//*
                FloatingActionButton b = (FloatingActionButton) findViewById(R.id.fab_submit);
                b.setVisibility(View.VISIBLE);

                name. setEnabled(true);
                name. setFocusableInTouchMode(true);
                name. setClickable(true);

                phone. setEnabled(true);
                phone. setFocusableInTouchMode(true);
                phone. setClickable(true);

                email. setEnabled(true);
                email. setFocusableInTouchMode(true);
                email. setClickable(true);

               *//* street. setEnabled(true);
                street. setFocusableInTouchMode(true);
                street. setClickable(true);

                place. setEnabled(true);
                place. setFocusableInTouchMode(true);
                place. setClickable(true);*//*
                return true;
            case R.id.Delete_Contact:
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
                d.show();
                return true;
            default:
                return super. onOptionsItemSelected(item);
        }
    }*/

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            int Value = extras.getInt("id");

            //taskOrEvent = inputTask.getText().toString();
            deadlineDate = getDate(year, month, day);
            dDay = getdDay(date, deadlineDate);

            if (Value > 0) {
                if (inputTask.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Required field is empty",Toast.LENGTH_SHORT).show();
                }
                else if (dDay < 0) {
                    Toast.makeText(getApplicationContext(),"Deadline date has passed",Toast.LENGTH_SHORT).show();
                }
                else{
                if (mydb.updateDeadline(id_To_Update, inputTask.getText().toString(),
                        dateToString(deadlineDate),inputDescription.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), MainAct.class);
                    startActivity(intent);
                    }else {
                    Toast.makeText(getApplicationContext(), "not Updated",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                if (inputTask.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Required field is empty",Toast.LENGTH_SHORT).show();
                }
                else if (dDay < 0) {
                    Toast.makeText(getApplicationContext(),"Deadline date has passed",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (mydb.insertDeadline(inputTask.getText().toString(),
                            dateToString(deadlineDate),inputDescription.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "not inserted",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void submitTask(){
        stringDeadlineDate = dateToString(deadlineDate);
        descriptionText = inputDescription.toString();
        sendData(taskOrEvent, stringDeadlineDate, dDay, descriptionText);
        NavUtils.navigateUpFromSameTask(this);
    }

    private boolean validTask() {
        if (inputTask.getText().toString().trim().isEmpty()) {
            inputTaskLayout.setError(getString(R.string.input_task_error));
        }
        else {
            inputTaskLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void sendData(String task, String deadline, long dDay, String description) {

    }

    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    private long getdDay(Date nowDate, Date pickedDate) {
        long dDay;
        dDay = pickedDate.getTime() - nowDate.getTime();
        dDay = TimeUnit.DAYS.convert(dDay, TimeUnit.MILLISECONDS);
        return dDay;
    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
        dateButton.setText(dateFormat.format(date));
        return dateFormat.toString();
    }
}
