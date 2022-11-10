package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentsListActivity extends AppCompatActivity {

    public static final String GROUP_NUMBER = "groupnumber";
    private float textSize = 0;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        Intent intent = getIntent();
        int grpNumber = intent.getIntExtra(GROUP_NUMBER, 0);

        ListView listView = (ListView) findViewById(R.id.studentsList);
        SimpleCursorAdapter adapter = getDataFromDB(grpNumber);
        if (adapter != null) {
            listView.setAdapter(adapter);
        }
//        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Student.getStudents(grpNumber));
//
//        listView.setAdapter(adapter);
    }

    private SimpleCursorAdapter getDataFromDB(int groupId) {
        SimpleCursorAdapter listAdapter = null;

        SQLiteOpenHelper sqLiteHelper = new StudentsDatabaseHelper(this);
        try {
            db = sqLiteHelper.getReadableDatabase();
            cursor = db.rawQuery("select s.id _id, name, number\n" +
                    "from students s inner join groups g on s.group_id = g.id\n" +
                    "where g.id = 7", new String[] {Integer.toString(groupId)}

            );
            listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] {"name"}, new int[]{android.R.id.text1},0);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable" , Toast.LENGTH_SHORT);
            toast.show();
        }
        return listAdapter;
    }

            @Override
            protected void onDestroy() {
                super.onDestroy();
                cursor.close();
                db.close();
            }



//        String txtStudents = "";
//
//        for(Student s : Student.getStudents(grpNumber)) {
//            txtStudents += s.getName() + "\n";
//        }
//
//        TextView textView = (TextView) findViewById(R.id.text);
//        textView.setText(txtStudents);
//
//        textSize = textView.getTextSize();
//
//        if (savedInstanceState != null) {
//            textSize = savedInstanceState.getFloat("textSize");
//        }




//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putFloat("textSize", textSize);
//    }
    public void onBtnClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNumb = (String)  spinner.getSelectedItem();

        Intent intent = new Intent(this, StudentsListActivity.class);
        intent.putExtra(StudentsListActivity.GROUP_NUMBER, grpNumb);
        startActivity(intent);


    }
    public void onSendBtnClick(View view) {
        TextView textView = (TextView) findViewById(R.id.text);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Список студентів");
        startActivity(intent);
    }
//    public void onPlusBtnClick(View view) {
//         textSize = textSize * 1.1f;
//         TextView textView = findViewById(R.id.text);
//         textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//
//
//    }

}