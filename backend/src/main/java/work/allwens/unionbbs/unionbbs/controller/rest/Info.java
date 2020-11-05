package work.allwens.unionbbs.unionbbs.controller.rest;

import java.util.Map;

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
import work.allwens.unionbbs.unionbbs.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class Info {
    @Autowired
    UserDao userDao;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userDao.getById(id);
    }

    @PostMapping("/login")
    public Map<String, Object> userLogin(@RequestBody Login body) {
        try {
            User item = userDao.login(body.getAccount(), body.getPassword());
            return Map.of("user", item, "token", JwtUtil.sign(item.getId(), item.getUpassword()));
        } catch (Exception e) {
            return Map.of("error", "SIGN_FAILED", "message", e.getMessage());
        }
    }

    @PostMapping("/register")
    public Map<String, Object> userRegister(@RequestBody Register body) {
        try {
            User item = userDao.register(body.getName(), body.getAccount(), body.getPassword());
            return Map.of("user", item, "token", JwtUtil.sign(item.getId(), item.getUpassword()));
        } catch (Exception e) {
            return Map.of("error", "REGISTER_FAILED", "message", e.getMessage());
        }
    }
}
