package thu.vn.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thu.vn.entity.User;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", user);

            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}