package work.allwens.unionbbs.unionbbs.controller.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

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
public class Api {
    @Autowired
    UserDao userDao;

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    /**
     * 登陆接口
     * 
     * @param body
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> userLogin(@RequestBody Login body) {
        try {
            User item = userDao.login(body.getAccount(), body.getPassword());
            return Map.of("user", item, "token", JwtUtil.sign(item.getId(), item.getUpassword()));
        } catch (Exception e) {
            return Map.of("error", "SIGN_FAILED", "message", e.getMessage());
        }
    }

    /**
     * 注册接口
     * 
     * @param body
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> userRegister(@RequestBody Register body) {
        try {
            User item = userDao.register(body.getName(), body.getAccount(), body.getPassword());
            return Map.of("user", item, "token", JwtUtil.sign(item.getId(), item.getUpassword()));
        } catch (Exception e) {
            return Map.of("error", "REGISTER_FAILED", "message", e.getMessage());
        }
    }

    /**
     * 返回个人信息
     */
    @GetMapping("/users/myInfo")
    public User getMyInfo(HttpServletRequest req) {
        return (User) req.getAttribute("author");
    }

    /**
     * 返回第index页的帖子列表
     * 
     * @param index
     * @return
     */
    @GetMapping("/posts/page/{index}")
    public List<Post> getPostByPage(@PathVariable("index") int index) {
        return postDao.getByPage(index);
    }

    /**
     * 得到帖子列表页数
     * 
     * @return
     */
    @GetMapping("/page/posts")
    public Map<String, Object> getPostTotalPage() {
        return Map.of("count", postDao.getTotalPage());
    }

    /**
     * 得到编号为pid的帖子的评论列表页数
     * 
     * @param pid
     * @return
     */
    @GetMapping("/page/posts/{pid}")
    public Map<String, Object> getCommentTotalPage(@PathVariable("pid") long pid) {
        return Map.of("count", commentDao.getTotalPage(pid));
    }

    /**
     * 得到编号为pid的帖子的第index页评论
     * 
     * @param pid
     * @param index
     * @return
     */
    @GetMapping("/posts/{pid}/page/{index}")
    public List<Comment> getCommentByPage(@PathVariable("pid") long pid, @PathVariable("index") int index) {
        return commentDao.getByPage(pid, index);
    }

    /**
     * 发帖子
     * 
     * @param body
     * @param req
     */
    @PostMapping("/posts/new")
    public void newPost(@RequestBody work.allwens.unionbbs.unionbbs.request.Post body, HttpServletRequest req) {
        // 获取绑定在请求中的用户信息(详情见过滤器)
        User author = (User) req.getAttribute("author");
        // 加入新的帖子
        postDao.newPost(author.getId(), body.getTitle(), body.getContent());
    }

    /**
     * 发评论
     * 
     * @param body
     * @param req
     */
    @PostMapping("/comments/new")
    public void newComment(@RequestBody work.allwens.unionbbs.unionbbs.request.Comment body, HttpServletRequest req) {
        User author = (User) req.getAttribute("author");
        commentDao.newComment(author.getId(), body.getPid(), body.getContent(), true);
    }

    @PostMapping("/users/update")
    public void updateUserInfo(@RequestBody work.allwens.unionbbs.unionbbs.request.User body, HttpServletRequest req) {
        User author = (User) req.getAttribute("author");
        userDao.update(body.getUname(), body.getUaccount(), body.getUpassword(), body.getUavatar(), author.getId());
    }
}
