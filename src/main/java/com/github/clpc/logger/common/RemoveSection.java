package com.github.clpc.logger.common;

public class RemoveSection {

    public String execute(String message) {
        return message
            .replace("§0", "") // 黒
            .replace("§1", "") // 濃い青
            .replace("§2", "") // 濃い緑
            .replace("§3", "") // 濃い水色
            .replace("§4", "") // 濃い赤色
            .replace("§5", "") // 濃い紫
            .replace("§6", "") // 金色
            .replace("§7", "") // 灰色
            .replace("§8", "") // 濃い灰色
            .replace("§9", "") // 青
            .replace("§a", "") // 緑
            .replace("§b", "") // 水色
            .replace("§c", "") // 赤
            .replace("§d", "") // ピンク
            .replace("§e", "") // 黄色
            .replace("§f", "") // 白色
            .replace("§l", "") // 太字
            .replace("§o", "") // 斜線
            .replace("§k", "") // ランダム文字列
            .replace("§r", "") // リセット
            .replace("§m", "") // 打ち消し線
            .replace("§n", "") // 下線
        ;
    }
}
