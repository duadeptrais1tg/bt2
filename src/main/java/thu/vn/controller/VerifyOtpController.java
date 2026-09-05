package thu.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.entity.User;
import thu.vn.form.OtpForm;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;
import thu.vn.utils.ValidationUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@WebServlet(urlPatterns = { "/verify-otp" })
public class VerifyOtpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = (String) req.getSession().getAttribute("verifyUsername");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }

        OtpForm form = new OtpForm();
        form.setOtp(ValidationUtil.clean(req.getParameter("otp")));

        Map<String, String> errors = ValidationUtil.validate(form);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
            return;
        }

        User user = userService.findByUsername(username);

        if (user == null) {
            req.setAttribute("error", "Tài khoản không tồn tại!");
        } else if (user.getOtp() == null || !user.getOtp().equals(form.getOtp())) {
            req.setAttribute("error", "Mã OTP không chính xác!");
        } else if (user.getOtpExpiry() == null || LocalDateTime.now().isAfter(user.getOtpExpiry())) {
            req.setAttribute("error", "Mã OTP đã hết hạn (chỉ có hiệu lực trong 5 phút)! Vui lòng đăng ký lại hoặc gửi lại mã.");
        } else {
            user.setStatus(1);
            user.setOtp(null);
            user.setOtpExpiry(null);
            userService.update(user);

            req.getSession().removeAttribute("verifyUsername");
            req.getSession().removeAttribute("otpMailWarning");
            req.setAttribute("message", "Kích hoạt tài khoản thành công! Bạn có thể đăng nhập ngay.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("form", form);
        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }
}
