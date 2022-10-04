package com.esiljak1.familytodo.family_invite;

import com.esiljak1.familytodo.family.Family;
import com.esiljak1.familytodo.family.FamilyRepository;
import com.esiljak1.familytodo.user.User;
import com.esiljak1.familytodo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class InviteService {
    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    @Autowired
    public InviteService(InviteRepository inviteRepository, UserRepository userRepository, FamilyRepository familyRepository) {
        this.inviteRepository = inviteRepository;
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
    }

    public ResponseEntity<?> createInvite(Long familyId, Long userId){
        Optional<Family> family = familyRepository.findById(familyId);
        Map<String, Object> responseMap = new HashMap<>();
        if(family.isEmpty()) {
            responseMap.put("Message", "Family with id " + familyId + " doesn't exist");
            return ResponseEntity.status(400).body(responseMap);
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            responseMap.put("Message", "User with id " + userId + " doesn't exist");
            return ResponseEntity.status(400).body(responseMap);
        }

        Invite invite = inviteRepository.save(new Invite(user.get(), family.get()));
        responseMap.put("Data", invite);
        return ResponseEntity.ok(responseMap);
    }
}
