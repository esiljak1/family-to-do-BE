package com.esiljak1.familytodo.family_invite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/v1/invite")
public class InviteController {
    private final InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping(path = "{familyId}")
    public ResponseEntity<?> createInvite(@PathVariable("familyId") Long familyId, @RequestBody Long userId){
        return inviteService.createInvite(familyId, userId);
    }

    @GetMapping(path = "/forUser/{userId}")
    public ResponseEntity<?> getInvitesForUser(@PathVariable("userId") Long userId){
        return inviteService.getInvitesForUser(userId);
    }

    @GetMapping(path = "/forFamily/{familyId}")
    public ResponseEntity<?> getInvitesForFamily(@PathVariable("familyId") Long familyId){
        return inviteService.getInvitesForFamily(familyId);
    }
}
