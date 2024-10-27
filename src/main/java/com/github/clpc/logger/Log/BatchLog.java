package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.BatchDiscordLog;
import com.github.clpc.logger.TextLog.BatchTextLog;

import java.util.HashMap;

public class BatchLog {

    /**
     * バッチログを出力するメソッド
     * @param TABLE_NAME テーブル名
     * @param MESSAGE    作業内容
     * @param QUERY      SQLクエリ
     * @param PARAM      パラメータ
     */
    public void outPutLog(String PLUGIN_NAME, String TABLE_NAME, String MESSAGE, String QUERY, HashMap<String, String> PARAM){
        // テキスト管理者ログを出力
        new BatchTextLog().outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, QUERY, PARAM);
        // Discord管理者ログを出力
        new BatchDiscordLog().outPutLog(PLUGIN_NAME, TABLE_NAME, MESSAGE, QUERY, PARAM);
    }

}
