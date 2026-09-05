package thu.vn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
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

        // Chặn path traversal và tham số rỗng
        if (fileName == null || fileName.isBlank() || fileName.contains("..")
                || fileName.contains("/") || fileName.contains("\\")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(Constant.UPLOAD_DIR, fileName);
        if (!file.exists() || !file.isFile()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = Files.probeContentType(file.toPath());
        resp.setContentType(contentType != null ? contentType : "application/octet-stream");
        resp.setContentLengthLong(file.length());

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.transferTo(resp.getOutputStream());
        }
    }
}
