package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IAnimeService;
import iss.sa.team2.ad.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animes")
public class AnimeRestController {

	@Autowired
    private IAnimeService animeService;

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        Optional<Anime> animeOptional = animeService.findById(id);
        return animeOptional.map(anime -> new ResponseEntity<>(anime, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        Anime savedAnime = animeService.save(anime);
        return new ResponseEntity<>(savedAnime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Anime>> findAll() {
        List<Anime> animeList = animeService.findAll();
        return new ResponseEntity<>(animeList, HttpStatus.OK);
    }
}
