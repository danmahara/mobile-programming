package com.example.practicemain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomPostRecyclerAdapter extends RecyclerView.Adapter<CustomPostRecyclerAdapter.CustomPostViewHolder> {

    List<Post> postList;

    public CustomPostRecyclerAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public CustomPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_data_list, parent, false);
        return (new CustomPostViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomPostViewHolder holder, int position) {

        Post post = postList.get(position);
        holder.id.setText(String.valueOf(post.getId())); // Convert int to String
        holder.userId.setText(String.valueOf(post.getUserId())); // Convert int to String
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class CustomPostViewHolder extends RecyclerView.ViewHolder {
        TextView userId, id, title, body;

        CustomPostViewHolder(View view) {
            super(view);
            userId = view.findViewById(R.id.userId);
            id = view.findViewById(R.id.postId);
            title = view.findViewById(R.id.post_title);
            body = view.findViewById(R.id.body);
        }
    }
}
