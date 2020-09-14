package com.zhengyuxiao.toolbox.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.*;

/**
 * @author xzy
 * @date 2020-09-13 21:37
 * 说明：
 */
public class ObjectAnalyzer {
    /**
     * Prints all fields of a class.
     *
     * @param cl - a class
     */
    public static void printFields(Class cl) {
        // 域
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            // 修饰符
            String modifier = Modifier.toString(field.getModifiers());
            // 类型
            String fieldType = field.getType().getName();
            // 名称
            String fieldName = field.getName();

            if (modifier.length() > 0) {
                System.out.println("    " + modifier + " " + fieldType + " " + fieldName + ";");
            } else {
                System.out.println("    " + fieldType + " " + fieldName + ";");
            }

        }
    }

    /**
     * Prints all constructors of a class.
     *
     * @param cl - a class
     */
    public static void printConstructors(Class cl) {
        // 构造器
        Constructor[] constructors = cl.getConstructors();

        for (Constructor constructor : constructors) {
            // 方法名
            String constructorName = constructor.getName();
            // 修饰符
            String modifiers = Modifier.toString(constructor.getModifiers());

            // 输出修饰符、方法名
            System.out.print("    ");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(constructorName + "(");

            // 输出参数列表
            Class[] paramTypes = constructor.getParameterTypes();
            Parameter[] params = constructor.getParameters();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName() + " " + params[i].getName());
            }

            System.out.println(");");
        }
    }

    /**
     * Prints all methods of a class.
     *
     * @param cl - a class
     */
    public static void printMethods(Class cl) {
        // 方法
        Method[] methods = cl.getMethods();

        for (Method method : methods) {
            // 修饰符
            String modifiers = Modifier.toString(method.getModifiers());
            // 返回值类型
            String returnType = method.getReturnType().getName();
            // 方法名
            String methodName = method.getName();

            // 输出修饰符、返回值类型、方法名
            System.out.print("    ");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(returnType + " " + methodName + "(");

            // 输出参数列表
            Class[] paramTypes = method.getParameterTypes();
            Parameter[] params = method.getParameters();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName() + " " + params[i].getName());
            }

            System.out.println(");");

        }
    }

    /**
     * Prints all fields、constructors and methods of a class.
     *
     * @param cl - a class
     */
    public static void printClass(Class cl) {
        // 修饰符
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }

        // 类名
        System.out.print("class" + cl.getName() + " ");

        // 父类
        Class superClass = cl.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            System.out.print("extends " + superClass.getName());
        }
        System.out.println("{");

        // 域
        System.out.println("fields：");
        printFields(cl);
        // 构造器
        System.out.println("constructors：");
        printConstructors(cl);
        // 方法
        System.out.println("methods：");
        printMethods(cl);
        System.out.println("}");
    }

    /**
     * Converts an object to a string representation that lists all fields.
     *
     * @param object - an object
     * @return - a string with the object`s class name and all fields names and values.
     */
    public static String toString(Object object) throws IllegalAccessException {
        // 1.null
        if (object == null) {
            return "null";
        }

        Class cl = object.getClass();

        // 2.String对象
        if (cl == String.class) {
            return (String) object;
        }

        // 3.数组类型
        if (cl.isArray()) {
            StringBuilder arrayStringBuilder = new StringBuilder(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(cl); i++) {
                if (i > 0) {
                    arrayStringBuilder.append(",");
                }

                Object element = Array.get(cl, i);
                if (cl.getComponentType().isPrimitive()) {
                    // 原始数据类型
                    arrayStringBuilder.append(element);
                } else {
                    // 对象类型
                    arrayStringBuilder.append(toString(element));
                }
            }
            arrayStringBuilder.append("}");
            return arrayStringBuilder.toString();
        }

        // 4.一般类型
        StringBuilder objectStringBuilder = new StringBuilder(cl.getName());
        // 检查类以及父类的域
        do {
            objectStringBuilder.append("[");

            // 4.1获取类中声明的所有域
            Field[] fields = cl.getDeclaredFields();

            // 4.2将所有域设置为可访问的
            AccessibleObject.setAccessible(fields, true);

            // 4.3获取所有 实例域 的名称和值
            for (Field field : fields) {

                // 实例域！
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!objectStringBuilder.toString().endsWith("[")) {
                        objectStringBuilder.append(",");
                    }
                    objectStringBuilder.append(field.getName()).append("=");
                    Class fieldClass = field.getType();
                    Object fieldValue = field.get(object);
                    if (fieldClass.isPrimitive()) {
                        // 基本类型
                        objectStringBuilder.append(fieldValue);
                    } else {
                        // 对象类型：递归查询
                        objectStringBuilder.append(toString(fieldValue));
                    }
                }
            }
            objectStringBuilder.append("]");
            cl = cl.getSuperclass();
        } while (cl != null);
        return objectStringBuilder.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        TestClass testClass = new TestClass();
        System.out.println(ObjectAnalyzer.toString(testClass));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TestClass {
        //fields
        private String field1;
        protected String field2;
        public String field3;
        public static String field4;
        private final String CONSTANT = "constant";
    }
}
