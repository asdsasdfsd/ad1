package iss.sa.team2.ad.interfacemethods;

import java.util.List;
import java.util.Optional;

import iss.sa.team2.ad.model.Usertag;

public interface IUsertagService {

    Optional<Usertag> getUsertagById(Long id);

    Usertag saveUsertag(Usertag usertag);

    void deleteUsertag(Long id);

    List<Usertag> getAllUsertags();
}
