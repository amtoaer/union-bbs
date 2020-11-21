package work.allwens.unionbbs.unionbbs.dao;

import java.util.List;

import work.allwens.unionbbs.unionbbs.entity.Post;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PostDao extends AbstractDao<Post> {
	@Autowired
	CommentDao commentDao;

	@Override
	public List<Post> getByPage(int index) {
		return getJdbcTemplate().query(
				"SELECT posts.id,uname,uavatar,ptitle,pdate,pcomment FROM posts JOIN users ON posts.uid=users.id ORDER BY pdate DESC LIMIT 10 OFFSET ?;",
				this.rowMapper, 10 * (index - 1));
	}

	public long getTotalPage() {
		long count = getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM posts;", Long.class);
		return (long) Math.ceil((double) count / 10);
	}

	public void newPost(long uid, String title, String content) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(connection -> {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO posts(uid,ptitle,pdate,pcomment) VALUES(?,?,?,?);");
			ps.setLong(1, uid);
			ps.setString(2, title);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, 0);
			return ps;
		}, keyHolder);
		long pid = keyHolder.getKey().longValue();
		commentDao.newComment(uid, pid, content, false);
	}

}
