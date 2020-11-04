package work.allwens.unionbbs.unionbbs.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import work.allwens.unionbbs.unionbbs.entity.User;

@Component
@Transactional
public class UserDao extends AbstractDao<User> {

}
