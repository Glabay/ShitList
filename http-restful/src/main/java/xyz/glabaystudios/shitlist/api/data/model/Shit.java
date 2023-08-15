package xyz.glabaystudios.shitlist.api.data.model;

import jakarta.persistence.*;
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
@Entity
public class Shit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private Long discordId;
    private String reason;
    private Long points;
    private String createdOn;

    @ManyToOne
    private Shitter shit;

}
