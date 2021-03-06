package com.github.syr0ws.settings.api.file;

import com.github.syr0ws.settings.api.SettingDescriptor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public interface SettingLoader {
    void load(Set<SettingDescriptor<?>> descriptors, ConfigurationSection config);

    SettingValueLoaderFactory getSettingValueLoaderFactory();
}
