package com.xiaohulu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/27
 * \* Time: 21:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public abstract class SqldbBase {
//    1.abstract的特点
//            (1).用abstract关键字修饰的函数是抽象函数。
//            (2).类内含有抽象函数的类叫做抽象类，类需要用abstract修饰
//            (3).抽象类不能被实例化，所以无法对抽象类进行new
//            (4).继承自抽象类的子类必须覆盖实现父类中的抽象函数 。
//            (5).抽象类可以有构造函数，当被继承的时候，子类会用super()调用父类的构造函数
//2.抽象类的使用场景
//            (1) abstract不和可以和final一同使用：被final修饰后的类不能出现子类，而abstract的类需要子类来重写方法(不然毫无意义)。
//            (2)abstract不和可以和private一同使用: 当用private修饰抽象函数的时候，子类继承会无法重写父类的抽象方法(private不被子类继承)

    /**
     * Release Database resource. Example: close(con);
     * @param obj
     */
//    方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现，重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。
//    重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同
//    重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！
    public  static void close(Object obj){
        closeObject(obj);
    }
    /**
     * Release Database resource. Example: close(ps, rs);
     * @param obj1
     * @param obj2
     */
    public static void  close(Object obj1,Object obj2){
        closeObject(obj1);
        closeObject(obj2);
    }


    /**
     * Release resource
     * @param obj
     */
    public static void closeObject(Object obj){
        if(obj==null){
            return;
        }
        try {
            if(obj instanceof Connection){
                ((Connection) obj).close();
            }
            if (obj instanceof PreparedStatement){
                ((PreparedStatement) obj).close();
            }
            if (obj instanceof ResultSet){
                ((ResultSet) obj).close();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        obj=null;
    }

}