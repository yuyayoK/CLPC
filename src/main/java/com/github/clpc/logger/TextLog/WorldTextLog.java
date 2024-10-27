package com.github.clpc.logger.TextLog;

import java.time.LocalDateTime;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.*;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * ワールドログを出力するクラス
 */
public class WorldTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得

    /**
     * ワールドログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        FILE_WORLD_LOG.set(NOW + ".ログの種類", "【-ワールドログ-】");
        FILE_WORLD_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_WORLD_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_WORLD_LOG.set(NOW + ".内容", MESSAGE);
        CONFIG_WORLD_LOG.saveConfig();
    }
}
