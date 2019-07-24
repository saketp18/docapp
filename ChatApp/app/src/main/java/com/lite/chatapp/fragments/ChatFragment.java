package com.lite.chatapp.fragments;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.lite.chatapp.MainActivity;
import com.lite.chatapp.R;
import com.lite.chatapp.adapter.MessageAdapter;
import com.lite.chatapp.core.RetrofitClient;
import com.lite.chatapp.core.Utils;
import com.lite.chatapp.db.MessageRepo;
import com.lite.chatapp.models.Message;
import com.lite.chatapp.models.MessageBot;
import com.lite.chatapp.models.MessageDB;
import com.lite.chatapp.models.MessageUI;
import com.lite.chatapp.presenter.GetMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Saket on 24,July,2019
 */
public class ChatFragment extends Fragment {

    private List<MessageUI> messages;
    private Button mSendButton;
    private EditText mMessageText;
    private RecyclerView mMessageList;
    private MessageAdapter mMessageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String user;

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.sendButton) {
                callChatBot(mMessageText.getText().toString());
                selfdata(mMessageText.getText().toString());
                mMessageText.getText().clear();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            user = getArguments().getString("user", null);
        }
        messages = new ArrayList<>();
        getAllMessages();
        mMessageAdapter = new MessageAdapter(getActivity(), messages);

    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chatlistfragmentview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSendButton = (Button)view.findViewById(R.id.sendButton);
        mMessageText = (EditText) view.findViewById(R.id.message);
        mMessageText.requestFocus();
        InputMethodManager imm = (InputMethodManager)((MainActivity)getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mMessageText, InputMethodManager.SHOW_IMPLICIT);
        mMessageList = (RecyclerView)view.findViewById(R.id.messagelist);
        mSendButton.setOnClickListener(mClickListener);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mMessageList.setLayoutManager(mLayoutManager);
        mMessageList.setAdapter(mMessageAdapter);
        mMessageAdapter.notifyDataSetChanged();
    }

    private void getAllMessages() {
        if(user == null)
            return;
        MessageRepo messageRepo = new MessageRepo(getContext());
        messageRepo.getMessage(user,"Self").observe(((MainActivity)getActivity()),new Observer<List<MessageDB>>() {
            @Override
            public void onChanged(@Nullable List<MessageDB> messageDBS) {
                Log.d("Saket",messageDBS.toString());
                for (MessageDB messageDB : messageDBS) {
                    MessageBot messageBot = new MessageBot(messageDB.getChatBotName(), messageDB.getChatbotId(), messageDB.getMessage(), null);
                    int senderType = messageDB.getSenderType();
                    MessageUI messageUI = new MessageUI(messageBot, senderType);
                    messages.add(messageUI);
                }
            }
        });
    }

    private void selfdata(String message){
        MessageBot messageBot = new MessageBot("Self", "12345", message, null);
        MessageUI messageUI = new MessageUI(messageBot, Utils.SENDER.SELF.ordinal());
        mMessageAdapter.add(messageUI);
        mMessageAdapter.notifyDataSetChanged();
        mMessageList.scrollToPosition(mMessageAdapter.getItemCount() - 1);
    }

    public void callChatBot(String message) {
        Map<String, String> data = new HashMap<>();
        data.put("apiKey", "6nt5d1nJHkqbkphe");
        data.put("externalID", "chirag1");
        data.put("chatBotID", "63906");
        data.put("message", message);
        GetMessage service = RetrofitClient.getRetrofitInstance().create(GetMessage.class);
        Call<Message> call = service.getMessage(data);
        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response != null) {
                    MessageUI messageUI = new MessageUI(response.body().getMessage(), Utils.SENDER.BOT.ordinal());
                    mMessageAdapter.add(messageUI);
                    mMessageAdapter.notifyDataSetChanged();
                    mMessageList.scrollToPosition(mMessageAdapter.getItemCount() - 1);
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
