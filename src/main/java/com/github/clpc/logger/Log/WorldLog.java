package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.WorldDiscordLog;
import com.github.clpc.logger.TextLog.WorldTextLog;

import java.util.HashMap;

/**
 * ワールドログを出力するクラス
 */
public class WorldLog {

    /**
     * ワールドログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE    作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE){
        // テキストワールドログを出力
        new WorldTextLog().outPutLog(PLUGIN_NAME, MESSAGE);
        // Discordワールドログを出力
        new WorldDiscordLog().outPutLog(PLUGIN_NAME, MESSAGE);
    }

}
