package iss.sa.team2.ad.service;

import iss.sa.team2.ad.interfacemethods.IFriendshipService;
import iss.sa.team2.ad.model.Friendship;
import iss.sa.team2.ad.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendshipService implements IFriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public Optional<Friendship> findById(Long id) {
        return friendshipRepository.findById(id);
    }

    @Override
    public Friendship save(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public void deleteById(Long id) {
        friendshipRepository.deleteById(id);
    }

    @Override
    public List<Friendship> findAll() {
        return friendshipRepository.findAll();
    }
}
