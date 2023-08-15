package xyz.glabaystudios.shitlist.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.shitlist.api.data.model.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByDiscordUserId(Long discordUserId);
}