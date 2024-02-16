package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IFriendshipService;
import iss.sa.team2.ad.model.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipRestController {

    @Autowired
    private IFriendshipService friendshipService;

    @GetMapping("/{id}")
    public ResponseEntity<Friendship> getFriendshipById(@PathVariable Long id) {
        Optional<Friendship> friendshipOptional = friendshipService.findById(id);
        return friendshipOptional.map(friendship -> new ResponseEntity<>(friendship, HttpStatus.OK))
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Friendship> createFriendship(@RequestBody Friendship friendship) {
        Friendship savedFriendship = friendshipService.save(friendship);
        return new ResponseEntity<>(savedFriendship, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Friendship>> getAllFriendships() {
        List<Friendship> friendshipList = friendshipService.findAll();
        return new ResponseEntity<>(friendshipList, HttpStatus.OK);
    }
}
