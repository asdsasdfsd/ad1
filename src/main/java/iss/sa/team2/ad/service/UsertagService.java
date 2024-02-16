package iss.sa.team2.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.sa.team2.ad.interfacemethods.IUsertagService;
import iss.sa.team2.ad.model.Usertag;
import iss.sa.team2.ad.repository.UsertagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsertagService implements IUsertagService{

	@Autowired
    private UsertagRepository usertagRepository;

    // 示例方法，根据ID查找用户标签
    public Optional<Usertag> getUsertagById(Long id) {
        return usertagRepository.findById(id);
    }

    // 示例方法，保存用户标签
    public Usertag saveUsertag(Usertag usertag) {
        return usertagRepository.save(usertag);
    }

    // 示例方法，删除用户标签
    public void deleteUsertag(Long id) {
        usertagRepository.deleteById(id);
    }

    // 示例方法，获取所有用户标签
    public List<Usertag> getAllUsertags() {
        return usertagRepository.findAll();
    }
}
