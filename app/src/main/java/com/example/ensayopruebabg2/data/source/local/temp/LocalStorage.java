package com.example.ensayopruebabg2.data.source.local.temp;

public interface LocalStorage {
    String getUser();
    void setUser(String user);

    String getToken();
    void setToken(String token);

    void setCredentials(String credentials);

    String getCredentials();


    void setPosts(String posts);
    String getPosts();

}
