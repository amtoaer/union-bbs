package work.allwens.unionbbs.unionbbs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import work.allwens.unionbbs.unionbbs.dao.UserDao;

@Component
public class LoginFilter extends FilterRegistrationBean<Filter> {
    @Autowired
    UserDao userDao;

    @PostConstruct
    public void init() {
        setFilter(new ApiFilter());
        // 需要登录才能访问：users下的所有功能，新帖子和新评论内容
        setUrlPatterns(List.of("/api/users/*", "/posts/new", "/comments/new"));
    }

    class ApiFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            // 拿到token
            String token = req.getHeader("Authorization");
            long id;
            // 正常拿到id
            if ((id = JwtUtil.getId(token)) != -1) {
                var tmp = userDao.getById(id);
                // 通过验证则继续处理
                if (JwtUtil.verify(token, id, tmp.getUpassword())) {
                    // 将请求发起人绑定到请求
                    request.setAttribute("author", tmp);
                    // 继续处理
                    chain.doFilter(request, response);
                } else {
                    HttpServletResponse resp = (HttpServletResponse) response;
                    resp.setStatus(401);
                    resp.setContentType("application/json;charset=UTF-8");
                    ObjectMapper mapper = new ObjectMapper();
                    resp.getWriter().write(
                            mapper.writeValueAsString(Map.of("error", "Unauthorized", "message", "token已过期，请重新登录")));
                }
            } else {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.setStatus(401);
                resp.setContentType("application/json;charset=UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                resp.getWriter().write(
                        mapper.writeValueAsString(Map.of("error", "Unauthorized", "message", "请求未包含token，认证失败")));
            }
        }
    }
}
