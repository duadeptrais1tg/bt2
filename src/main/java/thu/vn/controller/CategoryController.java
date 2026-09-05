package thu.vn.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import thu.vn.config.Constant;
import thu.vn.entity.Category;
import thu.vn.form.CategoryForm;
import thu.vn.service.CategoryServiceImpl;
import thu.vn.utils.ValidationUtil;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
        "/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryServiceImpl cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("/admin/categories")) {
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = cateService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            cateService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        boolean isUpdate = url.contains("/admin/category/update");

        // Bind form
        CategoryForm form = new CategoryForm();
        form.setCategoryname(ValidationUtil.clean(req.getParameter("categoryname")));
        form.setImages(ValidationUtil.clean(req.getParameter("images")));
        form.setStatus(parseIntOrDefault(req.getParameter("status"), -1));

        Integer categoryid = isUpdate ? parseIntOrNull(req.getParameter("categoryid")) : null;

        // Validate
        Map<String, String> errors = ValidationUtil.validate(form);
        if (!form.getImages().isEmpty() && !form.getImages().startsWith("http")) {
            errors.put("images", "Link ảnh phải bắt đầu bằng http:// hoặc https://");
        }
        Part part = req.getPart("images1");
        boolean hasFile = part != null && part.getSize() > 0;
        if (hasFile) {
            String ct = part.getContentType();
            if (ct == null || !ct.toLowerCase().startsWith("image/")) {
                errors.put("images1", "File tải lên phải là ảnh");
            }
        }
        if (isUpdate && categoryid == null) {
            errors.put("categoryname", "Thiếu mã danh mục cần cập nhật");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            if (isUpdate) {
                Category current = categoryid != null ? cateService.findById(categoryid) : new Category();
                if (current != null) {
                    current.setCategoryname(form.getCategoryname());
                    current.setStatus(form.getStatus() < 0 ? current.getStatus() : form.getStatus());
                }
                req.setAttribute("cate", current);
                req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
            }
            return;
        }

        String uploadPath = Constant.UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        if (!isUpdate) {
            Category category = new Category();
            category.setCategoryname(form.getCategoryname());
            category.setStatus(form.getStatus());

            try {
                if (hasFile) {
                    category.setImages(writeUpload(part, uploadPath));
                } else if (!form.getImages().isEmpty()) {
                    category.setImages(form.getImages());
                } else {
                    category.setImages("avatar.png");
                }
            } catch (FileNotFoundException fne) {
                fne.printStackTrace();
            }

            cateService.insert(category);
        } else {
            Category category = cateService.findById(categoryid);
            String fileold = category.getImages();
            category.setCategoryname(form.getCategoryname());
            category.setStatus(form.getStatus());

            try {
                if (hasFile) {
                    if (fileold != null && !fileold.startsWith("http")) {
                        deleteFile(uploadPath + File.separator + fileold);
                    }
                    category.setImages(writeUpload(part, uploadPath));
                } else if (!form.getImages().isEmpty()) {
                    category.setImages(form.getImages());
                } else {
                    category.setImages(fileold);
                }
            } catch (FileNotFoundException fne) {
                fne.printStackTrace();
            }

            cateService.update(category);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    private static String writeUpload(Part part, String uploadPath) throws IOException {
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        int index = filename.lastIndexOf(".");
        String ext = index >= 0 ? filename.substring(index + 1) : "jpg";
        String fname = System.currentTimeMillis() + "." + ext;
        part.write(uploadPath + File.separator + fname);
        return fname;
    }

    private static int parseIntOrDefault(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }

    private static Integer parseIntOrNull(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }
}
