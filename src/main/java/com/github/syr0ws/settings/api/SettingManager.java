package com.github.syr0ws.settings.api;

import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoaderFactory;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.api.model.SettingModel;
import org.bukkit.configuration.ConfigurationSection;

public interface SettingManager {

    void loadSettings(SettingModel model, ConfigurationSection section);

    SettingFilterFactory getSettingFilterFactory();

    SettingValueLoaderFactory getSettingLoaderFactory();

    SettingAnalyzer getSettingAnalyzer();

    SettingLoader getSettingLoader();
}
