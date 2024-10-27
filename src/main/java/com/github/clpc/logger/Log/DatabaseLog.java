package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.DatabaseDiscordLog;
import com.github.clpc.logger.TextLog.DatabaseTextLog;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * データベースログを出力するクラス
 */
public class DatabaseLog {
    private final Player player;

    /**
     * データベースログを出力するクラス
     * @param player プレイヤー情報
     */
    public DatabaseLog(Player player) {
        this.player = player;
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
        // テキスト管理者ログを出力
        new DatabaseTextLog(player).outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, QUERY, PARAM);
        // Discord管理者ログを出力
        new DatabaseDiscordLog(player).outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, QUERY, PARAM);
    }
}
