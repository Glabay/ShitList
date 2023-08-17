package xyz.glabaystudios.website.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

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
    public String getHomePage(HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage(HttpServletRequest request) {
        if (Objects.isNull(request.getRemoteUser()))
            return "redirect:/login";
        return "admin";
    }
}
