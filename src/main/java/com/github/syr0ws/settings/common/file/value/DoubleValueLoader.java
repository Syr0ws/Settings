package com.github.syr0ws.settings.common.file.value;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.exception.SettingException;
import org.bukkit.configuration.ConfigurationSection;

public class DoubleValueLoader extends AbstractSettingValueLoader<Double> {

    @Override
    public void load(Setting<Double> setting, ConfigurationSection config, String path) throws SettingException {
        super.load(setting, config, path);

        if(!config.isDouble(path))
            throw new SettingException(String.format("Path '%s' is not a double or doesn't exist.", path));

        double value = config.getDouble(path, 0d);

        setting.setValue(value);
    }
}
