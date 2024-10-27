package com.github.clpc.logger.TextLog;

import com.github.clpc.logger.DiscordLog.AdminDiscordLog;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.CONFIG_ADMIN_LOG;
import static com.github.clpc.logger.operationconst.TextLogConst.FILE_ADMIN_LOG;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * 管理者ログを出力するクラス
 */
public class AdminTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public AdminTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * 管理者ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        FILE_ADMIN_LOG.set(NOW + ".ログの種類", "【-管理者ログ-】");
        FILE_ADMIN_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_ADMIN_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_ADMIN_LOG.set(NOW + ".管理者", MCID + "（" + UUID + "）");
        FILE_ADMIN_LOG.set(NOW + ".作業内容", MESSAGE);
        CONFIG_ADMIN_LOG.saveConfig();
    }
}
