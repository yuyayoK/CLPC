package com.github.clpc.logger.common;

import org.bukkit.ChatColor;

import static org.bukkit.Bukkit.getConsoleSender;

/**
 * コンソールメッセージを出力する共通クラス
 */
public class SendConsoleMessage {

    /**
     * 通常時のコンソールメッセージを出力するメソッド
     * @param message 表示するメッセージ
     */
    public void normalMessage(String message) {
        getConsoleSender().sendMessage(
            ChatColor.DARK_AQUA + "＃＃＃＃ " +
            ChatColor.YELLOW + ChatColor.UNDERLINE + message +
            ChatColor.RESET + ChatColor.DARK_AQUA  + " ＃＃＃＃"
        );
    }

    /**
     * 異常時のコンソールメッセージを出力するメソッド
     * @param message 表示するメッセージ
     */
    public void emergencyMessage(String message) {
        getConsoleSender().sendMessage(
            ChatColor.DARK_AQUA + "＃＃＃＃ " +
            ChatColor.RED + ChatColor.UNDERLINE + "【異常を検知】" +
            ChatColor.RESET + ChatColor.DARK_AQUA  + " ＃＃＃＃"
        );
        getConsoleSender().sendMessage(
            ChatColor.DARK_AQUA + "＃＃＃＃ " +
            ChatColor.YELLOW + ChatColor.UNDERLINE + message +
            ChatColor.RESET + ChatColor.DARK_AQUA  + " ＃＃＃＃"
        );
    }
}
