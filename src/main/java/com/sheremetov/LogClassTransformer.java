package com.sheremetov;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LogClassTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.startsWith("java") && !className.startsWith("sun") && !className.startsWith("jdk")) {
            File file = new File(ClassesLogger.LOG_FILE);
            try {

                if (!file.exists()) {
                    file.createNewFile();
                    file.setWritable(true, false);
                    //file.setExecutable(true, false);
                }

                FileWriter fileWritter = new FileWriter(ClassesLogger.LOG_FILE,true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(className + "\n");
                bufferWritter.close();

                //System.out.println("ClassName -> " + className);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

		return classfileBuffer;
	}

}
