package xyz.glabaystudios.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import xyz.glabaystudios.commands.DisSlash;

import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class Register implements DisSlash {
    @Override
    public void handleSlashCommand(SlashCommandInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;

        var username = TextInput.create("USERNAME_INPUT", "Enter a username for your profile.", TextInputStyle.SHORT)
                .setPlaceholder("EpicUserName")
                .setRequired(true)
                .build();

        var email = TextInput.create("EMAIL_INPUT", "Enter an email for recovery options.", TextInputStyle.SHORT)
                .setPlaceholder("name@domain.tld")
                .setRequired(true)
                .build();

        var password = TextInput.create("PASSWORD_INPUT", "Enter a password.", TextInputStyle.SHORT)
                .setPlaceholder("1337P433")
                .setRequired(true)
                .build();

        var registrationForm = Modal.create("REGISTRATION_FORM", "Register yourself for a Shit List")
                .addActionRow(username)
                .addActionRow(email)
                .addActionRow(password)
                .build();

        event.replyModal(registrationForm).queue();
    }
}
