package xyz.glabaystudios.shitlist.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.shitlist.api.data.model.ShitList;

public interface ShitListRepository extends JpaRepository<ShitList, Long> {
}