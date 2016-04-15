package com.hao.rpc;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

import java.util.List;
import java.util.Random;

/**
 * Created by user on 2016/4/5.
 */
public class RndomXBalance extends RandomLoadBalance{

    public static final String NAME = "randomx";

    private final Random random = new Random();

    /**
     *
     * @param invokers
     * @param url
     * @param invocation
     * @param <T>
     * @return
     */
    protected <T> Invoker<T>  doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        int length = invokers.size();
        int[] weights = new int[length];
        int totalWeight = 0;
        boolean sameWeight = true;
        for (int i = 0; i < length; i++) {
            int weight = getWeight(invokers.get(i),invocation);
            totalWeight += weight;
            if (sameWeight && i > 0 && weight != weights[i-1]) {
                sameWeight = false;
            }
            weights[i] = weight;
        }
        if (totalWeight > 0 && !sameWeight) {
            int offset = random.nextInt(totalWeight);
            for (int i = 0; i < length; i++) {
                offset -= weights[i];
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
        }
        return invokers.get(random.nextInt(length));
    }

    @Override
    protected int getWeight(Invoker<?> invoker, Invocation invocation) {
        int weight = super.getWeight(invoker,invocation);
        int idcWeight = invoker.getUrl().getParameter("",weight);
        return idcWeight;
    }
}
