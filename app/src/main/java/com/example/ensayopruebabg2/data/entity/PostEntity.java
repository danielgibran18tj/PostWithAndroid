package com.example.ensayopruebabg2.data.entity;

import java.io.Serializable;

public class PostEntity implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;
    private String img;

    public PostEntity(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    //    {
//        "userId": 1,
//            "id": 1,
//            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
//    }
}
