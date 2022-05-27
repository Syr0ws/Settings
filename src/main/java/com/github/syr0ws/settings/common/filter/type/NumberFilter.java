package com.github.syr0ws.settings.common.filter.type;

import com.github.syr0ws.settings.api.filter.SettingFilter;

public abstract class NumberFilter implements SettingFilter<Number> {

    protected int compare(int reference, Number number) {

        if(number instanceof Integer)
            return Integer.compare(reference, number.intValue());

        if(number instanceof Float)
            return Float.compare(reference, number.floatValue());

        if(number instanceof Double)
            return Double.compare(reference, number.doubleValue());

        if(number instanceof Long)
            return Long.compare(reference, number.longValue());

        throw new IllegalArgumentException("Number not supported.");
    }

    @Override
    public Class<Number> getValueType() {
        return Number.class;
    }
}
