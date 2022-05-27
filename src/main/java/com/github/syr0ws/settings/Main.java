package com.github.syr0ws.settings;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingAnalyzer;
import com.github.syr0ws.settings.api.SettingDescriptor;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.sdk.SimpleSettingAnalyzer;
import com.github.syr0ws.settings.sdk.filter.SimpleSettingFilterFactory;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        SettingFilterFactory factory = new SimpleSettingFilterFactory();
        SettingProvider provider = new SettingProvider();

        SettingAnalyzer analyzer = new SimpleSettingAnalyzer(factory);
        Set<SettingDescriptor<?>> descriptors = analyzer.analyze(provider);

        Setting<Integer> setting1 = provider.getMinPlayers();
        setting1.setValue(64);

        Setting<String> setting2 = provider.getChatFormat();
        setting2.setValue("");

        System.out.println(setting1.getValue());
        System.out.println(setting2.getValue());
    }
}
