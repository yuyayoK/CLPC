package com.github.clpc.logger.common;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class CustomConfig {
    private FileConfiguration config;
    private final File configFile;
    private final String file;
    private final Boolean commonFlag;
    private final Plugin plugin;

    public CustomConfig(Plugin plugin) {
        this(plugin, false, "config.yml");
    }

    public CustomConfig(Plugin plugin, Boolean commonFlag, String fileName) {
        this.plugin = plugin;
        this.file = fileName;
        this.commonFlag = commonFlag;
        if(commonFlag) configFile = new File(file);
        else configFile = new File(plugin.getDataFolder(), file);
    }

    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            try {
                if(commonFlag) {
                    configFile.createNewFile();
                    if(file.equals("database_setting.yml")) {
                        config = getConfig();
                        config.set("host", "");
                        config.set("port", "");
                        config.set("database", "");
                        config.set("username", "");
                        config.set("password", "");
                        config.save(configFile);
                    }
                }
                else plugin.saveResource(file, false);
            }catch (Exception ignored) {  }
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        final InputStream defConfigStream = plugin.getResource(file);
        if (defConfigStream == null) return;
        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
    }

    public FileConfiguration getConfig() {
        if (config == null) reloadConfig();
        return config;
    }

    public void saveConfig() {
        if (config == null) return;
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not SAVE config to " + configFile, ex);
        }
    }

    public String getFileName(){
        return file;
    }
}
