package thu.vn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thu.vn.config.Constant;

@WebServlet(urlPatterns = { "/image" })
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(Constant.UPLOAD_DIR + "/" + fileName);
            if (file.exists()) {
                resp.setContentType("image/jpeg");
                FileInputStream fis = new FileInputStream(file);
                fis.transferTo(resp.getOutputStream());
                fis.close();
            }
        }
    }
}