package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IBackgroundService;
import iss.sa.team2.ad.model.Background;
import iss.sa.team2.ad.repository.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BackgroundService implements IBackgroundService {

    private final BackgroundRepository backgroundRepository;

    @Autowired
    public BackgroundService(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
    }

    @Override
    public Optional<Background> findById(Long id) {
        return backgroundRepository.findById(id);
    }

    @Override
    public Background save(Background background) {
        return backgroundRepository.save(background);
    }

    @Override
    public void deleteById(Long id) {
        backgroundRepository.deleteById(id);
    }

    @Override
    public List<Background> findAll() {
        return backgroundRepository.findAll();
    }
}
