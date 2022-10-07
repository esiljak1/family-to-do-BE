package com.esiljak1.familytodo.family;

import com.esiljak1.familytodo.exceptions.NonExistentFamilyException;
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

    private void toFamily(Family family, FamilyDTO familyDTO) {
        if(familyDTO.getOwnerId() != null){
            User user = getUserWithId(familyDTO.getOwnerId());
            family.setOwner(user);
        }
        family.setName(familyDTO.getName() != null ? familyDTO.getName() :  family.getName());
        family.setPrivate(familyDTO.isPrivate() != null ? familyDTO.isPrivate() : family.getPrivate());
    }

    @Autowired
    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
    }

    public List<Family> getFamilies() {
        return familyRepository.findAll();
    }

    public Family createFamily(FamilyDTO familyDTO) throws NonExistentUserException {
        User user = getUserWithId(familyDTO.getOwnerId());
        return familyRepository.save(new Family(familyDTO.getName(), familyDTO.isPrivate(), user));
    }

    public void deleteFamily(Long id){
        familyRepository.deleteById(id);
    }

    public Family updateFamily(Long id, FamilyDTO familyDTO){
        Optional<Family> family = familyRepository.findById(id);
        if(family.isEmpty())
            throw new NonExistentFamilyException("Family with passed id doesn't exist");

        toFamily(family.get(), familyDTO);
        return familyRepository.save(family.get());
    }
}
