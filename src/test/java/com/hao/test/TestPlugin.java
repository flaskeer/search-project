package com.hao.test;

import org.junit.Test;

/**
 * Created by user on 2016/4/15.
 */
public class TestPlugin {

    @Test
    public void test() {
        OutPlugin outPlugin = new OutPlugin();
        TestFactory.add(outPlugin);
        TestFactory.factory(new TT());
    }

}
