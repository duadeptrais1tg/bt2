package thu.vn.service;

import thu.vn.entity.User;

public interface IUserService {
    User login(String username, String password);
    boolean register(String username, String password, String fullname, String email, String phone);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
}