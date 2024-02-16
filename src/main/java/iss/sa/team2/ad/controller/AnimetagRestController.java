package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IAnimetagService;
import iss.sa.team2.ad.model.Animetag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animetags")
public class AnimetagRestController {

	@Autowired
    private IAnimetagService animetagService;

    @GetMapping("/{id}")
    public ResponseEntity<Animetag> findById(@PathVariable Long id) {
        Optional<Animetag> animetagOptional = animetagService.findById(id);
        return animetagOptional.map(animetag -> new ResponseEntity<>(animetag, HttpStatus.OK))
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Animetag> save(@RequestBody Animetag animetag) {
        Animetag savedAnimetag = animetagService.save(animetag);
        return new ResponseEntity<>(savedAnimetag, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        animetagService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Animetag>> findAll() {
        List<Animetag> animetagList = animetagService.findAll();
        return new ResponseEntity<>(animetagList, HttpStatus.OK);
    }
}
