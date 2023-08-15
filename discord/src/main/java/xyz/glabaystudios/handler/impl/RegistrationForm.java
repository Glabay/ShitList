package xyz.glabaystudios.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.apache.http.util.EntityUtils;
import xyz.glabaystudios.DiscordMessages;
import xyz.glabaystudios.handler.ModalHandler;
import xyz.glabaystudios.network.GlabayStudiosNetwork;
import xyz.glabaystudios.network.dto.ProfileDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class RegistrationForm implements ModalHandler, GlabayStudiosNetwork, DiscordMessages {
    @Override
    public void handleModalSubmission(ModalInteractionEvent event) {
        Member disMem = event.getMember();
        if (Objects.isNull(disMem)) return;

        List<ModalMapping> fields = event.getValues();
        var username = fields.get(0).getAsString();
        var email = fields.get(1).getAsString();
        var discordId = disMem.getIdLong();

        var uri = BASE_API_ENDPOINT.concat("/v1/profile/create/").concat("%d/%s/%s".formatted(discordId, username, email));
        try {
            var response = submitHttpPostWithReply(uri, getHttpClient());
            if (response.getStatusLine().getStatusCode() == 200) {
                var profile = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity(), "UTF-8"), ProfileDTO.class);
                if (Objects.nonNull(profile)) {
                    var builder = getEmbeddedMessage("Shit List", "Success", "Your Shit List was successfully created!");
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
