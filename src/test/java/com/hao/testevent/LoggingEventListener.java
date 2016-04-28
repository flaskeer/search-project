package com.hao.testevent;

import com.hao.event.SubScribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 2016/4/28.
 */
public class LoggingEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingEventListener.class);

    private static final String SUFFIX = BeanEvent.class.getSimpleName();

    @SubScribe
    public void onEvent(BeanEvent e) {
        String className = e.getClass().getSimpleName();
        int i = className.lastIndexOf(SUFFIX);
        String subclassSuffix = className.substring(0,i);
        LOGGER.info("{} bean '{}' [{}]",subclassSuffix,e.getBeanName(),e.getBean());
    }

}
