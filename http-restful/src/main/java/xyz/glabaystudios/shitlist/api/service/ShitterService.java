package xyz.glabaystudios.shitlist.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.shitlist.api.data.dto.ShitterDTO;
import xyz.glabaystudios.shitlist.api.data.model.Shitter;
import xyz.glabaystudios.shitlist.api.data.repo.ShitterRepository;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;
import xyz.glabaystudios.shitlist.utils.translators.ShitterDataObjectTranslator;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */

@Service
@RequiredArgsConstructor
public class ShitterService implements DateTimeUtils, ShitterDataObjectTranslator {
    private final ShitterRepository shitterRepository;

    public void saveShitter(Shitter shitter) {
        shitterRepository.save(shitter);
    }

    public ShitterDTO createNewShitter(Long shitterId) {
        var model = new Shitter();
            model.setDiscordId(shitterId);
            model.setTotalShitPoints(0L);
            model.setCreatedOn(getCurrentDateAndTime());
            model.setUpdatedOn(getCurrentDateAndTime());
        saveShitter(model);
        return fromObjectModel(model);
    }

    public ShitterDTO getShitterForId(Long discordId) {
        if (shitterRepository.findByDiscordId(discordId).isPresent()) {
            var shitter = shitterRepository.findByDiscordId(discordId).get();
            return fromObjectModel(shitter);
        }
        return null;
    }

    public void updateShitter(ShitterDTO dto) {
        if (shitterRepository.findByDiscordId(dto.getDiscordId()).isPresent()) {
            var shitter = shitterRepository.findByDiscordId(dto.getDiscordId()).get();
                shitter.setTotalShitPoints(dto.getTotalShitPoints());
                shitter.setUpdatedOn(getCurrentDateAndTime());
            saveShitter(shitter);
        }
        else throw new RuntimeException("Could not find a shitter");
    }
}
