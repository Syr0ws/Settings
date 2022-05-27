package com.github.syr0ws.settings.api.exception;

public class SettingException extends RuntimeException {

    public SettingException(String message) {
        super(message);
    }

    public SettingException(String message, Throwable cause) {
        super(message, cause);
    }
}
