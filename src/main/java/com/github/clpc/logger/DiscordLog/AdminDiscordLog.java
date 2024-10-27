package com.github.clpc.logger.DiscordLog;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_DISCORD_CHANNEL_ID;
import static com.github.clpc.logger.operationconst.DiscordLogConst.*;
import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * 管理者ログを出力するクラス
 */
public class AdminDiscordLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;
    private final Color COLOR = Color.PINK;

    // #### テキストチャンネル ####
    public final String TEXT_CHANNEL_NAME = ADMIN_TEXT_CHANNEL_NAME;
    public final List<String> TEXT_CHANNEL_ID_LIST = ADMIN_TEXT_CHANNEL_ID_LIST;

    // #### スレッドチャンネル ####
    public String THREAD_CHANNEL_NAME;
    public List<String> THREAD_CHANNEL_ID_LIST;

    public AdminDiscordLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * 管理者ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public boolean outPutLog(String PLUGIN_NAME, String MESSAGE){
        THREAD_CHANNEL_NAME = TEXT_CHANNEL_NAME + ".【-" + PLUGIN_NAME + "-】";
        THREAD_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("THREAD_CHANNEL." + THREAD_CHANNEL_NAME);
        EmbedBuilder embedBuilder = new EmbedBuilder()
            .setAuthor("【 " + PLUGIN_NAME + " 】", null, null)
            .setThumbnail("http://cravatar.eu/helmhead/" + UUID + ".png")
            .addField("管理者", "```" + MCID + "```", true)
            .addField("作業内容", "```" + MESSAGE + "```", true)
            .setFooter("in " + WORLD_NAME + "  |  " + "作業時刻：" + NOW, null)
            .setColor(COLOR)
        ;
        // #### テキストチャンネル ####
        for (String textChannelID : TEXT_CHANNEL_ID_LIST) {
            TextChannel textChannel = jda.getTextChannelById(textChannelID);
            if(textChannel == null) continue; // チャンネルが存在しなければスルー
            textChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // 管理者ログを出力
        }
        // #### スレッドチャンネル ####
        for (String threadChannelID : THREAD_CHANNEL_ID_LIST) {
            ThreadChannel threadChannel = jda.getThreadChannelById(threadChannelID);
            if(threadChannel == null) continue; // チャンネルが存在しなければスルー
            threadChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // 管理者ログを出力
        }
        return true;
    }
}
