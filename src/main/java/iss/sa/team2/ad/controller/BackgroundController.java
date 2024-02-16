package iss.sa.team2.ad.controller;

import iss.sa.team2.ad.interfacemethods.IBackgroundService;
import iss.sa.team2.ad.model.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/backgrounds")
public class BackgroundController {

	@Autowired
    private IBackgroundService backgroundService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Background> findById(@PathVariable Long id) {
        Optional<Background> backgroundOptional = backgroundService.findById(id);
        return backgroundOptional.map(background -> new ResponseEntity<>(background, HttpStatus.OK))
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Background> save(@RequestBody Background background) {
        Background savedBackground = backgroundService.save(background);
        return new ResponseEntity<>(savedBackground, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        backgroundService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Background>> findAll() {
        List<Background> backgroundList = backgroundService.findAll();
        return new ResponseEntity<>(backgroundList, HttpStatus.OK);
    }
}
