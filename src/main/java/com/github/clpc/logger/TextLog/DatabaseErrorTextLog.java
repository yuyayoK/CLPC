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
public class DatabaseErrorTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public DatabaseErrorTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * エラーログを出力するメソッド
     * 「/DatabaseErrorLog [PLUGIN_NAME] [TABLE_NAME] [MESSAGE] [EXCEPTION] [QUERY] [PARAM]」コマンドを検出
     * @param PLUGIN_NAME プラグイン名
     * @param TABLE_NAME  テーブル名
     * @param MESSAGE     エラー内容
     * @param EXCEPTION   エラー詳細
     * @param QUERY       SQLクエリ
     * @param PARAM       パラメータ
     */
    public void outPutLog(String PLUGIN_NAME, String TABLE_NAME, String MESSAGE, String EXCEPTION, String QUERY, HashMap<String, String> PARAM){
        FILE_ERROR_LOG.set(NOW + ".ログの種類", "【-エラーログ-】");
        FILE_ERROR_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".テーブル名", "##### " + TABLE_NAME + " #####");
        FILE_ERROR_LOG.set(NOW + ".対象者", MCID + "（" + UUID + "）");
        FILE_ERROR_LOG.set(NOW + ".エラー内容", MESSAGE);
        FILE_ERROR_LOG.set(NOW + ".エラー詳細", EXCEPTION);
        FILE_ERROR_LOG.set(NOW + ".SQLクエリ", QUERY);
        PARAM.forEach((KEY, VALUE) -> FILE_ERROR_LOG.set(NOW + ".パラメータ（" + KEY + "）", VALUE));
        CONFIG_ERROR_LOG.saveConfig();
    }
}
