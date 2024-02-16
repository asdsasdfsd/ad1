package iss.sa.team2.ad.repository;

import iss.sa.team2.ad.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 可以在这里添加自定义的查询方法
}
