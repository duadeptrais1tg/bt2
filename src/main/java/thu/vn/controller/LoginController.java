package thu.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thu.vn.entity.User;
import thu.vn.form.LoginForm;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;
import thu.vn.utils.ValidationUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        LoginForm form = new LoginForm();
        form.setUsername(ValidationUtil.clean(req.getParameter("username")));
        form.setPassword(ValidationUtil.clean(req.getParameter("password")));

        Map<String, String> errors = ValidationUtil.validate(form);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        User user = userService.login(form.getUsername(), form.getPassword());

        if (user != null) {
            if (user.getStatus() == 0) {
                req.setAttribute("error", "Tài khoản của bạn chưa được kích hoạt qua OTP! Vui lòng kiểm tra email.");
                req.setAttribute("form", form);
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("account", user);

            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/profile");
            }
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
