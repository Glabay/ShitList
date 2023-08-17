package xyz.glabaystudios.network.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Glaba
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Getter
@Setter
public class ProfileDTO {
    private Long discordUserId;

    private String username;
    private String userEmail;
    private String password;

    private String createdOn;
    private String updatedOn;

    private ShitListDTO shitList = new ShitListDTO();
}
