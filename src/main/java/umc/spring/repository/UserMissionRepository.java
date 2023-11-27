package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.UserMission;
import umc.spring.domain.enums.UserMissionStatus;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    UserMission findByMissionAndUser(Mission missionIdx, User userIdx);
    @Query("SELECT um FROM UserMission um WHERE um.status = :status AND um.user = :user")
    Page<UserMission> findInProgressMissionForUser(@Param("status") UserMissionStatus status, @Param("user") User user, Pageable pageable);
}
