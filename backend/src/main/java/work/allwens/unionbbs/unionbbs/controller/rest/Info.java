package work.allwens.unionbbs.unionbbs.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import work.allwens.unionbbs.unionbbs.dao.*;
import work.allwens.unionbbs.unionbbs.entity.*;

@RestController
@RequestMapping("/api")
public class Info {
    @Autowired
    UserDao userDao;

    @GetMapping("/users/{id}")
    public User user(@PathVariable("id") long id) {
        return userDao.getById(id);
    }
}
