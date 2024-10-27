package com.github.clpc.logger.TextLog;

import org.bukkit.entity.Player;

import java.time.LocalDateTime;

import static com.github.clpc.logger.operationconst.PluginConst.LOG_TIME;
import static com.github.clpc.logger.operationconst.TextLogConst.*;
import static com.github.clpc.logger.operationconst.WorldConst.WORLD_NAME;

/**
 * プレイヤーログを出力するクラス
 */
public class PlayerTextLog {
    private final String NOW = LocalDateTime.now().format(LOG_TIME); // システム日付を取得
    private final String UUID;
    private final String MCID;

    public PlayerTextLog(Player player) {
        this.UUID = String.valueOf(player.getUniqueId());
        this.MCID = player.getName();
    }

    /**
     * プレイヤーログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        FILE_PLAYER_LOG.set(NOW + ".ログの種類", "【-プレイヤーログ-】");
        FILE_PLAYER_LOG.set(NOW + ".ワールド名", "##### " + WORLD_NAME + " #####");
        FILE_PLAYER_LOG.set(NOW + ".プラグイン名", "##### " + PLUGIN_NAME + " #####");
        FILE_PLAYER_LOG.set(NOW + ".プレイヤー", MCID + "（" + UUID + "）");
        FILE_PLAYER_LOG.set(NOW + ".作業内容", MESSAGE);
        CONFIG_PLAYER_LOG.saveConfig();
    }
}
