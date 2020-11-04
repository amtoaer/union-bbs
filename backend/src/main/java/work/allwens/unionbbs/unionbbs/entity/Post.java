package work.allwens.unionbbs.unionbbs.entity;

import java.sql.Date;

public class Post {
    private long id;
    private String ptitle;
    private Date pdate;
    private int pcomment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getPcomment() {
        return pcomment;
    }

    public void setPcomment(int pcomment) {
        this.pcomment = pcomment;
    }

}
