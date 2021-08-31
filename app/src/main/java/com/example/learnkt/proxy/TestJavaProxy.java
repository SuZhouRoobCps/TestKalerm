package com.example.learnkt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJavaProxy {

    public  void main2() {
        final Animals dog=new Dog();
        //com.example.learnkt.proxy.Dog is not an interface  ERROR
        Animals animals= (Animals) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Dog.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                method.invoke(dog,args[0]);

                return null;
            }
        });

        Animals animals2= (Animals) Proxy.newProxyInstance(Animals.class.getClassLoader(), new Class[]{Animals.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                method.invoke(dog,args[0]);

                return null;
            }
        });

//        animals.eat("baba");
        animals2.sounds("hhsx");
    }
}
