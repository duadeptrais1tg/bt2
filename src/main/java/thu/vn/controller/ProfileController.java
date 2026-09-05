package thu.vn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import thu.vn.config.Constant;
import thu.vn.entity.User;
import thu.vn.form.ProfileForm;
import thu.vn.service.IUserService;
import thu.vn.service.UserServiceImpl;
import thu.vn.utils.ValidationUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/user/profile" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ProfileController extends HttpServlet {

    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = userService.findById(account.getId());
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("account");
        if (sessionUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = userService.findById(sessionUser.getId());

        ProfileForm form = new ProfileForm();
        form.setFullname(ValidationUtil.clean(req.getParameter("fullname")));
        form.setPhone(ValidationUtil.clean(req.getParameter("phone")));

        Map<String, String> errors = ValidationUtil.validate(form);

        // Validate file ảnh (nếu có upload)
        Part filePart = req.getPart("images");
        boolean hasFile = filePart != null && filePart.getSize() > 0;
        if (hasFile) {
            String ct = filePart.getContentType();
            if (ct == null || !ct.toLowerCase().startsWith("image/")) {
                errors.put("images", "File tải lên phải là ảnh (jpg, png, gif...)");
            } else if (filePart.getSize() > 10L * 1024 * 1024) {
                errors.put("images", "Ảnh không được vượt quá 10MB");
            }
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            // Hiển thị lại giá trị vừa nhập
            user.setFullname(form.getFullname());
            user.setPhone(form.getPhone());
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
            return;
        }

        user.setFullname(form.getFullname());
        user.setPhone(form.getPhone());

        if (hasFile) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String newFileName = System.currentTimeMillis() + "_" + originalFileName;

            File uploadDir = new File(Constant.UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            filePart.write(Constant.UPLOAD_DIR + File.separator + newFileName);
            user.setImages(newFileName);
        }

        try {
            userService.update(user);
            session.setAttribute("account", user);
            req.setAttribute("message", "Cập nhật thông tin thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Cập nhật thất bại!");
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
    }
}
