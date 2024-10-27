package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.ExceptionErrorDiscordLog;
import com.github.clpc.logger.TextLog.ExceptionErrorTextLog;
import org.bukkit.entity.Player;

/**
 * 例外エラーログを出力するクラス
 */
public class ExceptionErrorLog {
    private final Player player;

    public ExceptionErrorLog(Player player) {
        this.player = player;
    }

    /**
     * 例外エラーログを出力するメソッド
     * 「/ExceptionErrorLog [PLUGIN_NAME] [MESSAGE] [EXCEPTION]」コマンドを検出
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 例外エラー内容
     * @param EXCEPTION 例外エラー内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE, String EXCEPTION){
        // テキストエラーログを出力
        new ExceptionErrorTextLog(player).outPutLog(PLUGIN_NAME, MESSAGE, EXCEPTION);
        // Discordエラーログを出力
        new ExceptionErrorDiscordLog(player).outPutLog(PLUGIN_NAME, MESSAGE, EXCEPTION);
    }
}
