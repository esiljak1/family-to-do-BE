package com.esiljak1.familytodo.family;

import com.esiljak1.familytodo.exceptions.NonExistentUserException;
import com.esiljak1.familytodo.user.User;
import com.esiljak1.familytodo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    private User getUserWithId(long userId) throws NonExistentUserException {
        Optional<User> selectedUser = userRepository.findById(userId);
        if (selectedUser.isEmpty())
            throw new NonExistentUserException("User with passed id doesn't exist");
        return selectedUser.get();
    }

    private void updateUser(User user, Family family) {
        List<Family> userFamilies = user.getFamilies();
        userFamilies.add(family);
        userRepository.save(user);
    }

    @Autowired
    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
    }

    public List<Family> getFamilies() {
        return familyRepository.findAll();
    }

    public void createFamily(FamilyDTO familyDTO) throws NonExistentUserException {
        User user = getUserWithId(familyDTO.getOwnerId());
        Family family = familyRepository.save(new Family(familyDTO.getName(), familyDTO.isPrivate(), user));
        //updateUser(user, family);
    }
}
