package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.PlayerDiscordLog;

import com.github.clpc.logger.TextLog.PlayerTextLog;
import org.bukkit.entity.Player;

public class PlayerLog {
    private final Player player;

    public PlayerLog(Player player) {
        this.player = player;
    }

    /**
     * 管理者ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        // テキスト管理者ログを出力
        new PlayerTextLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
        // Discord管理者ログを出力
        new PlayerDiscordLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
    }
}
