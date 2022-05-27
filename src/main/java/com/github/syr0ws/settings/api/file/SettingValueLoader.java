package com.github.syr0ws.settings.api.file;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.ConfigurationSection;

public interface SettingValueLoader<T> {

    void load(Setting<T> setting, ConfigurationSection config, String path) throws SettingException;
}
