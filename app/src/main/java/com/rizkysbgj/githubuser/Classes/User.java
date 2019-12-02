package com.rizkysbgj.githubuser.Classes;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    String login;
    @SerializedName("avatar_url")
    String avatar_url;

    public User() {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
