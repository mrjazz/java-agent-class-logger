package com.test;

import com.sheremetov.ClassesLogger;

import java.lang.instrument.Instrumentation;

public class DemoApp {

    /**
     * Main method.
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        SomeUsedClass u = new SomeUsedClass();

        Instrumentation inst = ClassesLogger.getInstrumentation();
        System.out.println(inst);

//        Arrays
//                .stream(inst.getAllLoadedClasses())
//                .filter(i ->
//                        !i.getName().startsWith("java") &&
//                        !i.getName().startsWith("[") &&
//                        !i.getName().startsWith("sun") &&
//                        !i.getName().startsWith("jdk")
//                )
//                .forEach(System.out::println);
        
    }

}