package thu.vn.service;

import java.util.List;

import thu.vn.dao.ProductDaoImpl;
import thu.vn.entity.Product;

public class ProductServiceImpl {

    private ProductDaoImpl productDao = new ProductDaoImpl();

    public List<Product> findAll() { return productDao.findAll(); }
    public List<Product> findLatest(int limit) { return productDao.findLatest(limit); }
    public List<Product> findPage(int page, int pageSize) { return productDao.findPage(page, pageSize); }
    public long count() { return productDao.count(); }
    public List<Product> search(String keyword) { return productDao.search(keyword); }
    public List<Product> searchPage(String keyword, int page, int pageSize) { return productDao.searchPage(keyword, page, pageSize); }
    public long countSearch(String keyword) { return productDao.countSearch(keyword); }
    public Product findById(int id) { return productDao.findById(id); }
    public void insert(Product p) { productDao.insert(p); }
    public void update(Product p) { productDao.update(p); }
    public void delete(int id) { productDao.delete(id); }
}
