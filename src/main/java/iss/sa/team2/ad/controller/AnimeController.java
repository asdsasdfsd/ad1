package iss.sa.team2.ad.controller;
import iss.sa.team2.ad.interfacemethods.IAnimeService;
import iss.sa.team2.ad.interfacemethods.IUserService;
import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.User;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/animes")
public class AnimeController {

	@Autowired
    private IAnimeService animeService;
	@Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Anime> findById(@PathVariable Long id) {
        Optional<Anime> animeOptional = animeService.findById(id);
        return animeOptional.map(anime -> new ResponseEntity<>(anime, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<List<Anime>> findAll() {
        List<Anime> animeList = animeService.findAll();
        return new ResponseEntity<>(animeList, HttpStatus.OK);
    }
    
    @GetMapping("/animelist")
    public String showAnimeList(Model model) {
        List<Anime> top20Anime = animeService.getTop20AnimeByAverageRating();
        List<Anime> latest20Anime = animeService.getLatest20Anime();
        List<Anime> top6Anime = animeService.getTop6AnimeByAverageRating();
        List<Anime> latest6Anime = animeService.getLatest6Anime();
        model.addAttribute("top20Anime", top20Anime);
        model.addAttribute("latest20Anime", latest20Anime);
        model.addAttribute("top6Anime", top6Anime);
        model.addAttribute("latest6Anime", latest6Anime);
   
        return "animelist";
    }
    
    @GetMapping("/sortByRating")
    public String getAnimesSortByRating(Model model) {
        List<Anime> animesSortByRating = animeService.findAllByOrderByAverageRatingDesc();
        List<Anime> top20Anime = animeService.getTop20AnimeByAverageRating();
        model.addAttribute("animes", animesSortByRating);
        model.addAttribute("top20Anime", top20Anime);
        return "animeListSortByRating";
    }

    @GetMapping("/sortByReleaseDate")
    public String getAnimesSortByReleaseDate(Model model) {
        List<Anime> animesSortByReleaseDate = animeService.findAllByOrderByReleaseDateDesc();
        List<Anime> latest20Anime = animeService.getLatest20Anime();
        model.addAttribute("animes", animesSortByReleaseDate);
        model.addAttribute("latest20Anime", latest20Anime);
        return "animeListSortByReleaseDate";
    }
    
    @GetMapping("/details/{id}")
    public String showAnimeDetails(@PathVariable("id") Long animeId, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        RegularUser regularUser = null;
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId); 
            if (user.isPresent()) {
                regularUser = (RegularUser) user.get();
            }
        }
       
        Optional<Anime> animeOptional = animeService.findById(animeId);
        Anime anime = animeOptional.get();

        model.addAttribute("anime", anime);
        model.addAttribute("regularUser", regularUser);

        return "animeDetails";
    }
}
