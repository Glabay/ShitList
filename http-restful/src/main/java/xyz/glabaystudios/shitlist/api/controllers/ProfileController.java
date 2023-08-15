package xyz.glabaystudios.shitlist.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.glabaystudios.shitlist.api.data.dto.ProfileDTO;
import xyz.glabaystudios.shitlist.api.data.dto.ShitDTO;
import xyz.glabaystudios.shitlist.api.data.dto.ShitterDTO;
import xyz.glabaystudios.shitlist.api.service.ProfileService;
import xyz.glabaystudios.shitlist.api.service.ShitterService;
import xyz.glabaystudios.shitlist.utils.translators.ProfileDataObjectTranslator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController implements ProfileDataObjectTranslator {
    private final ProfileService profileService;
    private final ShitterService shitterService;

    @GetMapping("/fetch/{discordId}")
    public ResponseEntity<ProfileDTO> getPlayerProfileForUser(@PathVariable Long discordId) {
        var profile = profileService.getProfileForUser(discordId);
        if (Objects.isNull(profile))
            // There was no content to return to the user
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            // return the DTO
            return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/create/{discordId}/{username}/{email}")
    public ResponseEntity<ProfileDTO> createNewProfile(
            @PathVariable Long discordId,
            @PathVariable String email,
            @PathVariable String username
    ) {
        var profile = profileService.createNewProfile(discordId, username, email);
        if (Objects.isNull(profile))
            // There was no content to return to the user
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            // return the DTO
            return new ResponseEntity<>(profileService.getProfileForUser(discordId), HttpStatus.OK);
    }

    @PostMapping("/add/shitter/{listOwnerId}/{shitterId}")
    public ResponseEntity<ProfileDTO> addShitterToList(
            @PathVariable Long shitterId,
            @PathVariable Long listOwnerId
    ) {
        var profile = profileService.getProfileForUser(listOwnerId);
        if (Objects.isNull(profile))
            // There was no content to return to the user
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        var shitter = shitterService.createNewShitter(shitterId);
        Collection<ShitterDTO> shitters = new ArrayList<>(profile.getShitList().getShitters());
        shitters.add(shitter);
        profile.getShitList().setShitters(shitters);
        if (profileService.updatedProfileDetails(listOwnerId, profile))
            return new ResponseEntity<>(profileService.getProfileForUser(listOwnerId), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add/shitPoints")
    public ResponseEntity<ShitterDTO> addShitPointsToShitter(@RequestBody ShitDTO stupidShitDto) {
        if (Objects.isNull(stupidShitDto))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        var shitter = shitterService.getShitterForId(stupidShitDto.getDiscordId());
            shitter.setTotalShitPoints(shitter.getTotalShitPoints() + stupidShitDto.getPoints());

        stupidShitDto.setCreatedOn(getCurrentDateAndTime());

        Collection<ShitDTO> shitters = new ArrayList<>(shitter.getStupidShit());
        shitters.add(stupidShitDto);
        shitter.setStupidShit(shitters);

        shitterService.updateShitter(shitter);
        return new ResponseEntity<>(shitter, HttpStatus.OK);
    }

}
