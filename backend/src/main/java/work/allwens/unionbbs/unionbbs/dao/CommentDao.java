package work.allwens.unionbbs.unionbbs.dao;

import work.allwens.unionbbs.unionbbs.entity.Comment;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CommentDao extends AbstractDao<Comment> {

    public List<Comment> getByPage(long pid, int index) {
        // 查找帖子id为pid的第index页评论
        return getJdbcTemplate().query(
                "SELECT comments.id,uname,uavatar,ccontent,cdate FROM comments JOIN users WHERE comments.uid = users.id AND comments.pid = ? ORDER BY cdate LIMIT 10 OFFSET ?;",
                this.rowMapper, pid, 10 * (index - 1));
    }

    public long getTotalPage(long pid) {
        long count = getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM comments WHERE comments.pid=?;", Long.class,
                pid);
        return (long) Math.ceil((double) count / 10);
    }

    public void newComment(long uid, long pid, String content, boolean updatePost) {
        // updatePost用于指定是否更新Post
        var time = new Timestamp(System.currentTimeMillis());
        getJdbcTemplate().update("INSERT INTO comments(uid,pid,ccontent,cdate) VALUES (?,?,?,?);", uid, pid, content,
                time);
        if (updatePost) {
            getJdbcTemplate().update("UPDATE posts SET pdate = ?,pcomment=pcomment+1 WHERE posts.id = ?;", time, pid);
        }
    }
}
