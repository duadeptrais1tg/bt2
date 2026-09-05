package thu.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.entity.User;
import thu.vn.form.ForgotPasswordForm;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;
import thu.vn.utils.EmailUtil;
import thu.vn.utils.ValidationUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = { "/forgot-password" })
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ForgotPasswordForm form = new ForgotPasswordForm();
        form.setEmail(ValidationUtil.clean(req.getParameter("email")));
        form.setOtp(ValidationUtil.clean(req.getParameter("otp")));
        form.setNewPassword(ValidationUtil.clean(req.getParameter("newPassword")));

        boolean step2 = !form.getOtp().isEmpty() || !form.getNewPassword().isEmpty();

        // Validate email (luôn), OTP + mật khẩu mới (chỉ bước 2)
        Map<String, String> errors = ValidationUtil.validate(form);
        if (step2) {
            if (!form.getOtp().matches("^\\d{6}$")) {
                errors.put("otp", "Mã OTP gồm đúng 6 chữ số");
            }
            if (form.getNewPassword().length() < 6) {
                errors.put("newPassword", "Mật khẩu mới phải có ít nhất 6 ký tự");
            }
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            req.setAttribute("step2", step2);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        if (!step2) {
            // Bước 1: gửi OTP reset
            User user = userService.findByUsername(form.getEmail());
            if (user != null) {
                String otp = String.format("%06d", new Random().nextInt(999999));
                user.setOtp(otp);
                user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
                userService.update(user);

                System.out.println("[FORGOT-PASSWORD] OTP cho " + user.getUsername() + " = " + otp);
                EmailUtil.sendOTP(user.getEmail(), otp);
                req.setAttribute("message", "Đã gửi mã OTP đến email. Vui lòng nhập OTP và mật khẩu mới!");
                req.setAttribute("step2", true);
            } else {
                req.setAttribute("error", "Email/Tài khoản không tồn tại!");
            }
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        // Bước 2: xác nhận OTP và đổi mật khẩu
        User user = userService.findByUsername(form.getEmail());
        if (user != null && form.getOtp().equals(user.getOtp())
                && user.getOtpExpiry() != null && LocalDateTime.now().isBefore(user.getOtpExpiry())) {
            user.setPassword(form.getNewPassword());
            user.setOtp(null);
            user.setOtpExpiry(null);
            userService.update(user);

            req.setAttribute("message", "Đổi mật khẩu thành công! Vui lòng đăng nhập.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Mã OTP không đúng hoặc đã hết hạn!");
            req.setAttribute("form", form);
            req.setAttribute("step2", true);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
