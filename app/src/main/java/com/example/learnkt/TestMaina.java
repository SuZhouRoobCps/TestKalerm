package com.example.learnkt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMaina {



    public static String binaryToHex(String bin){
        return Long.toHexString(Long.parseLong(bin,2));
    }

    public void test(){

        Proxy.newProxyInstance(Object.class.getClassLoader(), new Class[]{Object.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                return null;
            }
        });

    }



}
