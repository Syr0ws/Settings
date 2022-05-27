package com.github.syr0ws.settings.sdk.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.file.SettingValueLoader;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class AbstractSettingValueLoader<T> implements SettingValueLoader<T> {

    @Override
    public void load(Setting<T> setting, FileConfiguration config, String path) {

        if(setting == null)
            throw new IllegalArgumentException("Setting cannot be null.");

        if(config == null)
            throw new IllegalArgumentException("FileConfiguration cannot be null.");

        if(path == null)
            throw new IllegalArgumentException("Path cannot be null.");
    }
}
