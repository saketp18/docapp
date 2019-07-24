package com.lite.chatapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lite.chatapp.R;
import com.lite.chatapp.presenter.Interactor;

import java.util.List;

/**
 * Created by Saket on 24,July,2019
 */
public class UserListAdapter extends BaseAdapter {

    private List<String> userList;
    private Interactor.UserFragList mUserFragList;

    public UserListAdapter(List<String> userList, Interactor.UserFragList userFragList) {
        this.userList = userList;
        this.mUserFragList = userFragList;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        Log.d("Saket", "hello there");
        LayoutInflater layoutInflater = null;
        if(convertview == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
            convertview = layoutInflater.inflate(R.layout.userlist_item, null, false);

        }
        final TextView textView = (TextView)convertview.findViewById(R.id.user_item);
        textView.setText(userList.get(position));
        return convertview;
    }
}
