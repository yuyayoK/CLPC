package com.github.clpc.logger.Log;

import com.github.clpc.logger.DiscordLog.MoneyDiscordLog;
import com.github.clpc.logger.TextLog.MoneyTextLog;
import org.bukkit.entity.Player;

public class MoneyLog {
    private final Player player;

    public MoneyLog(Player player) {
        this.player = player;
    }

    /**
     * 管理者ログを出力するメソッド
     * @param PLUGIN_NAME プラグイン名
     * @param MESSAGE 作業内容
     */
    public void outPutLog(String PLUGIN_NAME, String MESSAGE, String BEFORE, String AFTER, String VALUE) {
        // テキスト管理者ログを出力
        new MoneyTextLog(player).outPutLog(PLUGIN_NAME, MESSAGE, BEFORE, AFTER, VALUE);
        // Discord管理者ログを出力
        new MoneyDiscordLog(player).outPutLog(PLUGIN_NAME, MESSAGE, BEFORE, AFTER, VALUE);
    }
}
