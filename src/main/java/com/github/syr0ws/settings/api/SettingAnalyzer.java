package com.github.syr0ws.settings.api;

import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.api.model.SettingModel;

import java.util.Set;

public interface SettingAnalyzer {

    Set<SettingDescriptor<?>> analyze(SettingModel model);

    SettingFilterFactory getSettingFilterFactory();
}
