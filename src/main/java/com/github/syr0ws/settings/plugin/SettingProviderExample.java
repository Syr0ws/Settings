package com.github.syr0ws.settings.plugin;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.ConfigurableSetting;
import com.github.syr0ws.settings.api.filter.annotation.Min;
import com.github.syr0ws.settings.api.filter.annotation.NotEmpty;
import com.github.syr0ws.settings.api.filter.annotation.Range;
import com.github.syr0ws.settings.common.SimpleFilterableSetting;

public class SettingProviderExample implements SettingModelExample {

    @Range(min = 1, max = 32)
    @ConfigurableSetting(path = "min-players")
    private final Setting<Integer> minPlayers = new SimpleFilterableSetting<>("minPlayers", Integer.class);

    @NotEmpty
    @ConfigurableSetting(path = "chat-format")
    private final Setting<String> chatFormat = new SimpleFilterableSetting<>("chatFormat", String.class);

    @Min(value = 0)
    @ConfigurableSetting(path = "default-money")
    private final Setting<Double> defaultMoney = new SimpleFilterableSetting<>("defaultMoney", Double.class);

    @Override
    public Setting<Integer> getMinPlayers() {
        return this.minPlayers;
    }

    @Override
    public Setting<String> getChatFormat() {
        return this.chatFormat;
    }

    @Override
    public Setting<Double> getDefaultMoney() {
        return this.defaultMoney;
    }
}
