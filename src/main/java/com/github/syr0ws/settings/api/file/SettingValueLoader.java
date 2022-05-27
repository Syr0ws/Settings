package com.github.syr0ws.settings.api.file;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.file.FileConfiguration;

public interface SettingValueLoader<T> {

    void load(Setting<T> setting, FileConfiguration config, String path) throws SettingException;
}
