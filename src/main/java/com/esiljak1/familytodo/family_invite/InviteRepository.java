package com.esiljak1.familytodo.family_invite;

import com.esiljak1.familytodo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findAllByInvitedUserId(Long userId);
    List<Invite> findAllByFamily_Id(Long familyId);
}
