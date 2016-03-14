package cn.edu.seu.alumni.javabean;

import java.util.Arrays;

/**
 * @author pczhou
 * @date 2016/3/13
 */
public class StatusItem {

    //用户id
    private String user_id;

    //用户头像
    private String user_image;

    //用户名
    private String name;

    //发布时间
    private String post_time;

    //状态id
    private String news_id;

    //状态类型
    private String news_type;

    //状态文本
    private String news_text;

    //状态图片
    private String[] image;

    //评论数
    private int comment_amount;

    //点赞数
    private int like_amount;

    //分享数
    private int share_amount;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getNews_text() {
        return news_text;
    }

    public void setNews_text(String news_text) {
        this.news_text = news_text;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public int getComment_amount() {
        return comment_amount;
    }

    public void setComment_amount(int comment_amount) {
        this.comment_amount = comment_amount;
    }

    public int getLike_amount() {
        return like_amount;
    }

    public void setLike_amount(int like_amount) {
        this.like_amount = like_amount;
    }

    public int getShare_amount() {
        return share_amount;
    }

    public void setShare_amount(int share_amount) {
        this.share_amount = share_amount;
    }

    @Override
    public String toString() {
        return "StatusItem{" +
                "user_id='" + user_id + '\'' +
                ", user_image='" + user_image + '\'' +
                ", name='" + name + '\'' +
                ", post_time='" + post_time + '\'' +
                ", news_id='" + news_id + '\'' +
                ", news_type='" + news_type + '\'' +
                ", news_text='" + news_text + '\'' +
                ", image=" + Arrays.toString(image) +
                ", comment_amount=" + comment_amount +
                ", like_amount=" + like_amount +
                ", share_amount=" + share_amount +
                '}';
    }
}
