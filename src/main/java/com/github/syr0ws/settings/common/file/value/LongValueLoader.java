package com.github.syr0ws.settings.common.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.ConfigurationSection;

public class LongValueLoader extends AbstractSettingValueLoader<Long> {

    @Override
    public void load(Setting<Long> setting, ConfigurationSection config, String path) {
        super.load(setting, config, path);

        if(!config.isInt(path))
            throw new SettingException(String.format("Path '%s' is not an long or doesn't exist.", path));

        long value = config.getLong(path, 0L);

        setting.setValue(value);
    }
}
