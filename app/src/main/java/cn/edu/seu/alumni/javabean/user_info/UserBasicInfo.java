package cn.edu.seu.alumni.javabean.user_info;

public class UserBasicInfo {
    private int is_master; //0 1
    private String user_id;
    private String name;
    private String image; //头像url
    private String school;
    private String major;
    private String enroll_year;
    private String student_num;
    private String location;
    private String phone_num;
    private int weibo_bind;
    private int weixin_bind;

    public UserBasicInfo() {
    }

    public int getIs_master() {
        return is_master;
    }

    public void setIs_master(int is_master) {
        this.is_master = is_master;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEnroll_year() {
        return enroll_year;
    }

    public void setEnroll_year(String enroll_year) {
        this.enroll_year = enroll_year;
    }

    public String getStudent_num() {
        return student_num;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getWeibo_bind() {
        return weibo_bind;
    }

    public void setWeibo_bind(int weibo_bind) {
        this.weibo_bind = weibo_bind;
    }

    public int getWeixin_bind() {
        return weixin_bind;
    }

    public void setWeixin_bind(int weixin_bind) {
        this.weixin_bind = weixin_bind;
    }
}
