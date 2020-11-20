package work.allwens.unionbbs.unionbbs.entity;

import java.sql.Date;

public class Post {
    // 帖子id
    private long id;
    // 发帖人用户名
    private String uname;
    // 发帖人头像
    private String uavatar;
    // 帖子标题
    private String ptitle;
    // 发贴日期
    private Date pdate;
    // 评论数
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUavatar() {
		return uavatar;
	}

	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}

}
