package thu.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.entity.User;
import thu.vn.form.RegisterForm;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;
import thu.vn.utils.EmailUtil;
import thu.vn.utils.ValidationUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        RegisterForm form = new RegisterForm();
        form.setUsername(ValidationUtil.clean(req.getParameter("username")));
        form.setPassword(ValidationUtil.clean(req.getParameter("password")));
        form.setConfirmPassword(ValidationUtil.clean(req.getParameter("confirmPassword")));
        form.setFullname(ValidationUtil.clean(req.getParameter("fullname")));
        form.setEmail(ValidationUtil.clean(req.getParameter("email")));
        form.setPhone(ValidationUtil.clean(req.getParameter("phone")));

        // 1) Validate theo annotation
        Map<String, String> errors = ValidationUtil.validate(form);

        // 2) Kiểm tra nghiệp vụ bổ sung
        if (!errors.containsKey("confirmPassword")
                && !form.getPassword().equals(form.getConfirmPassword())) {
            errors.put("confirmPassword", "Mật khẩu nhập lại không khớp");
        }
        if (!errors.containsKey("username") && userService.findByUsername(form.getUsername()) != null) {
            errors.put("username", "Tên đăng nhập đã tồn tại");
        }
        if (!errors.containsKey("email") && userService.checkExistEmail(form.getEmail())) {
            errors.put("email", "Email đã được sử dụng");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword()); // Có thể băm BCrypt tại đây
        user.setEmail(form.getEmail());
        user.setFullname(form.getFullname());
        user.setPhone(form.getPhone());
        user.setStatus(0); // 0: Chưa kích hoạt

        String otp = String.format("%06d", new Random().nextInt(999999));
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        System.out.println("[REGISTER] OTP cho " + form.getUsername() + " = " + otp);

        try {
            userService.insert(user);

            boolean isSent = EmailUtil.sendOTP(form.getEmail(), otp);
            // Vẫn cho sang bước nhập OTP kể cả khi email lỗi (OTP đã in ở log server)
            req.getSession().setAttribute("verifyUsername", form.getUsername());
            if (!isSent) {
                req.getSession().setAttribute("otpMailWarning",
                        "Không gửi được email OTP (kiểm tra cấu hình EmailUtil). Xem mã OTP ở log server.");
            }
            resp.sendRedirect(req.getContextPath() + "/verify-otp");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đăng ký thất bại!");
            req.setAttribute("form", form);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
