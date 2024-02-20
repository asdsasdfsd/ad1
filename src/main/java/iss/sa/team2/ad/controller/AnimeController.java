package iss.sa.team2.ad.controller;
import iss.sa.team2.ad.dto.AnimeDTO;
import iss.sa.team2.ad.dto.PredictionRequest;
import iss.sa.team2.ad.enums.MyType;
import iss.sa.team2.ad.interfacemethods.IAnimeService;
import iss.sa.team2.ad.interfacemethods.IRegularUserAnimeService;
import iss.sa.team2.ad.interfacemethods.IUserService;
import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.RegularUserAnime;
import iss.sa.team2.ad.model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/animes")
public class AnimeController {

	@Autowired
    private IAnimeService animeService;
	@Autowired
    private IUserService userService;
	@Autowired
    private IRegularUserAnimeService regularUserAnimeService;
	@Autowired
    private RestTemplate restTemplate;

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
        
        RegularUserAnime regularUserAnime = new RegularUserAnime();
        
        regularUserAnime.setRegularUser(regularUser);
        regularUserAnime.setAnime(anime);
        regularUserAnime.setRating(-1);
        regularUserAnime.setTime(LocalDateTime.now());
        regularUserAnime.setType(MyType.History);
        
        model.addAttribute("anime", anime);
        model.addAttribute("regularUser", regularUser);

        return "animeDetails";
    }
    
    
    
    @GetMapping("/management")
    public String showAnimeManagementPage(Model model) {
        List<AnimeDTO> animeDTOs = animeService.findAllAnimeDTOs();
        if (animeDTOs.isEmpty()) {
            model.addAttribute("message", "Now anime list is empty.");
        } else {
        	model.addAttribute("anime", new Anime());
        	model.addAttribute("anime1", new Anime());
        	model.addAttribute("animeDTOs", animeDTOs);
        	model.addAttribute("showForm", false);
        	model.addAttribute("showForm2", false);
        }
        return "animeManagement";
    }
    
    @PostMapping("/search")
	public String searchAnimes(@ModelAttribute("name") String name, Model model) {
	    List<AnimeDTO> animeDTOs = animeService.searchByName(name);
	    
	    if (animeDTOs.isEmpty()) {
	        return "redirect:/animes/management";
	    } else {
	    	model.addAttribute("anime", new Anime());
        	model.addAttribute("anime1", new Anime());
	        model.addAttribute("animeDTOs", animeDTOs);
	        model.addAttribute("showForm", false);
        	model.addAttribute("showForm2", false);
	    }
	    
	    return "animeManagement";
	}
    
    @PostMapping("/create")
    public String createAnime(@Valid @ModelAttribute("anime") Anime anime, BindingResult result,Model model) {
        if (result.hasErrors()) {
        	List<AnimeDTO> animeDTOs = animeService.findAllAnimeDTOs();
            if (animeDTOs.isEmpty()) {
                model.addAttribute("message", "Now anime list is empty.");
            } else {
            	model.addAttribute("anime", anime);
            	model.addAttribute("anime1", anime);
            	model.addAttribute("animeDTOs", animeDTOs);
            	model.addAttribute("showForm", true);
            	model.addAttribute("showForm2", false);
            }
            return "animeManagement"; 
        }
        animeService.save(anime);
        return "redirect:/animes/management"; 
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
    	List<AnimeDTO> animeDTOs = animeService.findAllAnimeDTOs();
    	Optional<Anime> animeOptional = animeService.findById(id);
    	Anime anime = animeOptional.get();
    	model.addAttribute("anime", new Anime());
        model.addAttribute("anime1", anime);
        model.addAttribute("animeDTOs", animeDTOs);
        model.addAttribute("showForm", false);
    	model.addAttribute("showForm2", true);
    	model.addAttribute("ID", id);
        return "animeManagement"; 
    }
    
    @PostMapping("/edit/{id}")
    public String updateAnime(@Valid @ModelAttribute("anime") Anime anime, BindingResult result,Model model) {
    	if (result.hasErrors()) {
        	List<AnimeDTO> animeDTOs = animeService.findAllAnimeDTOs();
            if (animeDTOs.isEmpty()) {
                model.addAttribute("message", "Now anime list is empty.");
            } else {
            	model.addAttribute("anime", new Anime());
            	model.addAttribute("anime1", anime);
            	model.addAttribute("animeDTOs", animeDTOs);
            	model.addAttribute("showForm2", true);
            	model.addAttribute("showForm", false);
            	model.addAttribute("ID", anime.getId());
            }
            return "animeManagement"; 
        }
    	animeService.save(anime);
        return "redirect:/animes/management"; 
    }
    
    @GetMapping("/pre_delete/{id}")
    public String showDeleteConfirmation(@PathVariable("id") Long id, Model model) {
    	List<AnimeDTO> animeDTOs = animeService.findAllAnimeDTOs();
    	Optional<Anime> animeOptional = animeService.findById(id);
    	Anime anime = animeOptional.get();
    	model.addAttribute("anime", new Anime());
        model.addAttribute("anime1", anime);
        model.addAttribute("animeDTOs", animeDTOs);
        model.addAttribute("showForm", false);
    	model.addAttribute("showForm2",false);
    	model.addAttribute("showForm3",true);
    	model.addAttribute("delete_ID", id);
    	System.out.println(id);
        return "animeManagement"; 
    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable("id") Long id) {
        animeService.deleteById(id);
        return "redirect:/animes/management";
    }
}
