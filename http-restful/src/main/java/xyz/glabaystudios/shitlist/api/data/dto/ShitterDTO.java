package xyz.glabaystudios.shitlist.api.data.model;

import jakarta.persistence.*;
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
@Entity
public class Shitter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private Long discordId;
    private Long listId;
    private Long totalShitPoints;

    @OneToMany(mappedBy = "discordId", cascade = CascadeType.ALL)
    Collection<Shit> stupidShit = new ArrayList<>();


    private String createdOn;
    private String updatedOn;
}
