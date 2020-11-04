package work.allwens.unionbbs.unionbbs.entity;

import java.sql.Date;

public class Comment {
    private long id;
    private long uid;
    private long pid;
    private String ccontent;
    private Date cdate;
    private long cpre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public long getCpre() {
        return cpre;
    }

    public void setCpre(long cpre) {
        this.cpre = cpre;
    }
}
