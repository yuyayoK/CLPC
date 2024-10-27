package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.AdminDiscordLog;
import com.github.clpc.logger.TextLog.AdminTextLog;
import org.bukkit.entity.Player;

/**
 * 管理者ログを出力するクラス
 */
public class AdminLog {
    private final Player player;

    public AdminLog(Player player) {
        this.player = player;
    }

    /**
     * 管理者ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        // テキスト管理者ログを出力
        new AdminTextLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
        // Discord管理者ログを出力
        new AdminDiscordLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
    }
}
