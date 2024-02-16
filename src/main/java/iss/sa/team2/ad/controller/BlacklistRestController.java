package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IBlacklistService;
import iss.sa.team2.ad.model.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blacklists")
public class BlacklistRestController {

    @Autowired
    private IBlacklistService blacklistService;

    @GetMapping("/{id}")
    public ResponseEntity<Blacklist> findById(@PathVariable int id) {
        Optional<Blacklist> blacklistOptional = blacklistService.findById(id);
        return blacklistOptional.map(blacklist -> new ResponseEntity<>(blacklist, HttpStatus.OK))
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Blacklist> save(@RequestBody Blacklist blacklist) {
        Blacklist savedBlacklist = blacklistService.save(blacklist);
        return new ResponseEntity<>(savedBlacklist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        blacklistService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Blacklist>> findAll() {
        List<Blacklist> blacklistList = blacklistService.findAll();
        return new ResponseEntity<>(blacklistList, HttpStatus.OK);
    }
}