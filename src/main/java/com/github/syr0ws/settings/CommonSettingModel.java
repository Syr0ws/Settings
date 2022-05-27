package com.github.syr0ws.settings;

import com.github.syr0ws.settings.api.Setting;
import com.github.syr0ws.settings.api.model.SettingModel;

public interface CommonSettingModel extends SettingModel {

    Setting<Integer> getMinPlayers();

    Setting<String> getChatFormat();
}
