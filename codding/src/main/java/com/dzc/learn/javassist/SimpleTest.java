package com.dzc.learn.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SimpleTest {

    @Test
    public void DynGenerateClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass("top.ss007.GenerateClass");//创建类
        ct.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});//让类实现Cloneable接口
        try {
            CtField f = new CtField(CtClass.intType, "id", ct);//获得一个类型为int，名称为id的字段
            f.setModifiers(AccessFlag.PUBLIC);//将字段设置为public
            ct.addField(f);//将字段设置到类上
            //添加构造函数
            CtConstructor constructor = CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}", ct);
            ct.addConstructor(constructor);
            //添加方法
            CtMethod helloM = CtNewMethod.make("public void hello(String des){ System.out.println(des);}", ct);
            ct.addMethod(helloM);

            ct.writeFile();//将生成的.class文件保存到磁盘

            //下面的代码为验证代码
            Field[] fields = ct.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyMethod() {
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ct = pool.get("com.dzc.learn.javassist.Point");
            CtMethod m = ct.getDeclaredMethod("move");
            m.insertBefore("System.out.print(\"dx:\"+$1); System.out.println(\"dy:\"+$2);");
            m.insertAfter("System.out.println(this.x); System.out.println(this.y);");
            ct.writeFile();


            Class pc = ct.toClass();
            Method move = pc.getMethod("move", new Class[]{int.class, int.class});
            Constructor<?> con = pc.getConstructor(new Class[]{int.class, int.class});
            move.invoke(con.newInstance(1, 2), 1, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
