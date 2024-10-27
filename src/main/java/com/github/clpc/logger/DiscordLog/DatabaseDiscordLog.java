package com.github.clpc.logger.DiscordLog;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_DISCORD_CHANNEL_ID;
import static com.github.clpc.logger.operationconst.DiscordLogConst.*;
import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * データベースログを出力するクラス
 */
public class DatabaseDiscordLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;
    private final Color COLOR = Color.GREEN;

    // #### テキストチャンネル ####
    public final String TEXT_CHANNEL_NAME = DATABASE_TEXT_CHANNEL_NAME;
    public final List<String> TEXT_CHANNEL_ID_LIST = DATABASE_TEXT_CHANNEL_ID_LIST;

    // #### スレッドチャンネル ####
    public String THREAD_CHANNEL_NAME;
    public List<String> THREAD_CHANNEL_ID_LIST;

    public DatabaseDiscordLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * データベースログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param TABLE_NAME テーブル名
     * @param MESSAGE 作業内容
     * @param QUERY SQLクエリ
     * @param PARAM パラメータ
     */
    public void outPutLog(String PLUGIN_NAME, String TABLE_NAME, String MESSAGE, String QUERY, HashMap<String, String> PARAM){
        THREAD_CHANNEL_NAME = TEXT_CHANNEL_NAME + ".【-" + TABLE_NAME + "-】";
        THREAD_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("THREAD_CHANNEL." + THREAD_CHANNEL_NAME);
        EmbedBuilder embedBuilder = new EmbedBuilder()
            .setAuthor("【 " + PLUGIN_NAME + " 】", null, null)
            .setThumbnail("http://cravatar.eu/helmhead/" + UUID + ".png")
            .addField("対象者", "```" + MCID + "```", false)
            .addField("内容", "```" + MESSAGE + "```", false)
            .addField("SQLクエリ", "```" + QUERY + "```", false)
            .setFooter("in " + WORLD_NAME + "  |  " + "作業時刻：" + NOW, null)
            .setColor(COLOR)
        ;
        PARAM.forEach((KEY, VALUE) -> embedBuilder.addField("パラメータ（" + KEY + "）", VALUE, true));
        // #### テキストチャンネル ####
        for (String textChannelID : TEXT_CHANNEL_ID_LIST) {
            TextChannel textChannel = jda.getTextChannelById(textChannelID);
            if(textChannel == null) continue; // チャンネルが存在しなければスルー
            textChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // DBログを出力
        }
        // #### スレッドチャンネル ####
        for (String threadChannelID : THREAD_CHANNEL_ID_LIST) {
            ThreadChannel threadChannel = jda.getThreadChannelById(threadChannelID);
            if(threadChannel == null) continue; // チャンネルが存在しなければスルー
            threadChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // DBログを出力
        }
    }
}
