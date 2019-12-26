package com.homework1_3.androiddeynenko213;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mChooseStartDate;
    private Button mChooseEndDate;
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private Button mBtnOK;

    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    private String mStart;
    private String mEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        selectStartDate();
        selectEndDate();
        getInfo();

        mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int day, int mounts, int year) {
                mStartDateTxt = day + "-" + mounts + "-" + year;
                mChooseStartDate.setText( getString(R.string.date_start_button_placeholder,
                        mStartDateTxt));
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(day, mounts, year);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int day, int mounts, int year) {
                mEndDateTxt = day + "-" + mounts + "-" + year;
                mChooseEndDate.setText(getString(R.string.date_end_btn_placeholder,mEndDateTxt) );
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(day, mounts, year);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }

    private void getInfo() {
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartDate > mEndDate) {
                    String error = getString(R.string.error);
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText(mStart);
                    mChooseEndDate.setText(mEnd);
                } else {
                    String startTask = getString(R.string.startTask);
                    String endTask = getString(R.string.endTask);
                    Toast.makeText(MainActivity.this, startTask + " " + mStartDateTxt
                            + " " + endTask + " " + mEndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endtDateCalendar);
        mBtnOK = findViewById(R.id.btnOK);

        // Скроем календари при запуске приложения
        mStartDateCalendar.setVisibility(View.GONE);
        mEndDateCalendar.setVisibility(View.GONE);

    }

    private void selectStartDate() {
        mChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setVisibility(View.GONE);
            }
        });
    }

    private void selectEndDate() {
        mChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndDateCalendar.setVisibility(View.VISIBLE);
                mStartDateCalendar.setVisibility(View.GONE);
            }
        });
    }
}
