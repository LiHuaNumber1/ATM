package cn.inet.atm;

import java.util.Scanner;

public class Admin {
    Scanner sc;

    public Admin() {
        sc = new Scanner(System.in);
        System.err.println("********欢迎管理员登录**********");
        while (true) {
            System.err.println("1: 显示所有客户名单");
            System.err.println("2: 删除所有用户");
            System.err.println("3: 计算利息（每3个月一计，利息为：0.15%/月）");
            System.err.println("0: 退出管理员界面");
            String op = sc.next();
            if (op.equals("1")) {
                showUsers();
            } else if (op.equals("2")) {
                delAllUsers();
            } else if (op.equals("3")) {
                accrual();
            } else {
                return;
            }
        }
    }
    /**
     * 显示所有用户信息，像这样的操作，不建议放到数据库类中
     */
    public void showUsers(){
        System.err.println("用户名\t\t用户密码\t\t用户余额");
        for(int i=0;i<DataBase.names.length;i++){
            if(DataBase.names[i]!=null){
                System.err.println(DataBase.names[i]+"\t\t******\t\t"+DataBase.moneys[i]);
            }
        }
        System.err.println("***********************************************");
    }
    /**
     * 删除所有用户
     */
    public void delAllUsers(){
        DataBase.names=new String[5];
        DataBase.pwds =new String[5];
        DataBase.moneys=new double[5];
        DataBase.tranlog=new String[5];
        System.err.println("**********所有用户已经删除***********");
    }
    /**
     * 计算利息
     */
    public void accrual(){
        for(int i=0;i<DataBase.names.length;i++){			//通过用户名来计算利息
            if(DataBase.names[i]!=null){
                DataBase.tranlog[i]=DataBase.tranlog[i]+","+DateTool.getDate()+"@利息@"+RoundUp.RoundingUp(DataBase.moneys[i]*0.15);
                DataBase.moneys[i]=RoundUp.RoundingUp(DataBase.moneys[i]*1.15);
            }
        }
        System.err.println("*********计算利息成功***************");
    }
}
