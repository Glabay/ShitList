package xyz.glabaystudios.network.dto;

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
public class ShitDTO {

    private Long discordId;
    private String reason;
    private Long points;
    private String createdOn;

}
