package com.github.syr0ws.settings.sdk.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.file.FileConfiguration;

public class BooleanValueLoader extends AbstractSettingValueLoader<Boolean> {

    @Override
    public void load(Setting<Boolean> setting, FileConfiguration config, String path) {
        super.load(setting, config, path);

        if(!config.isBoolean(path))
            throw new SettingException(String.format("Path '%s' is not a boolean or doesn't exist.", path));

        Boolean value = config.getBoolean(path);

        setting.setValue(value);
    }
}
