package com.github.clpc.logger.TextLog;

import com.github.clpc.logger.common.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_TEXT_LOG_PATH;
import static com.github.clpc.logger.operationconst.PluginConst.*;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * 金銭ログを出力するクラス
 */
public class MoneyTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String TODAY = LocalDateTime.now().format(LOG_FILE_NAME);
    private final String UUID;
    private final String MCID;

    public MoneyTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * 金銭ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     * @param BEFORE 増減前
     * @param AFTER 増減後
     * @param VALUE 増減値
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE, String BEFORE, String AFTER, String VALUE) {
        String MONEY_LOG_PATH = FILE_TEXT_LOG_PATH.getString("MONEY_LOG") + "/" + UUID + "/" + MCID + "/" + TODAY + ".yml";
        new CustomConfig(plugin, true, MONEY_LOG_PATH).saveDefaultConfig();
        CustomConfig CONFIG_MONEY_LOG = new CustomConfig(plugin, true, MONEY_LOG_PATH);
        FileConfiguration FILE_MONEY_LOG = CONFIG_MONEY_LOG.getConfig();
        FILE_MONEY_LOG.set(NOW + ".ログの種類", "【-金銭ログ-】");
        FILE_MONEY_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_MONEY_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_MONEY_LOG.set(NOW + ".プレイヤー名", MCID + "（" + UUID + "）");
        FILE_MONEY_LOG.set(NOW + ".増減内容", MESSAGE);
        FILE_MONEY_LOG.set(NOW + ".増減前", BEFORE);
        FILE_MONEY_LOG.set(NOW + ".増減後", AFTER);
        FILE_MONEY_LOG.set(NOW + ".増減値", VALUE);
        CONFIG_MONEY_LOG.saveConfig();
    }
}
