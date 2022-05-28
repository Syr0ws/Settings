package com.github.syr0ws.settings.api;

import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.model.SettingModel;
import org.bukkit.configuration.ConfigurationSection;

public interface SettingManager {

    void loadSettings(SettingModel model, ConfigurationSection section);

    SettingAnalyzer getSettingAnalyzer();

    SettingLoader getSettingLoader();
}
