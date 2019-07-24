package com.lite.chatapp;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.lite.chatapp.fragments.ChatFragment;
import com.lite.chatapp.fragments.UserListFragment;
import com.lite.chatapp.presenter.Interactor;

/**
 * Created by Saket on 24,July,2019
 */

public class MainActivity extends AppCompatActivity implements LifecycleOwner, Interactor.SendUserList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserListFragment firstFragment = new UserListFragment();
        getSupportFragmentManager().beginTransaction()
        .add(R.id.container, firstFragment)
        .commit();
    }

    @Override
    public void sendUser(String user) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("user", user);
        chatFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, chatFragment) 
                .addToBackStack(null)
                .commit();
    }

}
