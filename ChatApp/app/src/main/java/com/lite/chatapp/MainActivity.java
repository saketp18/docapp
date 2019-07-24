package com.lite.chatapp;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lite.chatapp.adapter.MessageAdapter;
import com.lite.chatapp.fragments.ChatFragment;
import com.lite.chatapp.fragments.UserListFragment;
import com.lite.chatapp.models.Message;
import com.lite.chatapp.models.MessageBot;
import com.lite.chatapp.presenter.Interactor;
import com.lite.chatapp.presenter.MainActivityPresenter;
import com.lite.chatapp.core.Utils;
import com.lite.chatapp.models.MessageUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saket on 24,July,2019
 */

public class MainActivity extends AppCompatActivity implements Interactor.ChatResponse, LifecycleOwner, Interactor.SendUserList {


    private MainActivityPresenter mMainActivityPresenter;
    private List<Interactor.DBUpdateListener> mListeners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListeners = new ArrayList<>();
        mMainActivityPresenter = new MainActivityPresenter(this);
        UserListFragment firstFragment = new UserListFragment();
        getSupportFragmentManager().beginTransaction()
        .add(R.id.container, firstFragment)
        .commit();
    }

    @Override
    public void onSuccessData(MessageBot message, int type) {

    }

    @Override
    public void onFailureData(){
        Toast.makeText(this, "Please try again...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendUser(String user) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("user", user);
        chatFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, chatFragment) // replace flContainer
                .addToBackStack(null)
                .commit();
    }

    public synchronized void registerDataUpdateListener(Interactor.DBUpdateListener listener) {
        mListeners.add(listener);
    }

    public synchronized void unregisterDataUpdateListener(Interactor.DBUpdateListener listener) {
        mListeners.remove(listener);
    }

    public synchronized void dataUpdated() {
        for (Interactor.DBUpdateListener listener : mListeners) {
            listener.onDataUpdate();
        }
    }
}
