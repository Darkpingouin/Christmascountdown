package com.darkpingouin.christmascountdown;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private TextView text;
    private TextView f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDownStart();
        text = (TextView) findViewById(R.id.time);

    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    String xmas = year + "-12-25";
                    Date futureDate = dateFormat.parse(xmas);
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        String toto = String.format("X\nMas\n%02d Days\n", days) + String.format("%02d Hours\n", hours) + String.format("%02d Minutes\n", minutes) + String.format("%02d Seconds left", seconds);
                        SpannableString spannable = new SpannableString(toto);
                        spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(116,214,128)), 5, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(116,214,128)), 22, 34, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(69,45,21)), 44, 49, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        text.setText(spannable);
                        //text.setText(toto);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
}
