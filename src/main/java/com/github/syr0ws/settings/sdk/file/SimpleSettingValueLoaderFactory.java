package com.github.syr0ws.settings.sdk.file;

import com.github.syr0ws.settings.api.exception.SettingException;
import com.github.syr0ws.settings.api.file.SettingValueLoader;
import com.github.syr0ws.settings.api.file.SettingValueLoaderFactory;
import com.github.syr0ws.settings.sdk.file.value.*;

import java.util.HashMap;
import java.util.Map;

public class SimpleSettingValueLoaderFactory implements SettingValueLoaderFactory {

    private static final Map<Class<?>, SettingValueLoader<?>> LOADERS = new HashMap<>();

    static {
        addLoader(String.class, new StringValueLoader());
        addLoader(Boolean.class, new BooleanValueLoader());
        addLoader(Integer.class, new IntegerValueLoader());
        addLoader(Long.class, new LongValueLoader());
        addLoader(Float.class, new FloatValueLoader());
        addLoader(Double.class, new DoubleValueLoader());
    }

    @Override
    public <T> SettingValueLoader<T> getLoader(Class<T> type) {

        if(!this.isSupported(type))
            throw new SettingException(String.format("No loader found for type '%s'.", type.getSimpleName()));

        // This is in fact checked because we handle the way types and loaders
        // are added to the map. However, the compiler can't prove it.
        @SuppressWarnings("unchecked")
        SettingValueLoader<T> loader = (SettingValueLoader<T>) LOADERS.get(type);

        return loader;
    }

    @Override
    public <T> boolean isSupported(Class<T> type) {
        return LOADERS.containsKey(type);
    }

    public static <T> void addLoader(Class<T> type, SettingValueLoader<T> loader) {

        if(type == null)
            throw new IllegalArgumentException("Type cannot be null.");

        if(loader == null)
            throw new IllegalArgumentException("SettingValueLoader cannot be null.");

        LOADERS.put(type, loader);
    }
}
