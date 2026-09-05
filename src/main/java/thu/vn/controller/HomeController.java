package thu.vn.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu.vn.config.JpaConfig;
import thu.vn.entity.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/home", "/" })
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager en = JpaConfig.getEntityManager();
        try {
            // Lấy 10 sản phẩm mới nhất theo ID giảm dần
            List<Product> top10Products = en.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class)
                    .setMaxResults(10)
                    .getResultList();

            req.setAttribute("top10Products", top10Products);
        } finally {
            en.close();
        }

        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}