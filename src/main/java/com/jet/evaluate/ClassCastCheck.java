package com.jet.evaluate;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassCastCheck {
    public static void main(String[] args) throws Exception {

        URL basePath = new URL("file:///home/anna.ivanova/Documents/projects/Idea_UI/lambdas/lambda_maven/target/classes/");


        Object instance = getClassInstance(Singleton.class);
        System.out.println(instance);
        //
        Object instance2 = getClassInstance(
                new URLClassLoader(new URL[]{basePath}, null)
                        .loadClass("com.jet.evaluate.Singleton")
        );
        System.out.println(instance2);
        //
        Object instance3 = getClassInstance(
                new URLClassLoader(new URL[]{basePath}, null)
                        .loadClass("com.jet.evaluate.Singleton")
        );
        System.out.println(instance3);

        // Only the 1st cast is ok
        Singleton testCast1 = (Singleton) instance;
        System.out.println("1st cast ok");
        Singleton testCast2 = (Singleton) instance2;
        System.out.println("2nd cast ok");
        Singleton testCast3 = (Singleton) instance3;
        System.out.println("3rd cast ok");


    }

    private static Object getClassInstance(Class clazz) throws Exception {
        Method method = clazz.getMethod("getInstance");
        method.setAccessible(true);
        return method.invoke(null);
    }


    /*Evaluate -- class cast exception
    Integer integer = getT();
    integer = integer+10;
    */
    public static <T> T getT() {
        return (T) "HelloWorld";
    }

}
    class Singleton {

        private static final Singleton INSTANCE = new Singleton();

        public static Singleton getInstance() {
            return INSTANCE;
        }

        private Singleton() {
            Exception e = new Exception();
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (!"<clinit>".equals(stackTrace[1].getMethodName())) {
                throw new IllegalStateException("You shall not instanciate the Singleton twice !",e);
            }
        }

        public void sayHello() {
            System.out.println("Hello World ! " + this);
        }

    }




