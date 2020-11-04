package work.allwens.unionbbs.unionbbs.entity;

public class User {
    private long uid;
    private String uname;
    private String uaccount;
    private String upassword;
    private String uavatar;
    private String usign;

    public User(long uid, String uname, String uaccount, String upassword, String uavatar, String usign) {
        this.uid = uid;
        this.uname = uname;
        this.uaccount = uaccount;
        this.upassword = upassword;
        this.uavatar = uavatar;
        this.usign = usign;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUavatar() {
        return uavatar;
    }

    public void setUavatar(String uavatar) {
        this.uavatar = uavatar;
    }

    public String getUsign() {
        return usign;
    }

    public void setUsign(String usign) {
        this.usign = usign;
    }
}
