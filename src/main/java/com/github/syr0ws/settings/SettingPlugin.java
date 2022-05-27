package com.github.syr0ws.settings;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingAnalyzer;
import com.github.syr0ws.settings.api.SettingDescriptor;
import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoaderFactory;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.sdk.SimpleSettingAnalyzer;
import com.github.syr0ws.settings.sdk.file.SimpleSettingLoader;
import com.github.syr0ws.settings.sdk.file.SimpleSettingValueLoaderFactory;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterFactory;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class SettingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        YamlConfiguration config = new YamlConfiguration();
        config.set("min-players", 32);
        config.set("chat-format", "format");

        SettingFilterFactory filterFactory = new SimpleSettingFilterFactory();
        SettingProvider provider = new SettingProvider();

        SettingAnalyzer analyzer = new SimpleSettingAnalyzer(filterFactory);
        Set<SettingDescriptor<?>> descriptors = analyzer.analyze(provider);

        SettingValueLoaderFactory loaderFactory = new SimpleSettingValueLoaderFactory();
        SettingLoader loader = new SimpleSettingLoader(loaderFactory);

        loader.load(descriptors, config);

        Setting<Integer> setting1 = provider.getMinPlayers();
        setting1.setValue(64);

        Setting<String> setting2 = provider.getChatFormat();
        setting2.setValue("");

        System.out.println(setting1.getValue());
        System.out.println(setting2.getValue());
    }
}
