package thu.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.entity.Product;
import thu.vn.service.ProductServiceImpl;
import thu.vn.utils.ValidationUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/product" })
public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 6; // 6 sản phẩm / trang
        int page = 1;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception ignored) {
        }
        if (page < 1) page = 1;

        String q = ValidationUtil.clean(req.getParameter("q"));

        long totalProducts = productService.countSearch(q);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        if (totalPages == 0) totalPages = 1;
        if (page > totalPages) page = totalPages;

        List<Product> products = productService.searchPage(q, page, pageSize);

        req.setAttribute("productList", products);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("q", q);

        req.getRequestDispatcher("/views/product-list.jsp").forward(req, resp);
    }
}
