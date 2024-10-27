package com.github.clpc.logger.operationconst;

import com.github.clpc.logger.common.CustomConfig;
import com.github.clpc.logger.common.SendConsoleMessage;
import org.bukkit.configuration.file.FileConfiguration;

import static com.github.clpc.logger.operationconst.PluginConst.YAML_SETTING;
import static com.github.clpc.logger.operationconst.PluginConst.plugin;

public class ConfigConst {

    public static CustomConfig CONFIG;
    public static FileConfiguration FILE_CONFIG;

    // 各GUI用コンフィグファイル
    public static final String ADMIN_GUI_PATH = "ADMIN_GUI.yml";
    public static FileConfiguration FILE_ADMIN_GUI;

    // 各プラグイン専用コンフィグ
    public static final String TEXT_LOG_PATH = "TEXT_LOG_PATH.yml";
    public static final String DISCORD_CHANNEL_ID = "DISCORD_CHANNEL_ID.yml";
    public static FileConfiguration FILE_TEXT_LOG_PATH;
    public static FileConfiguration FILE_DISCORD_CHANNEL_ID;

    // 共通コンフィグファイル
    public static FileConfiguration FILE_WORLD_SETTING;

    /**
     * config.ymlを読み込み
     */
    public void loadConfig() {
        try {
            // config.ymlが存在しない場合、出力
            new CustomConfig(plugin).saveDefaultConfig();
            // config.ymlを取得
            CONFIG = new CustomConfig(plugin);
            FILE_CONFIG = CONFIG.getConfig();
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ config.ymlの読み込みに失敗しました ]");
        }
    }

    /**
     * 各GUI用configを読み込み
     */
    public void loadGUIConfig() {
        try {
            // 各ymlが存在しない場合、出力
            new CustomConfig(plugin, false, ADMIN_GUI_PATH).saveDefaultConfig();
            // 各GUI.ymlを取得
            FILE_ADMIN_GUI = new CustomConfig(plugin, false, ADMIN_GUI_PATH).getConfig();
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各GUI用configの読み込みに失敗しました ]");
        }
    }

    /**
     * 各プラグイン専用configを読み込み
     */
    public void loadPluginConfig() {
        try {
            // 各ymlが存在しない場合、出力
            new CustomConfig(plugin, false, TEXT_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, false, DISCORD_CHANNEL_ID).saveDefaultConfig();
            // 各プラグイン専用ymlを取得
            FILE_TEXT_LOG_PATH = new CustomConfig(plugin, false, TEXT_LOG_PATH).getConfig();
            FILE_DISCORD_CHANNEL_ID = new CustomConfig(plugin, false, DISCORD_CHANNEL_ID).getConfig();
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各プラグイン専用configの読み込みに失敗しました ]");
        }
    }

    /**
     * 各共通用configを読み込み
     */
    public void loadCommonConfig() {
        try {
            // YAML_setting.yml（共通YAML情報格納YAML） を取得
            FileConfiguration FILE_YAML_CONFIG = new CustomConfig(plugin, true, YAML_SETTING).getConfig();
            // 各共通用YAMLのパスを取得
            String WORLD_SETTING = FILE_YAML_CONFIG.getString("WORLD_SETTING"); // world設定情報
            // 各共通用YAMLを取得
            FILE_WORLD_SETTING = new CustomConfig(plugin, true, WORLD_SETTING).getConfig(); // world設定情報
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各共通用configの読み込みに失敗しました ]");
        }
    }

}
