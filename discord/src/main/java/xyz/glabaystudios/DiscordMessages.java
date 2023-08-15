package xyz.glabaystudios;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public interface DiscordMessages {

    default EmbedBuilder getEmbeddedMessage(String botName, String title, String description) {
        var builder = new EmbedBuilder();
            builder.setColor(new Color(40, 13, 65));
            builder.setTitle(title);
            builder.setAuthor(botName);
            builder.setDescription(description);
            builder.setFooter("Powered by: Glabay-Studios");
        return builder;
    }

    default EmbedBuilder getEmbeddedMessageWithFields(String botName, String title, String description, MessageEmbed.Field... fields) {
        var builder = getEmbeddedMessage(botName, title, description);
        Arrays.stream(fields).filter(Objects::nonNull).forEach(builder::addField);
        return builder;
    }
}
