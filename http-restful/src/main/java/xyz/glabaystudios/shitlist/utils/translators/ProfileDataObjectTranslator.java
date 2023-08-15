package xyz.glabaystudios.shitlist.utils.translators;

import xyz.glabaystudios.shitlist.api.data.dto.ProfileDTO;
import xyz.glabaystudios.shitlist.api.data.model.Profile;
import xyz.glabaystudios.shitlist.utils.DateTimeUtils;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface ProfileDataObjectTranslator extends DateTimeUtils, ShitListDataObjectTranslator {

    /**
     * Convert a Concrete Object from the database, to a Data Transfer Object, for passing around the service layers, as well as over the network
     * @param model The {@link Profile} to convert to a DTO
     * @return The Data Transfer Object, without Database identifying information
     */
    default ProfileDTO fromObjectModel(Profile model) {
        var dto = new ProfileDTO();
            dto.setDiscordUserId(model.getDiscordUserId());
            dto.setUsername(model.getUsername());
            dto.setUserEmail(model.getUserEmail());
            dto.setCreatedOn(model.getCreatedOn());
            dto.setUpdatedOn(model.getUpdatedOn());
            dto.setShitList(fromObjectModel(model.getShitList()));
        return dto;
    }

}
