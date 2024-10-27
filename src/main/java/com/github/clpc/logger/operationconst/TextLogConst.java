package com.github.clpc.logger.operationconst;

import com.github.clpc.logger.common.CustomConfig;
import com.github.clpc.logger.common.SendConsoleMessage;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_TEXT_LOG_PATH;
import static com.github.clpc.logger.operationconst.PluginConst.*;

public class TextLogConst {

    // テキストLOG用
    public static CustomConfig CONFIG_ADMIN_LOG;
    public static CustomConfig CONFIG_ERROR_LOG;
    public static CustomConfig CONFIG_BATCH_LOG;
    public static CustomConfig CONFIG_WORLD_LOG;
    public static CustomConfig CONFIG_PLAYER_LOG;
    public static CustomConfig CONFIG_DATABASE_LOG;
    public static CustomConfig CONFIG_EMERGENCY_LOG;

    public static FileConfiguration FILE_ADMIN_LOG;
    public static FileConfiguration FILE_ERROR_LOG;
    public static FileConfiguration FILE_BATCH_LOG;
    public static FileConfiguration FILE_WORLD_LOG;
    public static FileConfiguration FILE_PLAYER_LOG;
    public static FileConfiguration FILE_DATABASE_LOG;
    public static FileConfiguration FILE_EMERGENCY_LOG;

    /**
     * 各Log用configを読み込み
     */
    public void loadLogConfig() {
        try {
            // log_setting.ymlより「LOG_TIME」「LOG_FILE_NAME」を取得
            String LOG_TIME_PATTERN = FILE_TEXT_LOG_PATH.getString("LOG_TIME");
            String LOG_FILE_NAME_PATTERN = FILE_TEXT_LOG_PATH.getString("LOG_FILE_NAME");
            if(LOG_TIME_PATTERN != null) LOG_TIME = DateTimeFormatter.ofPattern(LOG_TIME_PATTERN); // 日付（形式：yyyy-MM-dd HH:KK:mm:ss）
            if(LOG_FILE_NAME_PATTERN != null) LOG_FILE_NAME = DateTimeFormatter.ofPattern(LOG_FILE_NAME_PATTERN); // 日付（形式：yyyy_MM_dd）
            String TODAY = LocalDateTime.now().format(LOG_FILE_NAME); // システム日付（形式：yyyy_MM_dd）
            // 各Log用configのパスを取得
            String ADMIN_LOG_PATH = FILE_TEXT_LOG_PATH.getString("ADMIN_LOG") + TODAY + ".yml"; // 管理者ログ
            String ERROR_LOG_PATH = FILE_TEXT_LOG_PATH.getString("ERROR_LOG") + TODAY + ".yml"; // エラーログ
            String BATCH_LOG_PATH = FILE_TEXT_LOG_PATH.getString("BATCH_LOG") + TODAY + ".yml"; // バッチログ
            String WORLD_LOG_PATH = FILE_TEXT_LOG_PATH.getString("WORLD_LOG") + TODAY + ".yml"; // ワールドログ
            String PLAYER_LOG_PATH = FILE_TEXT_LOG_PATH.getString("PLAYER_LOG") + TODAY + ".yml"; // プレイヤーログ
            String DATABASE_LOG_PATH  = FILE_TEXT_LOG_PATH.getString("DATABASE_LOG") + TODAY + ".yml"; // データベースログ
            String EMERGENCY_LOG_PATH  = FILE_TEXT_LOG_PATH.getString("EMERGENCY_LOG") + TODAY + ".yml"; // 異常ログ
            // 各Log用configが存在しない場合、出力
            new CustomConfig(plugin, true, ADMIN_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, ERROR_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, BATCH_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, WORLD_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, PLAYER_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, DATABASE_LOG_PATH).saveDefaultConfig();
            new CustomConfig(plugin, true, EMERGENCY_LOG_PATH).saveDefaultConfig();
            // 各共通YAMLを取得
            CONFIG_ADMIN_LOG = new CustomConfig(plugin, true, ADMIN_LOG_PATH); // 管理者ログ
            CONFIG_ERROR_LOG = new CustomConfig(plugin, true, ERROR_LOG_PATH); // エラーログ
            CONFIG_BATCH_LOG = new CustomConfig(plugin, true, BATCH_LOG_PATH); // バッチログ
            CONFIG_WORLD_LOG = new CustomConfig(plugin, true, WORLD_LOG_PATH); // ワールドログ
            CONFIG_PLAYER_LOG = new CustomConfig(plugin, true, PLAYER_LOG_PATH); // プレイヤーログ
            CONFIG_DATABASE_LOG = new CustomConfig(plugin, true, DATABASE_LOG_PATH); // データベースログ
            CONFIG_EMERGENCY_LOG = new CustomConfig(plugin, true, EMERGENCY_LOG_PATH); // 異常ログ
            FILE_ADMIN_LOG = CONFIG_ADMIN_LOG.getConfig(); // 管理者ログ
            FILE_ERROR_LOG = CONFIG_ERROR_LOG.getConfig(); // エラーログ
            FILE_BATCH_LOG = CONFIG_BATCH_LOG.getConfig(); // バッチログ
            FILE_WORLD_LOG = CONFIG_WORLD_LOG.getConfig(); // ワールドログ
            FILE_PLAYER_LOG = CONFIG_PLAYER_LOG.getConfig(); // プレイヤーログ
            FILE_DATABASE_LOG = CONFIG_DATABASE_LOG.getConfig(); // データベースログ
            FILE_EMERGENCY_LOG = CONFIG_EMERGENCY_LOG.getConfig(); // 異常ログ
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各Log用configの読み込みに失敗しました ]");
        }
    }
}
