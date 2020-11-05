package work.allwens.unionbbs.unionbbs.dao;

import java.sql.PreparedStatement;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Statement;
import work.allwens.unionbbs.unionbbs.entity.User;

@Component
@Transactional
public class UserDao extends AbstractDao<User> {

    public User register(String uname, String uaccount, String upassword) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users (uname,uaccount,upassword) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, uname);
            ps.setString(2, uaccount);
            ps.setString(3, upassword);
            return ps;
        }, keyHolder);
        return this.getById(keyHolder.getKey().longValue());
    }

    public User login(String uaccount, String upassword) {
        return getJdbcTemplate().queryForObject("SELECT * FROM users WHERE uaccount = ? AND upassword = ?",
                new Object[] { uaccount, upassword }, this.rowMapper);
    }
}
