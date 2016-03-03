package cn.edu.seu.alumni.javabean;

/**
 * 好友 user_id user_name image
 */
public class Friend {

    private String name;
    private String letters;

    public Friend(String name) {
        this.name = name;
    }

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}
