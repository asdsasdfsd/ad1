package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Blacklist;

import java.util.List;
import java.util.Optional;

public interface IBlacklistService {
    Optional<Blacklist> findById(int id);
    Blacklist save(Blacklist blacklist);
    void deleteById(int id);
    List<Blacklist> findAll();
	List<Blacklist> searchByName(String words);
}