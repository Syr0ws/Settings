package com.github.syr0ws.settings.common.file;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingDescriptor;
import com.github.syr0ws.settings.api.exception.SettingException;
import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoaderFactory;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class SimpleSettingLoader implements SettingLoader {

    private final SettingValueLoaderFactory factory;

    public SimpleSettingLoader(SettingValueLoaderFactory factory) {

        if(factory == null)
            throw new IllegalArgumentException("SettingValueLoaderFactory cannot be null.");

        this.factory = factory;
    }

    @Override
    public void load(Set<SettingDescriptor<?>> descriptors, ConfigurationSection config) {

        for(SettingDescriptor<?> descriptor : descriptors) {

            String path = descriptor.getPath();
            Setting<?> setting = descriptor.getSetting();

            // Loading the setting.
            try { this.loadSetting(setting, config, path);
            } catch (SettingException exception) { exception.printStackTrace(); }
        }
    }

    private <T> void loadSetting(Setting<T> setting, ConfigurationSection config, String path) throws SettingException {

        Class<T> type = setting.getValueType();

        // Checking that the type of the value of the setting is supported
        // and can be loaded from a configuration file.
        if(!this.factory.isSupported(type)) {
            String message = String.format("Type '%s' is not supported. To fix it, register a custom value loader into the factory.", type.getSimpleName());
            throw new SettingException(message);
        }

        SettingValueLoader<T> loader = this.factory.getLoader(type);
        loader.load(setting, config, path);
    }
}
