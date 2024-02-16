package iss.sa.team2.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.sa.team2.ad.enums.MyType;
import iss.sa.team2.ad.interfacemethods.IUserService;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.RegularUserAnime;
import iss.sa.team2.ad.model.User;
import iss.sa.team2.ad.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

	@Autowired
    private UserRepository userRepository;

    // 示例方法，根据ID查找用户
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // 示例方法，保存用户
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 示例方法，删除用户
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // 示例方法，获取所有用户
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public String findUserIdByAccountAndPassword(String account, String password) {
        return userRepository.findUserIdByAccountAndPassword(account, password);
    }
    
    
}