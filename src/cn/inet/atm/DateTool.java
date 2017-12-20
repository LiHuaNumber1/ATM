package cn.inet.atm;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 获取当前时间
 */
public  final class DateTool {
	private static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getDate(){
		return s.format(new Date());
	}
}
