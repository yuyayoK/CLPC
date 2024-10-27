package com.github.clpc.logger;

import com.github.clpc.logger.common.SendConsoleMessage;
import com.github.clpc.logger.common.StartUp;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;

import static com.github.clpc.logger.operationconst.DiscordLogConst.jda;
import static com.github.clpc.logger.operationconst.PluginConst.PLUGIN_NAME;
import static com.github.clpc.logger.operationconst.PluginConst.plugin;

public final class Logger extends JavaPlugin implements Listener {

    /**
     * プラグイン起動時（Plugin startup logic）
     */
    @Override
    public void onEnable() {
        plugin = this;
        new SendConsoleMessage().normalMessage("[ " + PLUGIN_NAME + "用プラグインを起動します ]"); // プラグイン起動開始メッセージをコンソールに出力
        new StartUp().setUp(); // 最初のセットアップを実施
        getServer().getPluginManager().registerEvents(this, this); // イベント検知を有効化
        new SendConsoleMessage().normalMessage("[ " + PLUGIN_NAME + "用プラグインを起動しました ]"); // プラグイン起動完了メッセージをコンソールに出力
    }

    /**
     * プラグイン終了時（Plugin shutdown logic）
     */
    @Override
    public void onDisable() {
        // Initial shutdown, allowing for some RestActions to still go through
        jda.shutdown();
        // Wait up to 10 seconds for requests to finish
        try {
            if (!jda.awaitShutdown(Duration.ofSeconds(10))) {
                jda.shutdownNow(); // Cancel request queue
                jda.awaitShutdown(); // Wait until shutdown is complete (indefinitely)
            }
        } catch (InterruptedException e) {
            new SendConsoleMessage().emergencyMessage("[ JDAシャットダウンに失敗しました。 ]"); // メッセージをコンソールに出力
        }
    }
}
