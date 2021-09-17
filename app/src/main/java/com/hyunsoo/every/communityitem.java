package com.hyunsoo.every;

public class communityitem {
    String title;
    String content;

    //아이템 저장할 틀 생성
    public communityitem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Community{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
