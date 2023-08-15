package xyz.glabaystudios.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import xyz.glabaystudios.handler.impl.RegistrationForm;

import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class FormModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;
        String formName = event.getModalId();

        switch (formName) {
            case "REGISTRATION_FORM" -> new RegistrationForm().handleModalSubmission(event);
            default -> System.err.println("Unhandled Form Modal... ".concat(formName));
        }
    }
}
