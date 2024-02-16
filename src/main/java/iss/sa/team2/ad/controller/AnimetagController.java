package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IAnimetagService;
import iss.sa.team2.ad.model.Animetag;
import iss.sa.team2.ad.model.Blacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/animetags")
public class AnimetagController {

	@Autowired
	private IAnimetagService animetagService;

	@GetMapping("/management")
	public String showAnimetagagManagementPage(Model model) {
		List<Animetag> animetags = animetagService.findAll();
		if (animetags.isEmpty()) {
			model.addAttribute("message", "tag list empty!");
		} else {
			System.out.println("get tag list：");
			for (Animetag animetag : animetags) {
				System.out.println(animetag);
			}
		}
		model.addAttribute("animetags", animetags);
		return "animetagManagement";
	}

	@GetMapping("/search")
	public String searchAnimetag(@RequestParam("tagName") String tagName, Model model) {
		List<Animetag> animetags = animetagService.findByNameContaining(tagName);
		if (animetags.isEmpty()) {
			model.addAttribute("message", "No matching results found.");
		} else {
			model.addAttribute("animetags", animetags);
		}
		return "animetagManagement";
	}

	@PostMapping("/create")
	public String createAnimetag(@RequestParam("tagName") String tagName) {
		Animetag newAnimetag = new Animetag();
		newAnimetag.setTagName(tagName);
		animetagService.save(newAnimetag);
		return "redirect:/animetags/management";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") long id, Model model) {
		Optional<Animetag> optionalAnimetag = animetagService.findById(id);
		if (optionalAnimetag.isPresent()) {
			Animetag animetag = optionalAnimetag.get();
			model.addAttribute("animetag", animetag);
			return "animetagEdit";
		} else {
			return "error";
		}
	}

	@PostMapping("/edit/{id}")
	public String editAnimetag(@RequestParam("id") long id, @RequestParam("tagName") String tagName, Model model) {
		Optional<Animetag> optionalAnimetag = animetagService.findById(id);
		if (optionalAnimetag.isPresent()) {
			Animetag animetag = optionalAnimetag.get();
			animetag.setTagName(tagName);
			animetagService.save(animetag);
			model.addAttribute("message", "Successfully modified");
		} else {
			model.addAttribute("message", "fail to edit");
		}
		return "redirect:/animetags/management";
	}

	@GetMapping("/delete/{id}")
	public String deleteAnimetag(@PathVariable("id") long id, Model model) {
		Optional<Animetag> Animetag = animetagService.findById(id);
		if (Animetag.isPresent()) {
			animetagService.deleteById(id);
			model.addAttribute("message", "Successfully delete");
		} else {
			model.addAttribute("message", "fail to delete");
		}
		return "redirect:/animetags/management";
	}

}
