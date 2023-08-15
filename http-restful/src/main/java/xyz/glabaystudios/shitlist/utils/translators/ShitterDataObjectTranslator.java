package xyz.glabaystudios.shitlist.utils.translators;

import xyz.glabaystudios.shitlist.api.data.dto.ShitterDTO;
import xyz.glabaystudios.shitlist.api.data.model.Shitter;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;

import java.util.Objects;

/**
 * @author Glaba
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface ShitterDataObjectTranslator extends DateTimeUtils, ShitDataObjectTranslator {

    /**
     * Convert a Concrete Object from the database, to a Data Transfer Object, for passing around the service layers, as well as over the network
     * @param model The {@link Shitter} to convert to a DTO
     * @return The Data Transfer Object, without Database identifying information
     */
    default ShitterDTO fromObjectModel(Shitter model) {
        var dto = new ShitterDTO();
            dto.setListId(Objects.isNull(model.getShitList()) ? -1L : model.getShitList().getUid());
            dto.setDiscordId(model.getDiscordId());
            dto.setCreatedOn(model.getCreatedOn());
            dto.setUpdatedOn(model.getUpdatedOn());
            dto.setTotalShitPoints(model.getTotalShitPoints());
            var shits = model.getStupidShit().stream().filter(Objects::nonNull).map(this::fromObjectModel).toList();
            dto.setStupidShit(shits);
        return dto;
    }

}
