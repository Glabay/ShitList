package xyz.glabaystudios.network.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Glaba
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Getter
@Setter
public class ShitterDTO {
    private Long discordId;
    private Long listId;
    private Long totalShitPoints;

    private Collection<ShitDTO> stupidShit = new ArrayList<>();


    private String createdOn;
    private String updatedOn;
}
