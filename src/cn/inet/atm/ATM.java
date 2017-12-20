package cn.inet.atm;

import java.util.Scanner;

public class ATM {
    //1:定义一个数组，保存用户的姓名
    String[] names = new String[5];  // 用户名
    //2:保存密码
    String[] pwds = new String[5];   // 密码
    //8：保存money
    int[] moneys = new int[5]; // 存款金额
    //10:定义一个变量，用于保存目前注册了多少人
    int index = 0;
    //14：记录当前用户的下标
    int current = -1;
    //6：声明输入对象
    private Scanner sc = new Scanner(System.in);

    //4:声明一个构造
    public ATM() {
        //5：开发一个while（true）接收用户的输入
        while (true) {
            System.err.println("1:登录\n2:注册\n3:退出");
            //7:获取操作，并判断用户输入的值
            String op = sc.nextLine();
            if (op.equals("1")) {
                //11:登录
                login();
            } else if (op.equals("2")) {
                //9:注册
                register();
            } else if (op.equals("3")) {
                break;
            } else {
                System.err.println("请正确输入！");
            }
        }
    }

    //9:注册
    public void register() {
        System.err.println("请输入姓名：");
        String name = sc.nextLine();
        System.err.println("请输入密码：");
        String pwd = sc.nextLine();
        System.err.println("请输入初始存款金额:");
        String m = sc.nextLine();
        int money = Integer.parseInt(m);
        //保存到成员变量中,需要有下标
        names[index] = name;
        pwds[index] = pwd;
        moneys[index] = money;
        index++;    //为下一个用户作准备
        System.err.println("注册成功！");
    }

    //11:登录
    public void login() {
        System.err.println("请输入姓名：");
        String name = sc.nextLine();
        System.err.println("请输入密码：");
        String pwd = sc.nextLine();
        //判断用户名和密码是否正确
        boolean boo = false;
        for (int i = 0; i < index; i++) {
            if (names[i] != null && names[i].equals(name) && pwds[i].equals(pwd)) {
                boo = true;
                //15:登录成功后，记录当前用户
                current = i;
                break;
            }
        }
        if (boo) {
            System.err.println("登录成功！");
            //12：登录成功后的操作
            operation();
        } else {
            System.err.println("登录失败！用户名或密码不正确！");
        }
    }

    //12：登录成功后的操作
    public void operation() {
        while (true) {
            System.err.println("1:存款\n2：取钱\n3：查看余额\n4：退出");
            //7:获取操作，并判断用户输入的值
            String op = sc.nextLine();
            if (op.equals("1")) {

            } else if (op.equals("2")) {

            } else if (op.equals("3")) {
                //13:查询余额
                balance();
            } else if (op.equals("4")) {
                break;
            } else {
                System.err.println("请正确输入！");
            }
        }
    }

    //13:查询余额
    public void balance() {
        System.err.println("您的余额是：" + moneys[current]);
    }

    //3:main
    public static void main(String[] args) {
        new ATM();
    }

}
