package xyz.glabaystudios.shitlist.utils.translators;

import xyz.glabaystudios.shitlist.api.data.dto.ShitListDTO;
import xyz.glabaystudios.shitlist.api.data.model.ShitList;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;

import java.util.Objects;

/**
 * @author Glaba
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface ShitListDataObjectTranslator extends DateTimeUtils, ShitterDataObjectTranslator {

    /**
     * Convert a Concrete Object from the database, to a Data Transfer Object, for passing around the service layers, as well as over the network
     * @param model The {@link ShitList} to convert to a DTO
     * @return The Data Transfer Object, without Database identifying information
     */
    default ShitListDTO fromObjectModel(ShitList model) {
        var dto = new ShitListDTO();
            dto.setListName(model.getListName());
            dto.setDiscordOwnerId(model.getDiscordOwnerId());
            dto.setCreatedOn(model.getCreatedOn());
            dto.setUpdatedOn(model.getUpdatedOn());
            var shits = model.getShitters().stream().filter(Objects::nonNull).map(this::fromObjectModel).toList();
            dto.setShitters(shits);
        return dto;
    }

}
