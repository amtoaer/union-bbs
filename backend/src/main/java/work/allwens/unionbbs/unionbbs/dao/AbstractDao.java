package work.allwens.unionbbs.unionbbs.dao;

import javax.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.ParameterizedType;

public class AbstractDao<T> extends JdbcDaoSupport {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String table;
    private Class<T> entityClass;
    protected RowMapper<T> rowMapper;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        // 获取T的类型
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        // 表名为类名+s（例如User->users）
        this.table = entityClass.getSimpleName().toLowerCase() + "s";
        this.rowMapper = new BeanPropertyRowMapper<>(entityClass);
    }

    public T getById(long id) {
        return getJdbcTemplate().queryForObject(String.format("SELECT * FROM %s WHERE id = ?", table), this.rowMapper,
                id);
    }

    public List<T> getByPage(int index) {
        int limit = 20;
        int offset = limit * (index - 1);
        return getJdbcTemplate().query(String.format("SELECT * FROM %s LIMIT ? OFFSET ?", table),
                new Object[] { limit, offset }, this.rowMapper);
    }

    public void deleteById(long id) {
        getJdbcTemplate().update(String.format("DELETE FROM %s WHERE id = ?", table), id);
    }

    @PostConstruct
    public void init() {
        super.setJdbcTemplate(jdbcTemplate);
    }
}
