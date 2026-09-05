package thu.vn.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import thu.vn.config.JpaConfig;
import thu.vn.entity.Product;

public class ProductDaoImpl {

    public List<Product> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class).getResultList();
        } finally {
            em.close();
        }
    }

    /** Tìm theo tên sản phẩm (LIKE, không phân biệt hoa thường). keyword rỗng -> lấy tất cả. */
    public List<Product> search(String keyword) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Product p WHERE LOWER(p.title) LIKE :kw ORDER BY p.id DESC", Product.class)
                    .setParameter("kw", "%" + safeLower(keyword) + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> searchPage(String keyword, int page, int pageSize) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Product p WHERE LOWER(p.title) LIKE :kw ORDER BY p.id DESC", Product.class)
                    .setParameter("kw", "%" + safeLower(keyword) + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long countSearch(String keyword) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT COUNT(p) FROM Product p WHERE LOWER(p.title) LIKE :kw", Long.class)
                    .setParameter("kw", "%" + safeLower(keyword) + "%")
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    private static String safeLower(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    public List<Product> findLatest(int limit) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findPage(int page, int pageSize) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC", Product.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long count() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public Product findById(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public void insert(Product product) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(product);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Product product) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(product);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Product p = em.find(Product.class, id);
            if (p != null) {
                em.remove(p);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
