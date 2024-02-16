package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IRegularUserAnimeService;
import iss.sa.team2.ad.model.RegularUserAnime;
import iss.sa.team2.ad.repository.RegularUserAnimeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegularUserAnimeService implements IRegularUserAnimeService {

    @Autowired
    private RegularUserAnimeRepository regularUserAnimeRepository;

    @Override
    public Optional<RegularUserAnime> getRegularUserAnimeById(Long id) {
        return regularUserAnimeRepository.findById(id);
    }

    @Override
    public RegularUserAnime saveRegularUserAnime(RegularUserAnime regularUserAnime) {
        return regularUserAnimeRepository.save(regularUserAnime);
    }

    @Override
    public void deleteRegularUserAnime(Long id) {
        regularUserAnimeRepository.deleteById(id);
    }

    @Override
    public List<RegularUserAnime> getAllRegularUserAnimes() {
        return regularUserAnimeRepository.findAll();
    }
}
