package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IAnimetagService;
import iss.sa.team2.ad.model.Animetag;
import iss.sa.team2.ad.repository.AnimetagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimetagService implements IAnimetagService {

    private final AnimetagRepository animetagRepository;

    @Autowired
    public AnimetagService(AnimetagRepository animetagRepository) {
        this.animetagRepository = animetagRepository;
    }

    @Override
    public Optional<Animetag> findById(Long id) {
        return animetagRepository.findById(id);
    }

    @Override
    public Animetag save(Animetag animetag) {
        return animetagRepository.save(animetag);
    }

    @Override
    public void deleteById(Long id) {
        animetagRepository.deleteById(id);
    }

    @Override
    public List<Animetag> findAll() {
        return animetagRepository.findAll();
    }

	@Override
	public List<Animetag> findByNameContaining(String tagName) {
		return animetagRepository.findByNameContaining(tagName);
	}
}
