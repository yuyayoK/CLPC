package com.github.clpc.logger.TextLog;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.CONFIG_ERROR_LOG;
import static com.github.clpc.logger.operationconst.TextLogConst.FILE_ERROR_LOG;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * エラーログを出力するクラス
 */
public class ExceptionErrorTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public ExceptionErrorTextLog(Player player) {
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
        FILE_ERROR_LOG.set(NOW + ".ログの種類", "【-エラーログ-】");
        FILE_ERROR_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".対象者", MCID + "（" + UUID + "）");
        FILE_ERROR_LOG.set(NOW + ".エラー内容", MESSAGE);
        FILE_ERROR_LOG.set(NOW + ".エラー詳細", EXCEPTION);
        CONFIG_ERROR_LOG.saveConfig();
    }

}
