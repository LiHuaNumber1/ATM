package cn.inet.atm;
/**
 * 多用户数据结构
 * 此类仅保存用户账户信息，所以被称为数据库类
 * @note 一个好的程序，业务逻辑层、用户操作层、展示层、数据层应该是分开的
 */
public abstract class DataBase {
	private static int index = 0;								//已经保存到多少个用户了,即最大用户下标
	public static final int STEP = 5;							//初始用户的容量及每次用户数组增加的数量
	public static String[] names = new String[STEP];			//用户数据
	public static String[] pwds  = new String[STEP];			//用户密码
	public static double[] moneys = new double[STEP];	        //用户的钱
	public static String[] tranlog = new String[STEP];			//交易记录
	/**
	 * 注册用户的功能
	 */
	public static void addUser(String name,String pwd,double money){  //在用户输入用户名和密码后，保存到用户数组中
		if(index>=names.length){
			int tmpLen = names.length+STEP;                     //数组的长度增加一个步长
			String[] tmpName = new String[tmpLen];		    	//实例化一个新的数组
			String[] tmpPwd = new String[tmpLen];
			double[] tmpMon = new double[tmpLen];
			String[] tmpTran = new String[tmpLen];
			for(int i=0;i<names.length;i++){					//进行数据考贝
				tmpName[i] = names[i];
				tmpPwd[i] = pwds[i];
				tmpMon[i] = moneys[i];
				tmpTran[i]= tranlog[i];
			}
			names = tmpName;									//赋上新值
			pwds = tmpPwd;
			moneys = tmpMon;
			tranlog = tmpTran;
		}
		names[index]=name;										//给指定的下标保存对应的值
		pwds[index] =pwd;
		moneys[index]=money;
		tranlog[index]=DateTool.getDate()+"@开户@"+money;
		index++;												//记录个数随同增长
	}
	/**
	 * 登录功能
	 * 接收用户输入的用户名和密码,如果登录成功就返回下标
	 * 否则返回-1
	 */
	public static int login(String name,String pwd){
		for(int i=0;i<names.length;i++){						//遍历用户列表，看是否存在此用户名
			if(names[i]!=null && names[i].equalsIgnoreCase(name) && pwds[i].equalsIgnoreCase(pwd)){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 存钱功能
	 * 参数为存款人的下标和钱数
	 */
	public static void save(int index,double money){
		moneys[index] = moneys[index]+money;									//钱数增加
		tranlog[index]=tranlog[index]+","+DateTool.getDate()+"@存款@"+money;		//记录操作记录
		System.err.println("存款已经成功！");
		showMoney(index);														//调用一下显示用户存款余额的方法
	}
	/**
	 * 取款功能
	 */
	public static void withDraw(int index,double money){
		moneys[index] = moneys[index]-money;									//减去钱数
		tranlog[index]=tranlog[index]+","+DateTool.getDate()+"@取款@"+money;		//记录操作记录
		System.err.println("$请收取您的现金!$");										//温馨提示
	}
	/**
	 * 显示交易记录
	 */
	public static void showTranlog(int index){
		String[] trans = tranlog[index].split(",");								//获取指定下标的字符串，同时再根据逗号进行分开
		System.err.println("*************************************************************");
		System.err.println("时间\t\t\t\t\t\t交易\t\t金额");
		for(int i=0;i<trans.length;i++){
			String[] details = trans[i].split("@");								//再根据@符进行分割
			System.err.println(details[0]+"\t\t"+details[1]+"\t\t"+details[2]);
		}
		showMoney(index);														//显示完成交易记录后，应该显示最后的余额
		System.err.println("*************************************************************");
	}
	/**
	 * 显示某个用户信息
	 */
	public static void showUsers(int index){
		System.err.println("***用户名："+names[index]+"\t密码："+pwds[index]+"\t余额："+moneys[index]);
	}
	/**
	 * 显示用户余额
	 */
	public static void showMoney(int index){
		System.err.println("你的余额为："+moneys[index]);
	}
	/**
	 * 修改密码
	 */
	public static void changePwd(int index,String newPwd){
		pwds[index]=newPwd;													//修改密码
		System.err.println("密码修改成功！");
	}
	/**
	 * 获取用户名
	 */
	public static String getName(int index){
		return names[index];
	}
	/**
	 * 获取用户密码
	 */
	public static String getPwd(int index){
		return pwds[index];
	}
	/**
	 * 获取用户余额
	 */
	public static double getMoney(int index){
		return moneys[index];
	}
}
