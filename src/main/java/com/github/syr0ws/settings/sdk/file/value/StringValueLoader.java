package com.github.syr0ws.settings.sdk.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.file.FileConfiguration;

public class StringValueLoader extends AbstractSettingValueLoader<String> {

    @Override
    public void load(Setting<String> setting, FileConfiguration config, String path) throws SettingException {
        super.load(setting, config, path);

        if(!config.isString(path))
            throw new SettingException(String.format("Path '%s' is not a String or doesn't exist.", path));

        String value = config.getString(path, "");
        setting.setValue(value);
    }
}
