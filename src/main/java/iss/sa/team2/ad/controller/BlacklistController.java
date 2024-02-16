package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IBlacklistService;
import iss.sa.team2.ad.model.Blacklist;
import iss.sa.team2.ad.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blacklists")
public class BlacklistController {

	@Autowired
	private IBlacklistService blacklistService;

	@GetMapping("/management")
	public String showBlacklistManagementPage(Model model) {
		List<Blacklist> blacklists = blacklistService.findAll();
		if (blacklists.isEmpty()) {
			model.addAttribute("message", "Now black list is empty.");
		} else {
			System.out.println("black list：");
			for (Blacklist blacklist : blacklists) {
				System.out.println(blacklist);
			}
		}
		model.addAttribute("blacklists", blacklists);
		return "blacklistManagement";
	}
	
	@PostMapping("/create")
	public String createBlacklist(@RequestParam("word") String word) {
		Blacklist newBlacklist = new Blacklist();
		newBlacklist.setWords(word);
		blacklistService.save(newBlacklist);
		return "redirect:/blacklists/management";
	}

	@GetMapping("/search")
	public String searchBlacklist(@RequestParam("words") String words, Model model) {
		List<Blacklist> blacklists = blacklistService.searchByName(words);
        if (blacklists.isEmpty()) {
            model.addAttribute("message", "No matching results found.");
        } else {
            model.addAttribute("blacklists", blacklists);
        }
		return "blacklistManagement";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") int id, Model model) {
	    Optional<Blacklist> optionalBlacklist = blacklistService.findById(id);
	    if (optionalBlacklist.isPresent()) {
	        Blacklist blacklist = optionalBlacklist.get();
	        model.addAttribute("blacklist", blacklist);
	        return "blacklistEdit";
	    } else {
	        return "error";
	    }
	}
	
	@PostMapping("/edit/{id}")
	public String editBlacklist(@RequestParam("id") int id, @RequestParam("words") String words, Model model) {
	    Optional<Blacklist> optionalBlacklist = blacklistService.findById(id);
	    if (optionalBlacklist.isPresent()) {
	        Blacklist blacklist = optionalBlacklist.get();
	        blacklist.setWords(words);
	        blacklistService.save(blacklist);
	        model.addAttribute("message", "Successfully modified");
	    } else {
	        model.addAttribute("message", "fail to edit");
	    }
	    return "redirect:/blacklists/management";
	}
	
    @GetMapping("/delete/{id}")
    public String deleteBlacklist(@PathVariable("id") int id, Model model) {
        Optional<Blacklist> blacklist = blacklistService.findById(id);
        if (blacklist.isPresent()) {
            blacklistService.deleteById(id);
            model.addAttribute("message", "Successfully delete");
        } else {
            model.addAttribute("message", "fail to delete");
        }
        return "redirect:/blacklists/management";
    }


}
