package com.lite.chatapp.fragments;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lite.chatapp.R;
import com.lite.chatapp.adapter.UserListAdapter;
import com.lite.chatapp.db.MessageRepo;
import com.lite.chatapp.presenter.Interactor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saket on 24,July,2019
 */
public class UserListFragment extends Fragment{

    private UserListAdapter mUserListAdapter;
    private ListView mUserListView;
    private List<String> usersList;
    private Interactor.SendUserList mSendUserList;
    private Button mButton;

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Interactor.SendUserList){      // context instanceof YourActivity
            this.mSendUserList = (Interactor.SendUserList) context; // = (YourActivity) context
        } else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersList = new ArrayList<>();
        getAllUsers();
        mUserListAdapter = new UserListAdapter(usersList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.userlistview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mUserListView = (ListView)view.findViewById(R.id.userlist);
        mButton = (Button)view.findViewById(R.id.newbutton);
        mUserListView.setAdapter(mUserListAdapter);
        mUserListAdapter.notifyDataSetChanged();
        mUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSendUserList.sendUser(usersList.get(position));
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSendUserList.sendUser(null);
            }
        });
    }

    private void getAllUsers(){
        MessageRepo messageRepo = new MessageRepo(getContext());
        messageRepo.getUsers("Self").observeForever(new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> users) {
                for(String user : users){
                    usersList.add(user);
                }
            }
        });
    }

}
