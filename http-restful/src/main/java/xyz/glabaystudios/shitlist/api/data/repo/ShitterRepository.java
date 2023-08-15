package xyz.glabaystudios.shitlist.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.shitlist.api.data.model.Shitter;

import java.util.Optional;

public interface ShitterRepository extends JpaRepository<Shitter, Long> {
    Optional<Shitter> findByDiscordId(Long discordId);
}