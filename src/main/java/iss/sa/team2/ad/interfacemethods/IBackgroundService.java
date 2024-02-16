package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Background;

import java.util.List;
import java.util.Optional;

public interface IBackgroundService {
    Optional<Background> findById(Long id);
    Background save(Background background);
    void deleteById(Long id);
    List<Background> findAll();
}

