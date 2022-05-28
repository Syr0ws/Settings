package com.github.syr0ws.settings.plugin;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingDeclaration;
import com.github.syr0ws.settings.api.filter.annotation.Min;
import com.github.syr0ws.settings.api.filter.annotation.NotEmpty;
import com.github.syr0ws.settings.api.filter.annotation.Range;
import com.github.syr0ws.settings.common.SimpleFilterableSetting;

public class SettingProviderExample implements SettingModelExample {

    @Range(min = 1, max = 32)
    @SettingDeclaration(path = "min-players")
    private final Setting<Integer> minPlayers = new SimpleFilterableSetting<>("minPlayers", Integer.class);

    @NotEmpty
    @SettingDeclaration(path = "chat-format")
    private final Setting<String> chatFormat = new SimpleFilterableSetting<>("chatFormat", String.class);

    @Min(value = 0)
    @SettingDeclaration(path = "default-money")
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
