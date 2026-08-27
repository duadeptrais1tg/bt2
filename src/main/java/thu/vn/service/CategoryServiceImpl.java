package thu.vn.service;

import java.util.List;
import thu.vn.dao.CategoryDaoImpl;
import thu.vn.entity.Category;

public class CategoryServiceImpl {
    private CategoryDaoImpl cateDao = new CategoryDaoImpl();

    public List<Category> findAll() { return cateDao.findAll(); }
    public Category findById(int id) { return cateDao.findById(id); }
    public void insert(Category category) { cateDao.insert(category); }
    public void update(Category category) { cateDao.update(category); }
    public void delete(int id) {
        try {
            cateDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}