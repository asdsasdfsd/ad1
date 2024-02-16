package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.ICommentService;
import iss.sa.team2.ad.model.Comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    
    @GetMapping("/management")
    public String showCommentManagementPage(Model model) {
    	List<Comment> comments = commentService.findAll();

        // 打印评论列表
        if (comments.isEmpty()) {
            System.out.println("评论列表为空");
        } else {
            System.out.println("获取到的评论列表：");
            for (Comment comment : comments) {
                System.out.println(comment);
            }
        }

        // 将评论列表添加到模型中
        model.addAttribute("comments", comments);

        // 返回评论管理页面
        return "commentManagement";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional.map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 其他方法的实现与之类似
}
