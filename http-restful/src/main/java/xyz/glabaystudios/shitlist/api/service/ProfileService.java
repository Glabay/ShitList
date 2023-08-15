package xyz.glabaystudios.shitlist.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.shitlist.api.data.dto.ProfileDTO;
import xyz.glabaystudios.shitlist.api.data.model.Profile;
import xyz.glabaystudios.shitlist.api.data.repo.ProfileRepository;
import xyz.glabaystudios.shitlist.utils.translators.ProfileDataObjectTranslator;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileDataObjectTranslator {
    private final ProfileRepository profileRepository;
    private final ShitListService shitListService;

    public ProfileDTO getProfileForUser(Long discordId) {
        if (profileRepository.findByDiscordUserId(discordId).isPresent()) {
            var profile = profileRepository.findByDiscordUserId(discordId).get();
            return fromObjectModel(profile);
        }
        return null;
    }

    public boolean updatedProfileDetails(Long discordId, ProfileDTO dto) {
        if (profileRepository.findByDiscordUserId(discordId).isPresent()) {
            var profile = profileRepository.findByDiscordUserId(discordId).get();
                profile.setUserEmail(dto.getUserEmail());
                profile.setUsername(dto.getUsername());
                profile.setUpdatedOn(getCurrentDateAndTime());
            profileRepository.save(profile);
            return true;
        }
        return false;
    }

    public Profile createNewProfile(Long discordId, String username, String email) {
        var profile = new Profile();
            profile.setDiscordUserId(discordId);
            profile.setUsername(username);
            profile.setUserEmail(email);
            profile.setCreatedOn(getCurrentDateAndTime());
            profile.setUpdatedOn(getCurrentDateAndTime());
        shitListService.saveList(profile.getShitList());
        profileRepository.save(profile);
        return profile;
    }


}
