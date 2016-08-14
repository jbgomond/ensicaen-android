package com.jbgomond.resultatsensicaen;

import android.os.AsyncTask;
import android.widget.ListView;
import java.util.Properties;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class MailsTask extends AsyncTask<String, String, Message[]> {
    MailsAdapter adapter;

    public MailsTask(MailsAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Message[] doInBackground(String... params) {
        Message[] messages = new Message[0];
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("zimbra.ensicaen.fr", "XXX", "XXX");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            System.out.println("VOUS AVEZ " + inbox.getMessageCount() + " MESSAGES");
            messages = inbox.getMessages(inbox.getMessageCount() - 10, inbox.getMessageCount());

            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add("X-mailer");
            inbox.fetch(messages, fp);
        } catch (Exception mex) {
            System.out.println("ACCESS ERROR");
            mex.printStackTrace();
        }

        return messages;
    }

    @Override
    protected void onPostExecute(Message[] messages) {
        adapter.setMessages(messages);
        adapter.notifyDataSetChanged();
    }
}
