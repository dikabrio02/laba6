package com.example.student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
        }

//        runTimer();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("seconds", seconds);
    }
    protected void onStart() {
        super.onStart();
        isRunning = true;
    }
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }
    public void onBtnClick(View view) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNumb = (String) spinner.getSelectedItem();

        String txtStudents = "";
        for (Student s: Student.getStudents(grpNumb)) {
            txtStudents += s.getName() + "\n";

        }

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(txtStudents);

        Intent intent = new Intent(this, StudentsListActivity.class);
        intent.putExtra(StudentsListActivity.GROUP_NUMBER, grpNumb);
        startActivity(intent);


    }
//    private void runTimer() {
//        final TextView timeView = (TextView) findViewById(R.id.textView);
//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                int hours = seconds/3600;
//                int minutes = (seconds%3600) / 60;
//                int secs = seconds%60;
//
//                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
//                timeView.setText(time);
//                if (isRunning) {
//                    seconds++;
//                }
//                handler.postDelayed(this, 1000);
//
//            }
//        });
//    }
public void onGrpBtnClick(View view) {
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    String grpNumb = (String) spinner.getSelectedItem();

    Intent intent = new Intent(this, StudentsGroupActivity.class);
    intent.putExtra(StudentsGroupActivity.GROUP_NUMBER, grpNumb);

    startActivity(intent);
}
public void onBtnShowGroupsClick(View view) {
        Intent intent = new Intent(this, GroupsListActivity.class);
        startActivity(intent);
}

}