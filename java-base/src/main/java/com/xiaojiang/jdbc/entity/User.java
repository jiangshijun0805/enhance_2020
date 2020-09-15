package com.xiaojiang.jdbc.entity;

public class User {
    private Integer id;
    private String name;
    private Integer post_date_year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPost_date_year() {
        return post_date_year;
    }

    public void setPost_date_year(Integer post_date_year) {
        this.post_date_year = post_date_year;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", post_date_year=" + post_date_year +
                '}';
    }
}
