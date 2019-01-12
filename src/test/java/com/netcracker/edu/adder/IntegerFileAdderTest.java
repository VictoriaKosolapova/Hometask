package com.netcracker.edu.adder;


import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.fail;


public class IntegerFileAdderTest {
    protected static Object integerFileAdder;
    protected static Method method = null;

    @Ignore
    @BeforeClass
    public static void setBeforeClass() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            method = clazz.getMethod("add", new Class[] {});
        } catch (Exception e) {
            fail("Method not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[] { "","","" });
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
    }


    @Test
    public void test1() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            method = clazz.getMethod("add", new Class[] {});
        } catch (Exception e) {
            fail("Method not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[] {
                    "src\\test\\resources\\test1\\input1",
                    "src\\test\\resources\\test1\\input2",
                    "src\\test\\resources\\test1\\output" });
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
        try {
            method.invoke(integerFileAdder);
            Assert.assertEquals(FileUtils.readLines(new File("src\\test\\resources\\test1\\expected")), FileUtils.readLines(new File("src\\test\\resources\\test1\\output")));
        }
        catch (Exception e){
            fail("Wrong add() result");
        }
    }


   @Test(expected = FileNotFoundException.class)// if one of input files is absent
    public void test2() throws Throwable {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            method = clazz.getMethod("add", new Class[]{});
        } catch (Exception e) {
            fail("Method not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[]{
                    "src\\test\\resources\\test2\\input1",
                    "src\\test\\resources\\test2\\input2",
                    "src\\test\\resources\\test2\\output"});
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
       try {
            method.invoke(integerFileAdder);
       }
        catch (IllegalAccessException e){
           fail();

       }
       catch (InvocationTargetException e){
           throw e.getCause();

       }
    }

    @Test(expected = NumberFormatException.class)// if one of input files line contains not Integer value
    public void test3() throws Throwable {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[]{
                    "src\\test\\resources\\test3\\input1",
                    "src\\test\\resources\\test3\\input2",
                    "src\\test\\resources\\test3\\output"});
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
        try {
            method.invoke(integerFileAdder);
        }
        catch (IllegalAccessException e){
            fail();
        }
        catch (InvocationTargetException e){
            throw e.getCause();

        }
    }

    @Test(expected = NumberFormatException.class)// if result of sum operation is not in [INTEGER_MIN; INTEGER_MAX) half-open interval
    public void test4() throws Throwable {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[]{
                    "src\\test\\resources\\test4\\input1",
                    "src\\test\\resources\\test4\\input2",
                    "src\\test\\resources\\test4\\output"});
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
        try {
            method.invoke(integerFileAdder);
        }
        catch (IllegalAccessException e){
            fail();
        }
        catch (InvocationTargetException e){
            throw e.getCause();

        }
    }

    @Test
    public void test5() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.netcracker.edu.adder.IntegerFileAdder");
        } catch (Exception e) {
            fail("Class not found");
        }
        try {
            method = clazz.getMethod("add", new Class[] {});
        } catch (Exception e) {
            fail("Method not found");
        }
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            integerFileAdder = constructor.newInstance(new Object[] {
                    "src\\test\\resources\\test5\\input1",
                    "src\\test\\resources\\test5\\input2",
                    "src\\test\\resources\\test5\\output" });
        } catch (Exception e) {
            fail("Object couldn't be created");
        }
        try {
            method.invoke(integerFileAdder);
            Assert.assertEquals(FileUtils.readLines(new File("src\\test\\resources\\test5\\expected")), FileUtils.readLines(new File("src\\test\\resources\\test5\\output")));
        }
        catch (Exception e){
            fail("Wrong add() result");
        }
    }
}