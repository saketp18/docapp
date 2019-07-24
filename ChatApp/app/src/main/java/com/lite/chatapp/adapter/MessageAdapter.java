package com.lite.chatapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lite.chatapp.R;
import com.lite.chatapp.core.Utils;
import com.lite.chatapp.db.MessageRepo;
import com.lite.chatapp.models.MessageUI;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private List<MessageUI> messages;
    private Context mContext;

    public MessageAdapter(Context context, List<MessageUI> messages) {
        super();
        this.messages = messages;
        mContext = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View messageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_itemview, parent, false);
        MessageHolder messageHolder = new MessageHolder(messageView);
        return messageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        if (messages.get(position).getType() == Utils.SENDER.BOT.ordinal()) {
            holder.message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        } else
            holder.message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        holder.message.setText(messages.get(position).getmMessage().getChatBotMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        protected TextView message;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.messageitem);
        }
    }

    public void add(MessageUI m) {
        messages.add(m);
        addMessageDB(m);
    }

    public void addMessageDB(MessageUI m) {
        MessageRepo messageRepo = new MessageRepo(mContext);
        String message = m.getmMessage().getChatBotMessage();
        String chatbotId = m.getmMessage().getChatBotId();
        String chatbotname = m.getmMessage().getChatBotName();
        messageRepo.insetMessage(chatbotId, message, m.getType(), chatbotname);
    }
}
