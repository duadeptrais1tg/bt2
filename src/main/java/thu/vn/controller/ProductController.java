package thu.vn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import thu.vn.config.Constant;
import thu.vn.entity.Category;
import thu.vn.entity.Product;
import thu.vn.form.ProductForm;
import thu.vn.service.CategoryServiceImpl;
import thu.vn.service.ProductServiceImpl;
import thu.vn.utils.ValidationUtil;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
@WebServlet(urlPatterns = {
        "/admin/products", "/admin/product/add", "/admin/product/insert",
        "/admin/product/edit", "/admin/product/update", "/admin/product/delete" })
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductServiceImpl productService = new ProductServiceImpl();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.endsWith("/admin/products")) {
            String q = ValidationUtil.clean(req.getParameter("q"));
            List<Product> list = q.isEmpty() ? productService.findAll() : productService.search(q);
            req.setAttribute("productList", list);
            req.setAttribute("q", q);
            req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);

        } else if (url.endsWith("/admin/product/add")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);

        } else if (url.endsWith("/admin/product/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Product p = productService.findById(id);
            if (p == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/products");
                return;
            }
            req.setAttribute("product", p);
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);

        } else if (url.endsWith("/admin/product/delete")) {
            try {
                productService.delete(Integer.parseInt(req.getParameter("id")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/products");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        boolean isUpdate = url.endsWith("/admin/product/update");

        ProductForm form = new ProductForm();
        form.setTitle(ValidationUtil.clean(req.getParameter("title")));
        form.setDescription(ValidationUtil.clean(req.getParameter("description")));
        form.setImages(ValidationUtil.clean(req.getParameter("images")));
        form.setPrice(parseDouble(req.getParameter("price")));
        form.setCategoryId(parseInteger(req.getParameter("categoryId")));

        Integer id = isUpdate ? parseInteger(req.getParameter("id")) : null;

        Map<String, String> errors = ValidationUtil.validate(form);

        Category category = null;
        if (form.getCategoryId() != null) {
            category = categoryService.findById(form.getCategoryId());
            if (category == null) {
                errors.put("categoryId", "Danh mục không tồn tại");
            }
        }

        if (!form.getImages().isEmpty() && !form.getImages().startsWith("http")) {
            errors.put("images", "Link ảnh phải bắt đầu bằng http:// hoặc https://");
        }

        Part part = req.getPart("imageFile");
        boolean hasFile = part != null && part.getSize() > 0;
        if (hasFile) {
            String ct = part.getContentType();
            if (ct == null || !ct.toLowerCase().startsWith("image/")) {
                errors.put("imageFile", "File tải lên phải là ảnh");
            }
        }

        if (isUpdate && id == null) {
            errors.put("title", "Thiếu mã sản phẩm cần cập nhật");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("form", form);
            req.setAttribute("categories", categoryService.findAll());
            if (isUpdate) {
                Product existing = id != null ? productService.findById(id) : new Product();
                req.setAttribute("product", existing);
                req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);
            }
            return;
        }

        Product product = isUpdate ? productService.findById(id) : new Product();
        product.setTitle(form.getTitle());
        product.setPrice(form.getPrice());
        product.setDescription(form.getDescription());
        product.setCategory(category);

        String uploadPath = Constant.UPLOAD_DIR;
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();

        if (hasFile) {
            String old = product.getImages();
            if (isUpdate && old != null && !old.startsWith("http")) {
                Files.deleteIfExists(Paths.get(uploadPath, old));
            }
            String original = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String fname = System.currentTimeMillis() + "_" + original;
            part.write(uploadPath + File.separator + fname);
            product.setImages(fname);
        } else if (!form.getImages().isEmpty()) {
            product.setImages(form.getImages());
        } else if (!isUpdate) {
            product.setImages("avatar.png");
        }

        if (isUpdate) {
            productService.update(product);
        } else {
            productService.insert(product);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/products");
    }

    private static Double parseDouble(String s) {
        try {
            return Double.valueOf(s.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private static Integer parseInteger(String s) {
        try {
            return Integer.valueOf(s.trim());
        } catch (Exception e) {
            return null;
        }
    }
}
