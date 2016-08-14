package com.jbgomond.resultatsensicaen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.mail.Message;

public class MailsActivity extends AppCompatActivity {

    private Message[] mails = new Message[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mails_view);

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

            MailsAdapter adapter = new MailsAdapter(mails);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this));

            // Gets mails list
            new MailsTask(adapter).execute();
        }
    }
}
