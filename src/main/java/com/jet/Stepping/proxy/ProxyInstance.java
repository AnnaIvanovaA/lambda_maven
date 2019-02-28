package com.jet.Stepping.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyInstance {
    public static void main(String[] args) {
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                ProxyInstance.class.getClassLoader(),
                new Class[] { Map.class },
                new DynamicInvocationHandler());

        System.out.println(proxyInstance.get("hello"));
        //proxyInstance.put("hello", "world");


        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyInstance.class.getClassLoader(), new Class[] { Map.class },
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyInstance.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();

    }
}
