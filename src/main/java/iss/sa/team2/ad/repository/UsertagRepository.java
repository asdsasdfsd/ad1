package iss.sa.team2.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iss.sa.team2.ad.model.Usertag;

@Repository
public interface UsertagRepository extends JpaRepository<Usertag, Long> {
    // 可以在这里添加自定义的查询方法
}
