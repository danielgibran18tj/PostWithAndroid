package com.example.ensayopruebabg2.data.source.local.temp;

import android.content.Context;


import com.example.ensayopruebabg2.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalStorageImpl implements LocalStorage {

    private final Context context;

    @Inject
    public LocalStorageImpl(Context context) {
        this.context = context;
    }

    private void writeMessage(String keyName,String message) {
        context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE)
                .edit().putString(keyName, message).apply();
    }

    private String readMessage(String keyName) {
        return context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE)
                .getString(keyName, "");
    }

    private void writeValue(String keyName,boolean value) {
        context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE)
                .edit().putBoolean(keyName, value).apply();
    }

    private boolean readValue(String keyName) {
        return context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE)
                .getBoolean(keyName, false);
    }

    @Override
    public String getUser(){
        return readMessage(context.getString(R.string.shared_preferences_item_user));
    }

    @Override
    public void setUser(String user) {
        writeMessage(context.getString(R.string.shared_preferences_item_user),user);
    }

    @Override
    public String getToken() {
        return readMessage(context.getString(R.string.shared_preferences_item_token));
    }

    @Override
    public void setToken(String token) {
        writeMessage(context.getString(R.string.shared_preferences_item_token),token);
    }

    @Override
    public void setCredentials(String credentials) {
        writeMessage(context.getString(R.string.shared_preferences_item_credentials),credentials);
    }

    @Override
    public String getCredentials() {
        return readMessage(context.getString(R.string.shared_preferences_item_credentials));
    }

    @Override
    public String getPosts(){
        return readMessage(context.getString(R.string.shared_preferences_item_posts));
    }

    @Override
    public void setPosts(String posts) {
        writeMessage(context.getString(R.string.shared_preferences_item_posts), posts);
    }

}