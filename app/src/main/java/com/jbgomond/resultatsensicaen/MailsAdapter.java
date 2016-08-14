package com.jbgomond.resultatsensicaen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public class MailsAdapter extends RecyclerView.Adapter<MailsAdapter.ViewHolder> {
    private Message[] messages;

    public MailsAdapter(Message[] messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mails_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            SimpleDateFormat dateformat;

            // Si reçu aujourd'hui : 10:15
            // Sinon : 10 juin
            if (DateUtils.isToday(messages[position].getSentDate().getTime())) {
                dateformat = new SimpleDateFormat("HH:mm", Locale.FRANCE);
            } else {
                dateformat = new SimpleDateFormat("dd MMM", Locale.FRANCE);
            }

            //holder.trainingId = trainings.get(position).getId();
            holder.mailFrom.setText(((InternetAddress) messages[position].getFrom()[0]).getPersonal());
            holder.mailTitle.setText(messages[position].getSubject());
            holder.mailDate.setText(dateformat.format(messages[position].getSentDate()));
            holder.mailShortContent.setText(messages[position].getSubject()); //TODO afficher le début du contenu
            holder.messages = messages;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Get the size of items in adapter
     *
     * @return the size of items in adapter
     */
    @Override
    public int getItemCount() {
        return messages.length;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Message[] messages;
        private int mailId;
        private TextView mailFrom;
        private TextView mailTitle;
        private TextView mailDate;
        private TextView mailShortContent;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            mailFrom = (TextView) itemView.findViewById(R.id.mail_from);
            mailTitle = (TextView) itemView.findViewById(R.id.mail_title);
            mailDate = (TextView) itemView.findViewById(R.id.mail_date);
            mailShortContent = (TextView) itemView.findViewById(R.id.mail_short_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*Intent intent = new Intent(context, SemestersActivity.class);
            intent.putExtra("SEMESTERS", messages[mailId]).getSemesters());
            context.startActivity(intent);*/
        }
    }
}