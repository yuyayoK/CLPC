package com.github.clpc.logger.common;

import com.github.clpc.logger.operationconst.*;
import net.dv8tion.jda.api.JDABuilder;

import static com.github.clpc.logger.operationconst.DiscordLogConst.TOKEN;
import static com.github.clpc.logger.operationconst.DiscordLogConst.jda;

public class StartUp {

    /**
     * 各固定値の読み込みを実施
     */
    public void setUp() {
        new ConfigConst().loadConfig();       // config.ymlを読み込み
        new ConfigConst().loadGUIConfig();    // 各GUI用configを読み込み（loadConfig()の後に実施）
        new ConfigConst().loadPluginConfig(); // 各プラグイン用configを読み込み（loadConfig()の後に実施）
        new ConfigConst().loadCommonConfig(); // 各共通用configを読み込み
        new PluginConst().loadPluginConst();  // 各プラグイン用Constを読み込み（loadConfig()の後に実施）
        new WorldConst().loadWorldConst();    // 各ワールド用Constを読み込み（loadConfig()の後に実施）
        new TextLogConst().loadLogConfig();   // 各TextLog用Constを読み込み（loadCommonConfig()の後に実施）
        new DiscordLogConst().loadDiscordLogConst(); // 各DiscordLog用Constを読み込み（loadCommonConfig()の後に実施）
        startDiscordBot(); // DiscordBotを起動（loadDatabaseConst()の後に実施）
    }

    /**
     * DiscordBotを起動
     */
    public static void startDiscordBot() {
        try {
            // トークンからJDAを取得
            jda = JDABuilder.createDefault(TOKEN).build();
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ DiscordBotの起動に失敗しました ]");
        }
    }
}
