package com.jbgomond.resultatsensicaen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void buttonOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.news_btn:
                break;
            case R.id.marks_btn:
                intent = new Intent(this, TrainingsActivity.class);
                startActivity(intent);
                break;
            case R.id.mails_btn:
                intent = new Intent(this, MailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
