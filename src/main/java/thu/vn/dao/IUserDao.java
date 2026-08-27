package thu.vn.dao;

import thu.vn.entity.User;

public interface IUserDao {
    User findByUsername(String username);
    void insert(User user);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
}