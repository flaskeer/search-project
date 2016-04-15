package com.hao.plugin;

/**
 * Created by user on 2016/4/15.
 */
public class PluginException extends RuntimeException{
    public PluginException() {
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }


}
