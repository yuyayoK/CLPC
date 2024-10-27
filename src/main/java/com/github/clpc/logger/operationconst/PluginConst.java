package com.github.clpc.logger.operationconst;

import com.github.clpc.logger.common.SendConsoleMessage;
import org.bukkit.plugin.Plugin;

import java.time.format.DateTimeFormatter;

import static com.github.clpc.logger.operationconst.ConfigConst.*;

public class PluginConst {

    public static Plugin plugin;
    public static String PLUGIN_NAME = "DiscordLog出力システム"; // プラグイン名：DiscordLog出力（デフォルト）
    public static String PLUGIN_COMMAND = "DiscordLog"; // プラグインコマンド
    public static final String YAML_SETTING = "plugin_settings/YAML_setting.yml"; // 共通YAML格納フォルダへのパス
    public static DateTimeFormatter LOG_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:KK:mm:ss"); // 日付（形式：yyyy-MM-dd HH:KK:mm:ss）
    public static DateTimeFormatter LOG_FILE_NAME = DateTimeFormatter.ofPattern("yyyy_MM_dd"); // 日付（形式：yyyy_MM_dd）
    public static String PUBLIC_SETTING; // DiscordLog出力設定

    /**
     * 各プラグイン用Constを読み込み
     */
    public void loadPluginConst() {
        try {
            // 固定値を読み込み
            PLUGIN_NAME = FILE_CONFIG.getString("プラグイン名", "DiscordLog出力システム");
            PUBLIC_SETTING = FILE_CONFIG.getString("Log出力設定", "有効");
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各プラグイン用Constの読み込みに失敗しました ]");
        }
    }

}
