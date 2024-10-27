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
 * エラーログを出力するクラス
 */
public class ExceptionErrorDiscordLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;
    private final Color COLOR = Color.RED;

    // #### テキストチャンネル ####
    public final String TEXT_CHANNEL_NAME = ERROR_TEXT_CHANNEL_NAME;
    public final List<String> TEXT_CHANNEL_ID_LIST = ERROR_TEXT_CHANNEL_ID_LIST;

    // #### スレッドチャンネル ####
    public String THREAD_CHANNEL_NAME;
    public List<String> THREAD_CHANNEL_ID_LIST;

    public ExceptionErrorDiscordLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * エラーログを出力するメソッド
     * 「/ExceptionErrorLog [PLUGIN_NAME] [MESSAGE] [EXCEPTION]」コマンドを検出
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE エラー内容
     * @param EXCEPTION 例外エラー内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE, String EXCEPTION){
        THREAD_CHANNEL_NAME = TEXT_CHANNEL_NAME + ".【-" + PLUGIN_NAME + "-】";
        THREAD_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("THREAD_CHANNEL." + THREAD_CHANNEL_NAME);
        EmbedBuilder embedBuilder = new EmbedBuilder()
            .setAuthor("【 " + PLUGIN_NAME + " 】", null, null)
            .setThumbnail("http://cravatar.eu/helmhead/" + UUID + ".png")
            .addField("対象者", "```" + MCID + "```", true)
            .addField("エラー内容", "```" + MESSAGE + "```", true)
            .addField("エラー詳細", "```" + EXCEPTION + "```", false)
            .setFooter("in " + WORLD_NAME + "  |  " + "作業時刻：" + NOW, null)
            .setColor(COLOR)
        ;
        // #### テキストチャンネル ####
        for (String textChannelID : TEXT_CHANNEL_ID_LIST) {
            TextChannel textChannel = jda.getTextChannelById(textChannelID);
            if(textChannel == null) continue; // チャンネルが存在しなければスルー
            textChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // エラーログを出力
        }
        // #### スレッドチャンネル ####
        for (String threadChannelID : THREAD_CHANNEL_ID_LIST) {
            ThreadChannel threadChannel = jda.getThreadChannelById(threadChannelID);
            if(threadChannel == null) continue; // チャンネルが存在しなければスルー
            threadChannel.sendMessageEmbeds(embedBuilder.build()).queue(); // エラーログを出力
        }
    }
}
