package xyz.glabaystudios.shitlist.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.shitlist.api.data.dto.ShitDTO;
import xyz.glabaystudios.shitlist.api.data.model.Shit;
import xyz.glabaystudios.shitlist.api.data.repo.ShitRepository;
import xyz.glabaystudios.shitlist.api.data.repo.ShitterRepository;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;
import xyz.glabaystudios.shitlist.utils.translators.ShitDataObjectTranslator;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */

@Service
@RequiredArgsConstructor
public class ShitService implements DateTimeUtils, ShitDataObjectTranslator {
    private final ShitRepository shitRepository;
    private final ShitterRepository shitterRepository;

    public void saveShit(Shit shit) {
        shitRepository.save(shit);
    }

    public void createNewShit(ShitDTO dto) {
        if (shitterRepository.findByDiscordId(dto.getDiscordId()).isPresent()) {
            var shitter = shitterRepository.findByDiscordId(dto.getDiscordId()).get();
            var model = new Shit();
                model.setDiscordId(dto.getDiscordId());
                model.setReason(dto.getReason());
                model.setPoints(dto.getPoints());
                model.setCreatedOn(dto.getCreatedOn());
                model.setShitter(shitter);
            saveShit(model);
        }
    }
}
