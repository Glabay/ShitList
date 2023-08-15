package xyz.glabaystudios.shitlist.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.shitlist.api.data.model.Shit;

public interface ShitRepository extends JpaRepository<Shit, Long> {
}