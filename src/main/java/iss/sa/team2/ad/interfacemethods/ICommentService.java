package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void deleteById(Long id);
    List<Comment> findAll();
}