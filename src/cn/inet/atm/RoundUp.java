package cn.inet.atm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 处理四舍五入，保留两位
 */
public class RoundUp {
    public static double RoundingUp(double money) {
        // 对于不需要任何准确计算精度的数字可以直接使用float或double，
        // 但是如果需要精确计算的结果，则必须使用BigDecimal类，而且使用BigDecimal类也可以进行大数的操作。
        BigDecimal b = new BigDecimal(money);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量,表示进行四舍五入的操作
        b = b.divide(new BigDecimal(1), 2, RoundingMode.HALF_UP);
        return b.doubleValue();
    }
}
