package com.github.syr0ws.settings;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.SettingInfo;
import com.github.syr0ws.settings.api.filter.annotation.NotEmpty;
import com.github.syr0ws.settings.api.filter.annotation.Range;
import com.github.syr0ws.settings.sdk.SimpleFilterableSetting;

public class SettingProvider implements CommonSettingModel {

    @Range(min = 1, max = 32)
    @SettingInfo(path = "min-players")
    private final Setting<Integer> minPlayers = new SimpleFilterableSetting<>(
            "minPlayers", Integer.class
    );

    @NotEmpty
    @SettingInfo(path = "chat-format")
    private final Setting<String> chatFormat = new SimpleFilterableSetting<>(
            "chatFormat", String.class
    );

    @Override
    public Setting<Integer> getMinPlayers() {
        return this.minPlayers;
    }

    @Override
    public Setting<String> getChatFormat() {
        return this.chatFormat;
    }
}
