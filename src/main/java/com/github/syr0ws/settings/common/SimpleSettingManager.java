package com.github.syr0ws.settings.common;

import com.github.syr0ws.settings.api.SettingAnalyzer;
import com.github.syr0ws.settings.api.SettingDescriptor;
import com.github.syr0ws.settings.api.SettingManager;
import com.github.syr0ws.settings.api.file.SettingLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoaderFactory;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.api.model.SettingModel;
import com.github.syr0ws.settings.common.file.SimpleSettingLoader;
import com.github.syr0ws.settings.common.file.SimpleSettingValueLoaderFactory;
import com.github.syr0ws.settings.common.filter.SimpleSettingFilterFactory;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class SimpleSettingManager implements SettingManager {

    private final SettingFilterFactory filterFactory;
    private final SettingValueLoaderFactory loaderFactory;
    private final SettingLoader loader;
    private final SettingAnalyzer analyzer;

    private SimpleSettingManager(SettingFilterFactory filterFactory, SettingValueLoaderFactory loaderFactory,
                                SettingLoader loader, SettingAnalyzer analyzer) {

        this.filterFactory = filterFactory;
        this.loaderFactory = loaderFactory;
        this.loader = loader;
        this.analyzer = analyzer;
    }

    @Override
    public void loadSettings(SettingModel model, ConfigurationSection section) {
        Set<SettingDescriptor<?>> descriptors = this.analyzer.analyze(model);
        this.loader.load(descriptors, section);
    }

    @Override
    public SettingFilterFactory getSettingFilterFactory() {
        return this.filterFactory;
    }

    @Override
    public SettingValueLoaderFactory getSettingLoaderFactory() {
        return this.loaderFactory;
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

        private SettingFilterFactory filterFactory;
        private SettingValueLoaderFactory loaderFactory;
        private SettingLoader loader;
        private SettingAnalyzer analyzer;

        public Builder withFilterFactory(SettingFilterFactory factory) {
            this.filterFactory = factory;
            return this;
        }

        public Builder withLoaderFactory(SettingValueLoaderFactory factory) {
            this.loaderFactory = factory;
            return this;
        }

        public Builder withLoader(SettingLoader loader) {
            this.loader = loader;
            return this;
        }

        public Builder withAnalyzer(SettingAnalyzer analyzer) {
            this.analyzer = analyzer;
            return this;
        }

        public SettingManager build() {

            if(this.filterFactory == null)
                this.filterFactory = new SimpleSettingFilterFactory();

            if(this.loaderFactory == null)
                this.loaderFactory = new SimpleSettingValueLoaderFactory();

            if(this.loader == null)
                this.loader = new SimpleSettingLoader(this.loaderFactory);

            if(this.analyzer == null)
                this.analyzer = new SimpleSettingAnalyzer(this.filterFactory);

            return new SimpleSettingManager(this.filterFactory, this.loaderFactory, this.loader, this.analyzer);
        }
    }
}
