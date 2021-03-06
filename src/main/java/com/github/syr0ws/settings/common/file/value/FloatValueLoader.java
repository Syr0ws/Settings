package com.github.syr0ws.settings.common.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.ConfigurationSection;

public class FloatValueLoader extends AbstractSettingValueLoader<Float> {

    @Override
    public void load(Setting<Float> setting, ConfigurationSection config, String path) {
        super.load(setting, config, path);

        if(!config.isDouble(path))
            throw new SettingException(String.format("Path '%s' is not a float or doesn't exist.", path));

        float value = (float) config.getDouble(path, 0f);

        setting.setValue(value);
    }
}
