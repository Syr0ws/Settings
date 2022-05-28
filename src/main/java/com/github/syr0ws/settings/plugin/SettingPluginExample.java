package com.github.syr0ws.settings.plugin;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingManager;
import com.github.syr0ws.settings.common.SimpleSettingManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;

public class SettingPluginExample extends JavaPlugin {

    @Override
    public void onEnable() {

        super.saveDefaultConfig();

        SettingModelExample model = new SettingProviderExample();
        SettingManager manager = new SimpleSettingManager.Builder().build();

        // Loading default settings to ensure that they have a valid default value.
        manager.loadSettings(model, this.getDefaultConfig());
        this.displaySettings(model);

        // Loading user settings.
        manager.loadSettings(model, super.getConfig());
        this.displaySettings(model);
    }

    private void displaySettings(SettingModelExample model) {

        Setting<Integer> minPlayersSetting = model.getMinPlayers();
        Setting<String> chatFormatSetting = model.getChatFormat();
        Setting<Double> defaultMoneySetting = model.getDefaultMoney();

        System.out.println(minPlayersSetting.getName() + " : " + minPlayersSetting.getValue());
        System.out.println(chatFormatSetting.getName() + " : " + chatFormatSetting.getValue());
        System.out.println(defaultMoneySetting.getName() + " : " + defaultMoneySetting.getValue());
    }

    private YamlConfiguration getDefaultConfig() {

        InputStream stream = super.getResource("config.yml");

        if(stream == null)
            throw new NullPointerException("Resource 'config.yml' not found.");

        InputStreamReader reader = new InputStreamReader(stream);

        return YamlConfiguration.loadConfiguration(reader);
    }
}
