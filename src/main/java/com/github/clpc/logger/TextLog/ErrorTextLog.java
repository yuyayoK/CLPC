package com.github.clpc.logger.TextLog;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.*;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * エラーログを出力するクラス
 */
public class ErrorTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public ErrorTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * エラーログを出力するメソッド
     * 「/ErrorLog [PLUGIN_NAME] [MESSAGE]」コマンドを検出
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        FILE_ERROR_LOG.set(NOW + ".ログの種類", "【-エラーログ-】");
        FILE_ERROR_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".対象者", MCID + "（" + UUID + "）");
        FILE_ERROR_LOG.set(NOW + ".エラー内容", MESSAGE);
        CONFIG_ERROR_LOG.saveConfig();
    }
}
