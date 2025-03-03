package com.example.practicemain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    ListViewActivity activity;
    List<UserData> userDataList;

    public CustomListAdapter(ListViewActivity activity, List<UserData> userDataList) {
        this.activity = activity;
        this.userDataList = userDataList;
    }

    @Override
    public int getCount() {
        return userDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       return initView(i,view,viewGroup);
    }

        public View initView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
            TextView name = view.findViewById(R.id.name);
            TextView email = view.findViewById(R.id.email);
            TextView height = view.findViewById(R.id.height);
            TextView age = view.findViewById(R.id.age);

            UserData userData = userDataList.get(i);
            name.setText(userData.getName());
            email.setText(userData.getEmail());
            height.setText(String.valueOf(userData.getHeight()));
            age.setText(String.valueOf(userData.getAge()));

            return view;
        } else {
            return view;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
