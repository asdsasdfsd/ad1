package iss.sa.team2.ad.interfacemethods;

import iss.sa.team2.ad.model.Friendship;

import java.util.List;
import java.util.Optional;

public interface IFriendshipService {
	
    Optional<Friendship> findById(Long id);
    Friendship save(Friendship friendship);
    void deleteById(Long id);
    List<Friendship> findAll();
}
