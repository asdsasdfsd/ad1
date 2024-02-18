package iss.sa.team2.ad.interfacemethods;

import java.util.List;
import java.util.Optional;

import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.User;

public interface IUserService {

    Optional<User> getUserById(String userId);

    User saveUser(User user);

    void deleteUser(String id);

    List<User> getAllUsers();
    
    String findUserIdByAccountAndPassword(String account, String password);
    
	List<User> searchByAccount(String account);

	RegularUser findByUsernameAndPassword(String username, String password);
}
