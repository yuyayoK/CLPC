package com.github.clpc.logger.operationconst;

import com.github.clpc.logger.common.SendConsoleMessage;

import static com.github.clpc.logger.operationconst.ConfigConst.FILE_WORLD_SETTING;

public class WorldConst {

    public static String WORLD_NAME; // ワールド名


    /**
     * 各ワールド用Constを読み込み
     */
    public void loadWorldConst() {
        try {
            WORLD_NAME = FILE_WORLD_SETTING.getString("WORLD_NAME");
        } catch (Exception e) {
            new SendConsoleMessage().emergencyMessage("[ 各ワールド用Constの読み込みに失敗しました ]");
        }
    }
}
