package work.allwens.unionbbs.unionbbs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class User {
    private long uid;
    private String uname;
    private String uaccount;
    private String upassword;
    private String uavatar;
    private int upermission;
    private String usign;

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

    @JsonProperty(access = Access.WRITE_ONLY)
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

    public int getUpermission() {
        return upermission;
    }

    public void setUpermission(int upermission) {
        this.upermission = upermission;
    }

    public String getUsign() {
        return usign;
    }

    public void setUsign(String usign) {
        this.usign = usign;
    }

}
