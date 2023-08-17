package xyz.glabaystudios;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import xyz.glabaystudios.listeners.DiscordListener;
import xyz.glabaystudios.listeners.FormModalListener;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-15
 */
public class Discord {

    private static Discord discordBot;
    private static JDA api;
    private static JDABuilder jdaBuilder;

    public static void main(String[] args) {
        getDiscordBot().init();
    }
    private void init() {
        System.out.println("Connecting to Discord...");
        try {
            api = getJdaBuilder().build();

            // Event Listeners
            getDiscordBotApi().addEventListener(
                    new DiscordListener(),
                    new FormModalListener()
            );

            // TODO Commands
            List<CommandData> commands = new ArrayList<>();
            // register
            commands.add(Commands.slash("register", "Register a new account for a Shit List."));

            // add shitter
            commands.add(Commands.slash("add-shitter", "Add a user to your Shit List and start tracking their stupid shit.")
                .addOptions(new OptionData(OptionType.USER, "shitter", "The user you want to add to your list", true))
            );

            // award points
            commands.add(Commands.slash("award", "Award someone some point")
                .addOptions(
                     new OptionData(OptionType.USER, "shitter", "The user you want to add points to", true),
                     new OptionData(OptionType.STRING, "reason", "What did they do this time around?", true),
                     new OptionData(OptionType.INTEGER, "points", "How many points is this worth?", true)
                )
            );

            // forgive points


            // send list to Discord
            getDiscordBotApi().updateCommands().addCommands(commands).queue();

            getDiscordBotApi().awaitReady();

            // print the invite url, and give it admin rights
            System.out.println(getDiscordBotApi().getInviteUrl(Permission.ADMINISTRATOR));

        } catch (LoginException e) {
            System.err.println("[LoginException] " + e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.err.println("[InterruptedException] " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void shutdownBot() {
        api.shutdown();
    }

    public static Discord getDiscordBot() {
        if (Objects.isNull(discordBot)) discordBot = new Discord();
        return discordBot;
    }

    public JDA getDiscordBotApi() {
        if (Objects.isNull(api)) init();
        return api;
    }

    private JDABuilder getJdaBuilder() {
        String DISCORD_TOKEN = "MTE0MDgzOTgzNzQyODA4ODg2Mg.GSe2HE.nuCaQTffQiRS30ZmIZKBYYE2lMVbTOKxxhZAXc";
        if (Objects.isNull(jdaBuilder))
            jdaBuilder = JDABuilder.createDefault(DISCORD_TOKEN,
                    //Bot Intents
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.MESSAGE_CONTENT,
                    GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                    GatewayIntent.DIRECT_MESSAGES)
                    //disabling some cache
                    .disableCache(
                            CacheFlag.MEMBER_OVERRIDES,
                            CacheFlag.VOICE_STATE,
                            CacheFlag.ONLINE_STATUS);
        return jdaBuilder;
    }
}
