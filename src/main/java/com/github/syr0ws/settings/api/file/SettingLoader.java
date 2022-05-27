package com.github.syr0ws.settings.api.file;

import com.github.syr0ws.settings.api.SettingDescriptor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Set;

public interface SettingLoader {

    void load(Set<SettingDescriptor<?>> descriptors, FileConfiguration config);
}
