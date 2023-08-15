package xyz.glabaystudios.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class DiscordListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;
        String commandName = event.getInteraction().getName();

        switch (commandName.toLowerCase()) {

            default -> System.err.println("Unhandled Slash Command... ".concat(commandName));
        }
    }
}
