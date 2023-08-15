package xyz.glabaystudios.shitlist.utils.translators;

import xyz.glabaystudios.shitlist.api.data.dto.ShitDTO;
import xyz.glabaystudios.shitlist.api.data.model.Shit;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;

/**
 * @author Glaba
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface ShitDataObjectTranslator extends DateTimeUtils {

    /**
     * Convert a Concrete Object from the database, to a Data Transfer Object, for passing around the service layers, as well as over the network
     * @param model The {@link Shit} to convert to a DTO
     * @return The Data Transfer Object, without Database identifying information
     */
    default ShitDTO fromObjectModel(Shit model) {
        var dto = new ShitDTO();
            dto.setPoints(model.getPoints());
            dto.setReason(model.getReason());
            dto.setCreatedOn(model.getCreatedOn());
            dto.setDiscordId(model.getDiscordId());
        return dto;
    }

}
