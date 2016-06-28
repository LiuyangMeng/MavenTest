package liuyang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CreUser {

	public static void main(String[] args) {
		try {

			// Class u = Class.forName("java.lang.Integer");

			// 获取整个类
			Class c = Class.forName("LiuyangMeng.User");
			// 获取所有的属性?
			Field[] fs = c.getDeclaredFields();

			// 定义可变长的字符串，用来存储属性
			StringBuffer sb = new StringBuffer();
			// 通过追加的方法，将每个属性拼接到此字符串中
			// 最外边的public定义
			sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
			// 里边的每一个属性
			for (Field field : fs) {
				sb.append("\t");// 空格
				sb.append(Modifier.toString(field.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
				sb.append(field.getType().getSimpleName() + " ");// 属性的类型的名字
				sb.append(field.getName() + ";\n");// 属性的名字+回车
			}

			sb.append("}");

			System.out.println(sb);

			System.out.println("传统========");
			User us = new User();
			us.name = "Liuyang11";
			// us.setName("liuyang");
			System.out.println(us.getName() + "\n 传统结束===========");

			System.out.println("reflect=======");
			// 获取类
			Class cl = Class.forName("LiuyangMeng.User");
			// 获取name属性
			Field fi = cl.getDeclaredField("name");
			// 实例化这个类赋给oj
			Object oj = cl.newInstance();
			// 打破封装
			fi.setAccessible(true);
			// 给o对象的id属性赋值"110"
			fi.set(oj, "meng");
			System.out.println(fi.get(oj) + "\n reflect==========");

			// 参数列表
			Class[] pars = new Class[1];
			// 注意参数类型
			pars[0] = String.class;

			// 获取Reflect的方法，第一个参数是方法名；第二个参数是参数的类型，注意是参数的类型！
			Method me=cl.getMethod("setName", pars);
			 //null表示getName方法没有参数  
			Method meget=cl.getMethod("getName", null);
			
			//开始调用方法，第一个参数是调用该方法的对象；第二个参数是值，即setName方法中要传入的值  
			Object obj=me.invoke(oj, "reflect pars");
			
			Object obj1=meget.invoke(oj, null);
			
			System.out.println(obj);
			System.out.println(obj1);
			
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
