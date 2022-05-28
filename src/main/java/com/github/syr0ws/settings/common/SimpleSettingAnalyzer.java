package com.github.syr0ws.settings.common;

import com.github.syr0ws.settings.api.*;
import com.github.syr0ws.settings.api.exception.SettingException;
import com.github.syr0ws.settings.api.filter.SettingFilter;
import com.github.syr0ws.settings.api.filter.SettingFilterFactory;
import com.github.syr0ws.settings.api.model.SettingModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class SimpleSettingAnalyzer implements SettingAnalyzer {

    private final SettingFilterFactory factory;

    public SimpleSettingAnalyzer(SettingFilterFactory factory) {

        if(factory == null)
            throw new IllegalArgumentException("SettingFilterFactory cannot be null.");

        this.factory = factory;
    }

    @Override
    public Set<SettingDescriptor<?>> analyze(SettingModel model) {

        Set<SettingDescriptor<?>> descriptors = new HashSet<>();

        // The use of the declared fields method enables to handle fields
        // with different visibility.
        Field[] fields = model.getClass().getDeclaredFields();

        for(Field field : fields) {

            // Checking if the field is a setting.
            if(!this.isSettingDeclaration(field)) continue;

            Setting<?> setting = null;

            // Retrieving the setting.
            try { setting = this.getSetting(field, model);
            } catch (SettingException exception) { exception.printStackTrace();}

            if(setting == null) continue;

            // Applying filters if the setting is filterable.
            try { this.applyFilters(setting, field);
            } catch (SettingException exception) { exception.printStackTrace(); }

            // Building the descriptor.
            SettingDescriptor<?> descriptor = this.getDescriptor(setting, field);

            descriptors.add(descriptor);
        }

        return descriptors;
    }

    @Override
    public SettingFilterFactory getSettingFilterFactory() {
        return this.factory;
    }

    private Setting<?> getSetting(Field field, SettingModel model) throws SettingException {

        Object object;

        boolean accessible = field.isAccessible();

        try {
            // A setting may be private. It's necessary to make
            // it accessible.
            field.setAccessible(true);
            object = field.get(model);

        } catch (SecurityException | IllegalAccessException exception) {
            throw new SettingException("Cannot access setting.", exception);

        } finally {
            // After retrieving the setting, it's important
            // to restore its accessibility state.
            field.setAccessible(accessible);
        }

        // A Setting must be declared with a @SettingInfo annotation.
        if(!(object instanceof Setting))
            throw new SettingException("A field with a @SettingInfo annotation is not a setting.");

        return (Setting<?>) object;
    }

    private <T> void applyFilters(Setting<T> setting, Field field) throws SettingException {

        if(!(setting instanceof FilterableSetting))
            return;

        FilterableSetting<T> filterable = (FilterableSetting<T>) setting;

        Set<SettingFilter<?>> filters = this.getFilters(field);

        for(SettingFilter<?> filter : filters) {

            // Checking if the filter can handle the value provided
            // by the setting by comparing their types.
            if(!filter.getValueType().isAssignableFrom(filterable.getValueType()))
                throw new SettingException(String.format("Invalid filter for setting %s.", setting.getName()));

            @SuppressWarnings("unchecked")
            SettingFilter<T> converted = (SettingFilter<T>) filter;

            // Adding the filter to the setting.
            filterable.getFilterManager().addFilter(converted);
        }
    }

    private <T> SettingDescriptor<T> getDescriptor(Setting<T> setting, Field field) {

        SettingDeclaration declaration = this.getSettingDeclaration(field);

        return new SimpleSettingDescriptor<>(declaration.path(), setting);
    }

    private Set<SettingFilter<?>> getFilters(Field field) {

        Set<SettingFilter<?>> filters = new HashSet<>();

        Annotation[] annotations = field.getAnnotations();

        for(Annotation annotation : annotations) {

            // Other annotations may be present. This is to ignore them.
            if(!this.factory.isSupported(annotation)) continue;

            SettingFilter<?> filter = this.factory.getFilter(annotation);

            filters.add(filter);
        }
        return filters;
    }

    private SettingDeclaration getSettingDeclaration(Field field) {
        return field.getAnnotation(SettingDeclaration.class);
    }

    private boolean isSettingDeclaration(Field field) {
        return field.isAnnotationPresent(SettingDeclaration.class);
    }
}