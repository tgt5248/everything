package com.hyunsoo.every;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import java.util.Calendar;

public class setAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Calendar c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        Button buttonstart = (Button) findViewById(R.id.button);
        Button buttonCancelAlarm = (Button)findViewById(R.id.button2);

        //원하는 시간 체크
        buttonstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });

        buttonCancelAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                cancelAlarm();
            }
        });

        //홈버튼
        ImageButton homebutton = (ImageButton)findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(setAlarm.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //상품추가버튼
        ImageButton addbutton = (ImageButton)findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(setAlarm.this, additem.class);
                startActivity(intent);
            }
        });

        //커뮤니티버튼
        ImageView communitybtn=(ImageButton)findViewById(R.id.communitybtn);
        communitybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(setAlarm.this, community.class);
                startActivity(intent);
            }
        });

        //알람버튼
        ImageView alrambtn=(ImageButton)findViewById(R.id.alarmbtn);
        alrambtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(setAlarm.this, setAlarm.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    //입력받은 시간으로 알람켜기
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        startAlarm(c);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if(c.before((Calendar.getInstance()))){
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        Toast.makeText(setAlarm.this, "알람이 설정되었습니다.", Toast.LENGTH_SHORT).show();

    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

}