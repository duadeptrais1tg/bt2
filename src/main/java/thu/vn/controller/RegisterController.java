package thu.vn.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        if (userService.checkExistUsername(username)) {
            req.setAttribute("alert", "Tên đăng nhập đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (userService.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = userService.register(username, password, fullname, email, phone);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Đăng ký thất bại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}