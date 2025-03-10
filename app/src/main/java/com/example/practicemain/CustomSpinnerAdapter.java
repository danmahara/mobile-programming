package com.example.practicemain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<UserData> {

    private final SpinnerActivity activity;
    private final List<UserData> userDataList;

    public CustomSpinnerAdapter(SpinnerActivity activity, List<UserData> userDataList) {
        super(activity, 0, userDataList);
        this.activity = activity;
        this.userDataList = userDataList;

    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View view, @NonNull ViewGroup parent) {
        return initView(i, view, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);

            TextView name = convertView.findViewById(R.id.name);
            TextView age = convertView.findViewById(R.id.age);
            TextView height = convertView.findViewById(R.id.height);
            TextView email = convertView.findViewById(R.id.email);

            UserData userData = userDataList.get(position);
            name.setText(userData.getName());
            email.setText(userData.getEmail());
            height.setText(String.valueOf(userData.getHeight()));
            age.setText(String.valueOf(userData.getAge()));

            return convertView;
        } else {
            return convertView;
        }
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
}
