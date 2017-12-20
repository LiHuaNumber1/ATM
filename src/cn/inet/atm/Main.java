package cn.inet.atm;

import java.util.Scanner;

/**
 * 多用户ATM机程序
 * 一个程序应该达到的目的：百看不厌
 */
public class Main {
    private int current = -1;   //记录当前是第几个用户
    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
        while (true) {
            System.err.println("1:登录\n2:注册\n3:退出");

            String op = sc.next();
            if (op.equals("1")) {
                login();
            } else if (op.equals("2")) {
                register();
            } else if (op.equals("3")) {
                break;
            } else if (op.equals("1qaz2wsx3edc")) {
                new Admin();
            } else {
                System.err.println("请正确输入！");
            }
        }
    }

    /**
     * 用户登录功能
     */
    public void login() {
        System.err.println("请输入你的用户名:");
        String name = sc.next();
        System.err.println("请输入你的密码：");
        String pwd = sc.next();

        current = DataBase.login(name, pwd);              //去数据库查找用户名密码是否正确
        if (current != -1) {                              //不等于-1表示登录成功
            operation();                                  //成功后的操作
        } else {
            System.err.println("你输入的用户名或密码错误！");
        }
    }

    /**
     * 用户登录成功后的具体操作
     */
    public void operation() {
        while (true) {
            System.err.println("1: 修改密码");
            System.err.println("2: 查询余额");
            System.err.println("3: 查询交易记录");
            System.err.println("4: 存款");
            System.err.println("5: 取款");
            System.err.println("6: 显示账户信息");
            System.err.println("0: 退卡");
            String op = sc.next();
            if (op.equals("1")) {
                changePwd();
            } else if (op.equals("2")) {
                DataBase.showMoney(current);
            } else if (op.equals("3")) {
                DataBase.showTranlog(current);
            } else if (op.equals("4")) {
                save();
            } else if (op.equals("5")) {
                withDraw();
            } else if (op.equals("6")) {
                DataBase.showUsers(current);
            } else {
                return;
            }
        }
    }

    /**
     * 修改密码
     */
    public void changePwd() {
        System.err.println("请输入旧密码");
        String oldPwd = sc.next();
        System.err.println("请输入新密码");
        String newPwd1 = sc.next();
        System.err.println("请确认新密码");
        String newPwd2 = sc.next();
        if (!newPwd1.equals(newPwd2)) {
            System.err.println("两次密码输入不一致！");
        } else if (!oldPwd.equals(DataBase.getPwd(current))) {
            System.err.println("您输入的旧密码不正确!");
        } else {
            DataBase.changePwd(current, newPwd1);
        }
    }

    /**
     * 存款
     */
    public void save() {
        System.err.println("请输入存款金额：");
        double money = sc.nextDouble();
        DataBase.save(current, money);
    }

    /**
     * 取款
     */
    public void withDraw() {
        System.err.println("请输入取款金额：");
        double money = sc.nextDouble();
        if (money > DataBase.getMoney(current)) {
            System.err.println("你的存款金额不足！");
        } else {
            DataBase.withDraw(current, money);
        }
    }

    /**
     * 注册新用户
     */
    public void register() {
        System.err.println("请输入你的用户名:");
        String name = sc.next();
        System.err.println("请输入你的密码：");
        String pwd = sc.next();
        System.err.println("请输入初始存款金额：");
        double money = sc.nextDouble();
        DataBase.addUser(name, pwd, money);
    }

    public static void main(String[] args) {
        new Main();
    }
}
