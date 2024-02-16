package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.RegularUserAnime;

import java.util.List;
import java.util.Optional;

public interface IRegularUserAnimeService {
	
    Optional<RegularUserAnime> getRegularUserAnimeById(Long id);
    
    RegularUserAnime saveRegularUserAnime(RegularUserAnime regularUserAnime);
    
    void deleteRegularUserAnime(Long id);
    
    List<RegularUserAnime> getAllRegularUserAnimes();
}