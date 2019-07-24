package com.lite.chatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lite.chatapp.adapter.MessageAdapter;
import com.lite.chatapp.presenter.Interactor;
import com.lite.chatapp.presenter.MainActivityPresenter;
import com.lite.chatapp.core.Utils;
import com.lite.chatapp.models.UIMessage;

public class MainActivity extends AppCompatActivity implements Interactor.ChatResponse {

    private Button mSendButton;
    private EditText mMessageText;
    private RecyclerView mMessageList;
    private MessageAdapter mMessageAdapter;
    private MainActivityPresenter mMainActivityPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.sendButton) {
                mMainActivityPresenter.callChatBot(mMessageText.getText().toString());
                onSuccessData(mMessageText.getText().toString(), Utils.SENDER.SELF.ordinal());
                mMessageText.getText().clear();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivityPresenter = new MainActivityPresenter(this);
        mSendButton = findViewById(R.id.sendButton);
        mMessageText = findViewById(R.id.message);
        mMessageList = findViewById(R.id.messagelist);
        mSendButton.setOnClickListener(mClickListener);
        mLayoutManager = new LinearLayoutManager(this);
        mMessageList.setLayoutManager(mLayoutManager);
        mMessageAdapter = new MessageAdapter();
        mMessageList.setAdapter(mMessageAdapter);


    }

    @Override
    public void onSuccessData(String message, int type) {
        UIMessage uiMessage = new UIMessage(message, type);
        mMessageAdapter.add(uiMessage);
        mMessageAdapter.notifyDataSetChanged();
        mMessageList.scrollToPosition(mMessageAdapter.getItemCount() - 1);
    }

    @Override
    public void onFailureData(){
        Toast.makeText(this, "Please try again...", Toast.LENGTH_SHORT).show();
    }

}
