package com.example.practicemain;

import java.util.List;

public interface PostDataCallback {
    void onLoading();

    void onSuccess(List<Post> postList);

    void onFailure(String error);

}
