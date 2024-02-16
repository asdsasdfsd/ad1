package iss.sa.team2.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iss.sa.team2.ad.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 可以在这里添加自定义的查询方法
	@Query("SELECT u.id FROM User u WHERE u.account = :account AND u.password = :password")
    String findUserIdByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
