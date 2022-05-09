package edu.javacourse.studentorder.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRanerTest {
    public static  void main (String[] args){
        SimpleRanerTest srt = new SimpleRanerTest();
        srt.runTests();

    }

    private void runTests() {
        try {
            System.out.println("SimpleRanerTest. runTests - 18");
            Class cl  = Class.forName("DictionaryDaoImplTest.java");
//            Class cl = Class.forName( "edu/javacourse/studentorder/dao/DictionaryDaoImplTest.java");
            String name = cl.getName();
//            Class cl = Class.forName( "edu/javacourse/studentorder/dao/DictionaryDaoImplTest.java");
            System.out.println("SimpleRanerTest. runTests - 21" + name);
            Constructor cst = cl.getConstructor();
            Object entity = cst.newInstance();
            Method[]  metods = cl.getMethods();
            for (Method m: metods) {
                Test ann = m.getAnnotation(Test.class);
                if (ann != null){
                    m.invoke(entity);
                }
//                m.invoke(entity);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
