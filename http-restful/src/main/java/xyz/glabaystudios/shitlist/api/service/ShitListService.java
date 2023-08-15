package xyz.glabaystudios.shitlist.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.shitlist.api.data.model.ShitList;
import xyz.glabaystudios.shitlist.api.data.repo.ShitListRepository;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Service
@RequiredArgsConstructor
public class ShitListService {
    private final ShitListRepository shitListRepository;


    public void saveList(ShitList shitList) {
        shitListRepository.save(shitList);
    }
}
