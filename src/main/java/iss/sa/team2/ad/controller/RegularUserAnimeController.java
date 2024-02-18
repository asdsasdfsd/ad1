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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/regularuseranime")
public class RegularUserAnimeController {

    @Autowired
    private IRegularUserAnimeService regularUserAnimeService;
    @Autowired
    private IAnimeService animeService;
	@Autowired
    private IUserService userService;

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
    
    @PostMapping("/create/subscription")
    public String createSubscription(@RequestParam("animeId") Long animeId,HttpSession session,RedirectAttributes redirectAttributes) {
    	
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

        RegularUserAnime regularUserAnime = new RegularUserAnime();
        
        regularUserAnime.setRegularUser(regularUser);
        regularUserAnime.setAnime(anime);
        regularUserAnime.setRating(-1);
        regularUserAnime.setTime(LocalDateTime.now());
        regularUserAnime.setType(MyType.Subscription);
        
        regularUserAnimeService.saveRegularUserAnime(regularUserAnime);
    
        redirectAttributes.addAttribute("animeId", animeId);
        
        return "redirect:/animes/details/{animeId}";
    }
}
