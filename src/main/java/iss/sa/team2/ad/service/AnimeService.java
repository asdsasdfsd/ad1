package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IAnimeService;
import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService implements IAnimeService {

	@Autowired
    private AnimeRepository animeRepository;

    @Override
    public Optional<Anime> findById(Long id) {
        return animeRepository.findById(id);
    }

    @Override
    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public void deleteById(Long id) {
        animeRepository.deleteById(id);
    }

    @Override
    public List<Anime> findAll() {
        return animeRepository.findAll();
    }
    
    @Override
    public List<Anime> getTop20AnimeByAverageRating() {
        return animeRepository.findTop20ByOrderByAverageRatingDesc();
    }

    @Override
    public List<Anime> getLatest20Anime() {
        return animeRepository.findTop20ByOrderByReleaseDateDesc();
    }
    
    @Override
    public List<Anime> getTop6AnimeByAverageRating() {
        return animeRepository.findTop6ByOrderByAverageRatingDesc();
    }

    @Override
    public List<Anime> getLatest6Anime() {
        return animeRepository.findTop6ByOrderByReleaseDateDesc();
    }
    
    @Override
    public List<Anime> findAllByOrderByAverageRatingDesc() {
        return animeRepository.findAllByOrderByAverageRatingDesc();
    }

    @Override
    public List<Anime> findAllByOrderByReleaseDateDesc() {
        return animeRepository.findAllByOrderByReleaseDateDesc();
    }
}
