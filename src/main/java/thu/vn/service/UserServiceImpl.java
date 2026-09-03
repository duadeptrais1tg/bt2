package thu.vn.service;

import thu.vn.dao.IUserDao;
import thu.vn.dao.UserDaoImpl;
import thu.vn.entity.User;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String fullname, String email, String phone) {
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRoleid(2); // 1: Admin, 2: User
        userDao.insert(user);
        return true;
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }
    
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }
}