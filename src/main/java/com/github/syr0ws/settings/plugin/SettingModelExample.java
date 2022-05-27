package com.github.syr0ws.settings.plugin;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.model.SettingModel;

public interface SettingModelExample extends SettingModel {

    Setting<Integer> getMinPlayers();

    Setting<String> getChatFormat();
}
