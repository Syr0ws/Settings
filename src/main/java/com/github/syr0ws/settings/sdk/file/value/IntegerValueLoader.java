package com.github.syr0ws.settings.sdk.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.file.FileConfiguration;

public class IntegerValueLoader extends AbstractSettingValueLoader<Integer> {

    @Override
    public void load(Setting<Integer> setting, FileConfiguration config, String path) throws SettingException {
        super.load(setting, config, path);

        if(!config.isInt(path))
            throw new SettingException(String.format("Path '%s' is not an int or doesn't exist.", path));

        int value = config.getInt(path, 0);
        setting.setValue(value);
    }
}
