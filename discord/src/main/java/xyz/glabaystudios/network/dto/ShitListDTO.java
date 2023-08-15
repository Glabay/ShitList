package xyz.glabaystudios.shitlist.api.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class ShitListDTO {

    private Long discordOwnerId;
    private String listName;

    private Collection<ShitterDTO> shitters = new ArrayList<>();

    private String createdOn;
    private String updatedOn;
}