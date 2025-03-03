package com.example.practicemain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {
    private final List<UserData> userDataList;

    public CustomRecyclerViewAdapter(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_practice, parent, false);

        return (new CustomViewHolder(convertView));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int i) {
        UserData userData = userDataList.get(i);
        holder.name.setText(userData.getName());
        holder.email.setText(userData.getEmail());
        holder.height.setText(String.valueOf(userData.getHeight()));
        holder.age.setText(String.valueOf(userData.getAge()));
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, height, age;

        public CustomViewHolder(View convertView) {
            super(convertView);

            name = convertView.findViewById(R.id.name);
            email = convertView.findViewById(R.id.email);
            height = convertView.findViewById(R.id.height);
            age = convertView.findViewById(R.id.age);

        }
    }
}
