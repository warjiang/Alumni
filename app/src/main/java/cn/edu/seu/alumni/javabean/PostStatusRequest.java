package cn.edu.seu.alumni.javabean;

/**
 * Created by 鹏程 on 2016/3/13.
 */
public class PostStatusRequest {

    private String news_text;
    private String[] image;

    public PostStatusRequest(String news_text, String[] image) {
        this.news_text = news_text;
        this.image = image;
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
}
