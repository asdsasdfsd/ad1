package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IRegularUserAnimeService;
import iss.sa.team2.ad.model.RegularUserAnime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/regularuseranime")
public class RegularUserAnimeController {

    @Autowired
    private IRegularUserAnimeService regularUserAnimeService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RegularUserAnime> getRegularUserAnimeById(@PathVariable Long id) {
        Optional<RegularUserAnime> regularUserAnimeOptional = regularUserAnimeService.getRegularUserAnimeById(id);
        return regularUserAnimeOptional.map(regularUserAnime -> new ResponseEntity<>(regularUserAnime, HttpStatus.OK))
                                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<List<RegularUserAnime>> getAllRegularUserAnimes() {
        List<RegularUserAnime> regularUserAnimeList = regularUserAnimeService.getAllRegularUserAnimes();
        return new ResponseEntity<>(regularUserAnimeList, HttpStatus.OK);
    }
}
