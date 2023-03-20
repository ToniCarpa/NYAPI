package com.example.nytbrowser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nytbrowser.R;

public class EndActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textTit;
    TextView textBy;
    TextView textHead;
    TextView textDate;
    TextView textSum;

    String tit,by,head,sum,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        imageView = findViewById(R.id.endImage);
        textTit = findViewById(R.id.detailTextTit);
        textBy = findViewById(R.id.detailTextBy);
        textHead = findViewById(R.id.detailTextHead);
        textDate = findViewById(R.id.detailTextDate);
        textSum = findViewById(R.id.detailTextSum);

        getData();
        setData();
    }

    private void getData(){
        if(getIntent().hasExtra("display_title")){
            tit = getIntent().getStringExtra("display_title");
            head = getIntent().getStringExtra("headline");
            by = getIntent().getStringExtra("byline");
            sum = getIntent().getStringExtra("summary_short");
            date = getIntent().getStringExtra("publication_date");
        }
    }
    private void setData(){
        textTit.setText(tit);
        textBy.setText(by);
        textHead.setText(head);
        textDate.setText(date);
        textSum.setText(sum);

    }
}