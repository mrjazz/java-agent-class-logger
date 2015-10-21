package com.sheremetov;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.sql.Timestamp;
import java.util.Date;

public class ClassesLogger {

    public static String LOG_FILE = "classes.log";

    private static Instrumentation instrumentation;

	/**
	 * JVM hook to statically load the javaagent at startup.
	 * 
	 * After the Java Virtual Machine (JVM) has initialized, the premain method
	 * will be called. Then the real application main method will be called.
	 * 
	 * @param args
	 * @param inst
	 * @throws Exception
	 */
	public static void premain(String args, Instrumentation inst) throws Exception {
        String path = args;

        try {
            File file = new File(path);
            if (file.exists()) {
                Date date= new Date();
                long sec = new Timestamp(date.getTime()).getTime();
                file.renameTo(new File(path + "." + sec));
            } else {
                file.createNewFile();
            }
        } catch (Exception e) {
            path = LOG_FILE; // use path by default
        }

        LOG_FILE = path;

        System.out.println("Class logger initialized with logfile: " + args);
        instrumentation = inst;
        instrumentation.addTransformer(new LogClassTransformer());
	}

	/**
	 * JVM hook to dynamically load javaagent at runtime.
	 * 
	 * The agent class may have an agentmain method for use when the agent is
	 * started after VM startup.
	 * 
	 * @param args
	 * @param inst
	 * @throws Exception
	 */
	public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("Agentmain method invoked");
		instrumentation = inst;
		instrumentation.addTransformer(new LogClassTransformer());
	}

    public static Instrumentation getInstrumentation() {
        return instrumentation;
    }


}