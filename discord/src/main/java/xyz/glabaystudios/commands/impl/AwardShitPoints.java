package xyz.glabaystudios.commands.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.http.util.EntityUtils;
import xyz.glabaystudios.DiscordMessages;
import xyz.glabaystudios.commands.DisSlash;
import xyz.glabaystudios.network.GlabayStudiosNetwork;
import xyz.glabaystudios.network.dto.ShitDTO;
import xyz.glabaystudios.network.dto.ShitterDTO;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class AwardShitPoints implements DisSlash, GlabayStudiosNetwork, DiscordMessages {

    @Override
    public void handleSlashCommand(SlashCommandInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;
        var shitter = Objects.requireNonNull(event.getOption("shitter")).getAsMember();
        if (Objects.isNull(shitter)) return;

        var reason = Objects.requireNonNull(event.getOption("reason")).getAsString();
        var points = Objects.requireNonNull(event.getOption("points")).getAsLong();
        var shitterId = shitter.getIdLong();

        var uri = BASE_API_ENDPOINT.concat("/v1/profile/add/shitPoints");
        try {
            var dto = new ShitDTO();
                dto.setDiscordId(shitterId);
                dto.setReason(reason);
                dto.setPoints(points);

            var response = submitHttpPostWithBodyAwaitReply(uri, getStringEntityFromDTO(dto), getHttpClient());
            if (response.getStatusLine().getStatusCode() == 200) {
                var shitterDTO = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity(), "UTF-8"), ShitterDTO.class);
                if (Objects.nonNull(shitterDTO)) {
                    var field = new MessageEmbed.Field("Total Shit Points", shitterDTO.getTotalShitPoints().toString(), true);
                    var builder = getEmbeddedMessageWithFields(
                            "Shit List",
                            "Points Awarded",
                            "`%s` has been awarded `%d` points for `%s`"
                                    .formatted(shitter.getEffectiveName(), points, reason),
                            field);
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
