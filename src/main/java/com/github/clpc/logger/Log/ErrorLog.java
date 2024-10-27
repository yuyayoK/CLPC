package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.ErrorDiscordLog;
import com.github.clpc.logger.TextLog.ErrorTextLog;
import org.bukkit.entity.Player;

/**
 * エラーログを出力するクラス
 */
public class ErrorLog {
    private final Player player;

    public ErrorLog(Player player) {
        this.player = player;
    }

    /**
     * エラーログを出力するメソッド
     * 「/ErrorLog [PLUGIN_NAME] [MESSAGE]」コマンドを検出
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        // テキストエラーログを出力
        new ErrorTextLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
        // Discordエラーログを出力
        new ErrorDiscordLog(player).outPutLog(PLUGIN_NAME, MESSAGE);
    }
}
