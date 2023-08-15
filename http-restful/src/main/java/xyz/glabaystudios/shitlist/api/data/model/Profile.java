package xyz.glabaystudios.shitlist.api.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private Long discordUserId;
    private String username;
    private String userEmail;
    private String createdOn;
    private String updatedOn;

    @OneToOne(mappedBy = "profile")
    private transient ShitList shitList = new ShitList();
}
