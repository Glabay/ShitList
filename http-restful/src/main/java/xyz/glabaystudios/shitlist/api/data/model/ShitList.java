package xyz.glabaystudios.shitlist.api.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
public class ShitList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    private Long discordOwnerId;
    private String listName;

    @OneToMany(mappedBy = "shitList", cascade = CascadeType.ALL)
    private Collection<Shitter> shitters = new ArrayList<>();

    private String createdOn;
    private String updatedOn;

    @OneToOne
    private Profile profile;

}