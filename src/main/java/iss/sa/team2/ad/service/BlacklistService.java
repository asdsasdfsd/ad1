package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IBlacklistService;
import iss.sa.team2.ad.model.Blacklist;
import iss.sa.team2.ad.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlacklistService implements IBlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public Optional<Blacklist> findById(int id) {
        return blacklistRepository.findById(id);
    }

    @Override
    public Blacklist save(Blacklist blacklist) {
        return blacklistRepository.save(blacklist);
    }

    @Override
    public void deleteById(int id) {
        blacklistRepository.deleteById(id);
    }

    @Override
    public List<Blacklist> findAll() {
        return blacklistRepository.findAll();
    }

	@Override
    public List<Blacklist> searchByName(String words) {
        return blacklistRepository.findByNameContaining(words);
    }

}