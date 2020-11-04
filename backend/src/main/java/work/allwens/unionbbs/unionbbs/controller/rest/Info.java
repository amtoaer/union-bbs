package work.allwens.unionbbs.unionbbs.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import work.allwens.unionbbs.unionbbs.dao.*;
import work.allwens.unionbbs.unionbbs.entity.*;
import work.allwens.unionbbs.unionbbs.request.Login;
import work.allwens.unionbbs.unionbbs.request.Register;

@RestController
@RequestMapping("/api")
public class Info {
    @Autowired
    UserDao userDao;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userDao.getById(id);
    }

    @PostMapping("/users/login")
    public User userLogin(@RequestBody Login body) {
        return userDao.login(body.getAccount(), body.getPassword());
    }

    @PostMapping("/usr/register")
    public User userRegister(@RequestBody Register body) {
        return userDao.register(body.getName(), body.getAccount(), body.getPassword());
    }
}
