package xyz.glabaystudios.website.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-17
 */
@Controller
@RequestMapping("/error")
public class WebErrorController {

    @GetMapping
    public String getErrorPage(HttpServletRequest request, Model model) {
        var status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (Objects.nonNull(status)) {
            int statusCode = Integer.parseInt(status.toString());
            String text = "";
            switch (statusCode) {
                case 401 -> text = "You're missing the permissions to view this page";
                case 403 -> text = "You're not authorized to view the contents of this secret page";
                case 404 -> text = "the page you're looking for cannot be found";
                default -> text = "Something went sideways with the time space continueum... someone should fix this...";
            }
            model.addAttribute("errorCode", statusCode);
            model.addAttribute("errorMessage", text);
        }
        else {
            model.addAttribute("errorCode", "Unknown");
            model.addAttribute("errorMessage", "An Unknown error has occurred");
        }
        return "error";
    }
}
