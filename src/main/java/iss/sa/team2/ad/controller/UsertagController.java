package iss.sa.team2.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import iss.sa.team2.ad.model.Usertag;
import iss.sa.team2.ad.service.UsertagService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usertags")
public class UsertagController {

	@Autowired
    private UsertagService usertagService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usertag> getUsertagById(@PathVariable Long id) {
        Optional<Usertag> usertagOptional = usertagService.getUsertagById(id);
        return usertagOptional.map(usertag -> new ResponseEntity<>(usertag, HttpStatus.OK))
                               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Usertag> createUsertag(@RequestBody Usertag usertag) {
        Usertag savedUsertag = usertagService.saveUsertag(usertag);
        return new ResponseEntity<>(savedUsertag, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsertag(@PathVariable Long id) {
        usertagService.deleteUsertag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Usertag>> getAllUsertags() {
        List<Usertag> usertagList = usertagService.getAllUsertags();
        return new ResponseEntity<>(usertagList, HttpStatus.OK);
    }
}
