package xyz.glabaystudios.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SiteMapController {

    @GetMapping({"/", "/home", "/index"})
    public String getHomePage() {

    }
}
