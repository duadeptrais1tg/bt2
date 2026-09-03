package thu.vn.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import thu.vn.config.JpaConfig;
import thu.vn.entity.User;

public class UserDaoImpl implements IUserDao {

    @Override
    public User findByUsername(String username) {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public void insert(User user) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) trans.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("email", email);
            return query.getSingleResult() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            enma.close();
        }
    }
    @Override
    public void update(User user) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) trans.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public User findById(int id) {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            return enma.find(User.class, id);
        } finally {
            enma.close();
        }
    }
    
    
    
}