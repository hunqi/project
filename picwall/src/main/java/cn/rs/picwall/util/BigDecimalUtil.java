package cn.rs.picwall.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtil {

    public static BigDecimal sqrt(BigDecimal x) {
        if(BigDecimal.ZERO.equals(x)) return BigDecimal.ZERO;

        int precision = 10;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_DOWN);

        BigDecimal two = new BigDecimal(2);
        BigDecimal r = x;
        int count = 0;
        while (count < precision){
            r = (r.add(x.divide(r, mc))).divide(two, mc);
            count++;
        }

        return r;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(new BigDecimal(2)));
        System.out.println(sqrt(new BigDecimal(3)));
        System.out.println(sqrt(new BigDecimal(9)));
        System.out.println(sqrt(new BigDecimal(0.999333)));
    }

}
