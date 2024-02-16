package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Animetag;

import java.util.List;
import java.util.Optional;

public interface IAnimetagService {
    Optional<Animetag> findById(Long id);
    Animetag save(Animetag animetag);
    void deleteById(Long id);
    List<Animetag> findAll();
	List<Animetag> findByNameContaining(String tagName);
}
