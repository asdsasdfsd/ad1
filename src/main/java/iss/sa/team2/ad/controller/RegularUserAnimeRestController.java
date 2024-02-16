package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.enums.MyType;
import iss.sa.team2.ad.interfacemethods.IAnimeService;
import iss.sa.team2.ad.interfacemethods.IRegularUserAnimeService;
import iss.sa.team2.ad.interfacemethods.IUserService;
import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.RegularUserAnime;
import iss.sa.team2.ad.model.User;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regularuseranime")
public class RegularUserAnimeRestController {

    @Autowired
    private IRegularUserAnimeService regularUserAnimeService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IAnimeService animeService;

    @GetMapping("/{id}")
    public ResponseEntity<RegularUserAnime> getRegularUserAnimeById(@PathVariable Long id) {
        Optional<RegularUserAnime> regularUserAnimeOptional = regularUserAnimeService.getRegularUserAnimeById(id);
        return regularUserAnimeOptional.map(regularUserAnime -> new ResponseEntity<>(regularUserAnime, HttpStatus.OK))
                                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RegularUserAnime> createRegularUserAnime(@RequestBody RegularUserAnime regularUserAnime) {
        RegularUserAnime savedRegularUserAnime = regularUserAnimeService.saveRegularUserAnime(regularUserAnime);
        return new ResponseEntity<>(savedRegularUserAnime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegularUserAnime(@PathVariable Long id) {
        regularUserAnimeService.deleteRegularUserAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<RegularUserAnime>> getAllRegularUserAnimes() {
        List<RegularUserAnime> regularUserAnimeList = regularUserAnimeService.getAllRegularUserAnimes();
        return new ResponseEntity<>(regularUserAnimeList, HttpStatus.OK);
    }
    
    @PostMapping("/add-to-collection")
    public ResponseEntity<?> addToCollection(@RequestBody RegularUserAnime regularUserAnime, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); 
        Optional<User> regularUser = userService.getUserById(null);
        Optional<Anime> anime = animeService.findById(regularUserAnime.getAnime().getId()); 

        RegularUserAnime regularUserAnime1 = new RegularUserAnime((RegularUser)regularUser.get(), anime.get(), regularUserAnime.getName(), LocalDateTime.now(), MyType.Collection, ""); 

        return ResponseEntity.ok().body("Collection added");
    }
}
