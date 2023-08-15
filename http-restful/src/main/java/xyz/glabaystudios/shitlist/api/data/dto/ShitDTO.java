package xyz.glabaystudios.shitlist.api.data.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private Long discordId;
    private String reason;
    private Long points;
    private String createdOn;

}
