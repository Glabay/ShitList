package xyz.glabaystudios.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.http.util.EntityUtils;
import xyz.glabaystudios.DiscordMessages;
import xyz.glabaystudios.commands.DisSlash;
import xyz.glabaystudios.network.GlabayStudiosNetwork;
import xyz.glabaystudios.network.dto.ProfileDTO;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class AddShitter implements DisSlash, GlabayStudiosNetwork, DiscordMessages {

    @Override
    public void handleSlashCommand(SlashCommandInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;
        var shitterId = 0L;
        var shitter = Objects.requireNonNull(event.getOption("shitter")).getAsMember();
        if (Objects.nonNull(shitter)) shitterId = shitter.getIdLong();

        var uri = BASE_API_ENDPOINT.concat("/v1/profile/add/shitter/").concat("%d/%d".formatted(disMem.getIdLong(), shitterId));
        try {
            var response = submitHttpPostWithReply(uri, getHttpClient());
            if (response.getStatusLine().getStatusCode() == 200) {
                var profile = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity(), "UTF-8"), ProfileDTO.class);
                if (Objects.nonNull(profile)) {
                    var builder = getEmbeddedMessage(
                            "Shit List",
                            "Added a little Shitter",
                            "Successfully added some little shit to you list that did you wrong or something stupid... either way we added them!");
                    event.replyEmbeds(builder.build()).queue();
                }
                else
                    event.reply("Something seems to have gone sideways... @Glabay please check into this").queue();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
