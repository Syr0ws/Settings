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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

public class SettingPluginExample extends JavaPlugin {

    @Override
    public void onEnable() {

        YamlConfiguration config = this.getDefaultConfig();
        super.saveDefaultConfig();

        // Creating the filter factory.
        SettingFilterFactory filterFactory = new SimpleSettingFilterFactory();

        // Creating the model.
        SettingModelExample model = new SettingProviderExample();

        // Creating analyzer.
        SettingAnalyzer analyzer = new SimpleSettingAnalyzer(filterFactory);

        // Analyzing the model.
        Set<SettingDescriptor<?>> descriptors = analyzer.analyze(model);

        // Creating the value loader factory.
        SettingValueLoaderFactory loaderFactory = new SimpleSettingValueLoaderFactory();

        // Creating the loader.
        SettingLoader loader = new SimpleSettingLoader(loaderFactory);

        // Loading default settings to ensure that they have a valid default value.
        System.out.println("Default values:");

        loader.load(descriptors, config);

        // Testing the default settings.
        this.displaySettings(model);

        // Loading user settings.
        loader.load(descriptors, super.getConfig());

        // Testing the user settings.
        System.out.println("User values:");

        this.displaySettings(model);
    }

    private void displaySettings(SettingModelExample model) {

        Setting<Integer> minPlayersSetting = model.getMinPlayers();
        Setting<String> chatFormatSetting = model.getChatFormat();

        System.out.println(minPlayersSetting.getName() + " : " + minPlayersSetting.getValue());
        System.out.println(chatFormatSetting.getName() + " : " + chatFormatSetting.getValue());
    }

    private YamlConfiguration getDefaultConfig() {

        InputStream stream = super.getResource("config.yml");

        if(stream == null)
            throw new NullPointerException("Resource 'config.yml' not found.");

        InputStreamReader reader = new InputStreamReader(stream);

        return YamlConfiguration.loadConfiguration(reader);
    }
}
