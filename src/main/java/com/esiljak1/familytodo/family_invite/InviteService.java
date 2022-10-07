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

    private ResponseEntity<?> error(String message){
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("Message", message);
        return ResponseEntity.status(400).body(responseMap);
    }

    public ResponseEntity<?> createInvite(Long familyId, Long userId){
        Optional<Family> family = familyRepository.findById(familyId);
        if(family.isEmpty())
            return error("Family with id " + familyId + " doesn't exist");

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            return error("User with id " + userId + " doesn't exist");

        Invite invite = inviteRepository.save(new Invite(user.get(), family.get()));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", invite);
        return ResponseEntity.ok(responseMap);
    }

    public ResponseEntity<?> getInvitesForUser(Long userId){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", inviteRepository.findAllByInvitedUserId(userId));
        return ResponseEntity.ok(responseMap);
    }

    public ResponseEntity<?> getInvitesForFamily(Long familyId) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("Data", inviteRepository.findAllByFamily_Id(familyId));
        return ResponseEntity.ok(responseMap);
    }

    public ResponseEntity<?> acceptInvite(InviteDTO inviteDTO) {
        Optional<Family> family = familyRepository.findById(inviteDTO.getFamilyId());
        if(family.isEmpty())
            return error("Family with id " + inviteDTO.getFamilyId() + " doesn't exist");

        Optional<User> user = userRepository.findById(inviteDTO.getUserId());
        if(user.isEmpty())
            return error("User with id " + inviteDTO.getUserId() + " doesn't exist");

        Family databaseFamily = family.get();
        databaseFamily.addUser(user.get());
        familyRepository.save(databaseFamily);

        return deleteInvite(inviteDTO);
    }

    public ResponseEntity<?> deleteInvite(InviteDTO inviteDTO){
        inviteRepository.deleteById(inviteDTO.getId());
        return ResponseEntity.ok("Success");
    }
}
