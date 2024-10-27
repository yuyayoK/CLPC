package com.github.clpc.logger.TextLog;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.*;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * データベースログを出力するクラス
 */
public class DatabaseTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public DatabaseTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * データベースログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param TABLE_NAME テーブル名
     * @param MESSAGE 作業内容
     * @param QUERY SQLクエリ
     * @param PARAM パラメータ
     */
    public void outPutLog(String PLUGIN_NAME, String TABLE_NAME, String MESSAGE, String QUERY, HashMap<String, String> PARAM){
        FILE_DATABASE_LOG.set(NOW + ".ログの種類", "【-DBログ-】");
        FILE_DATABASE_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_DATABASE_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_DATABASE_LOG.set(NOW + ".テーブル名", "##### " + TABLE_NAME + " #####");
        FILE_DATABASE_LOG.set(NOW + ".対象者", MCID + "（" + UUID + "）");
        FILE_DATABASE_LOG.set(NOW + ".内容", MESSAGE);
        FILE_DATABASE_LOG.set(NOW + ".SQLクエリ", QUERY);
        PARAM.forEach((KEY, VALUE) -> FILE_DATABASE_LOG.set(NOW + ".パラメータ（" + KEY + "）", VALUE));
        CONFIG_DATABASE_LOG.saveConfig();
    }
}
