package xyz.glabaystudios.handler;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface ModalHandler {
    void handleModalSubmission(ModalInteractionEvent event);
}
