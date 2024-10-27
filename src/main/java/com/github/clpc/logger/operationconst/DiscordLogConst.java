package com.github.clpc.logger.operationconst;

import com.github.clpc.logger.common.SendConsoleMessage;
import net.dv8tion.jda.api.JDA;

import java.util.List;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_DISCORD_CHANNEL_ID;

public class DiscordLogConst {

    // DiscordLog用
    public static JDA jda; // JDA情報
    public static String TOKEN; // トークン

    // Discordチャンネル名保存用
    public static final String ADMIN_TEXT_CHANNEL_NAME = "【-管理者ログ-】";
    public static final String BATCH_TEXT_CHANNEL_NAME = "【-Batchログ-】";
    public static final String DATABASE_TEXT_CHANNEL_NAME = "【-DBログ-】";
    public static final String ERROR_TEXT_CHANNEL_NAME = "【-エラーログ-】";
    public static final String MONEY_TEXT_CHANNEL_NAME = "【-金銭ログ-】";
    public static final String ILLEGAL_TEXT_CHANNEL_NAME = "【-不正ログ-】";
    public static final String PLAYER_TEXT_CHANNEL_NAME = "【-プレイヤーログ-】";
    public static final String WORLD_TEXT_CHANNEL_NAME = "【-ワールドログ-】";

    // DiscordテキストチャンネルID保存用
    public static List<String> ADMIN_TEXT_CHANNEL_ID_LIST;
    public static List<String> BATCH_TEXT_CHANNEL_ID_LIST;
    public static List<String> DATABASE_TEXT_CHANNEL_ID_LIST;
    public static List<String> ERROR_TEXT_CHANNEL_ID_LIST;
    public static List<String> MONEY_TEXT_CHANNEL_ID_LIST;
    public static List<String> ILLEGAL_TEXT_CHANNEL_ID_LIST;
    public static List<String> PLAYER_TEXT_CHANNEL_ID_LIST;
    public static List<String> WORLD_TEXT_CHANNEL_ID_LIST;

    /**
     * 各DiscordLog用Constを読み込み
     */
    public void loadDiscordLogConst() {
        try {
            TOKEN = FILE_DISCORD_CHANNEL_ID.getString("BOT_TOKEN");
            ADMIN_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + ADMIN_TEXT_CHANNEL_NAME);
            BATCH_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + BATCH_TEXT_CHANNEL_NAME);
            DATABASE_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + DATABASE_TEXT_CHANNEL_NAME);
            ERROR_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + ERROR_TEXT_CHANNEL_NAME);
            MONEY_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + MONEY_TEXT_CHANNEL_NAME);
            ILLEGAL_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + ILLEGAL_TEXT_CHANNEL_NAME);
            PLAYER_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + PLAYER_TEXT_CHANNEL_NAME);
            WORLD_TEXT_CHANNEL_ID_LIST = FILE_DISCORD_CHANNEL_ID.getStringList("TEXT_CHANNEL." + WORLD_TEXT_CHANNEL_NAME);
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各DiscordLog用Constの読み込みに失敗しました ]");
        }
    }
}