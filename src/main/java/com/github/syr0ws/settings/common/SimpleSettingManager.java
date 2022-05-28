package com.github.syr0ws.settings.common;

import com.github.syr0ws.settings.api.SettingAnalyzer;
import com.github.syr0ws.settings.api.SettingDescriptor;
import com.github.syr0ws.settings.api.SettingManager;
import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.model.SettingModel;
import com.github.syr0ws.settings.common.file.SimpleSettingLoader;
import com.github.syr0ws.settings.common.file.SimpleSettingValueLoaderFactory;
import com.github.syr0ws.settings.common.filter.SimpleSettingFilterFactory;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class SimpleSettingManager implements SettingManager {

    private final SettingLoader loader;
    private final SettingAnalyzer analyzer;

    private SimpleSettingManager(SettingLoader loader, SettingAnalyzer analyzer) {
        this.loader = loader;
        this.analyzer = analyzer;
    }

    @Override
    public void loadSettings(SettingModel model, ConfigurationSection section) {
        Set<SettingDescriptor<?>> descriptors = this.analyzer.analyze(model);
        this.loader.load(descriptors, section);
    }

    @Override
    public SettingAnalyzer getSettingAnalyzer() {
        return this.analyzer;
    }

    @Override
    public SettingLoader getSettingLoader() {
        return this.loader;
    }

    public static class Builder {

        private SettingLoader loader;
        private SettingAnalyzer analyzer;

        public Builder withLoader(SettingLoader loader) {
            this.loader = loader;
            return this;
        }

        public Builder withAnalyzer(SettingAnalyzer analyzer) {
            this.analyzer = analyzer;
            return this;
        }

        public SettingManager build() {

            if(this.loader == null) {
                SimpleSettingValueLoaderFactory factory = new SimpleSettingValueLoaderFactory();
                this.loader = new SimpleSettingLoader(factory);
            }

            if(this.analyzer == null) {
                SimpleSettingFilterFactory factory = new SimpleSettingFilterFactory();
                this.analyzer = new SimpleSettingAnalyzer(factory);
            }

            return new SimpleSettingManager(this.loader, this.analyzer);
        }
    }
}
