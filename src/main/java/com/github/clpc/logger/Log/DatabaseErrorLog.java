package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.DatabaseErrorDiscordLog;
import com.github.clpc.logger.TextLog.DatabaseErrorTextLog;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * エラーログを出力するクラス
 */
public class DatabaseErrorLog {
    private final Player player;

    public DatabaseErrorLog(Player player) {
        this.player = player;
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
        // テキストエラーログを出力
        new DatabaseErrorTextLog(player).outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, EXCEPTION, QUERY, PARAM);
        // Discordエラーログを出力
        new DatabaseErrorDiscordLog(player).outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, EXCEPTION, QUERY, PARAM);
    }
}
