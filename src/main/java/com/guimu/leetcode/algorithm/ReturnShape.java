package com.guimu.leetcode.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @Description: 回形矩阵打印
 * @Author: Guimu
 * @Create: 2019/04/06 18:07:14
 **/

public class ReturnShape {

    @Test
    public void printRetShape() {
        iterHc(5, 2);
    }



    private void iterHc(int h, int w) {
        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= h; x++) {
                if (checkPos(h, w, x, y)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * @Author: Guimu
     * @Description: 判断在某个位置是否需要输出
     * @Param:
     * @Return:
     * @Date: 2019-04-06 18:09
     */
    private boolean checkPos(int h, int w, int x, int y) {

        boolean xf = (x >= w && x <= h - w && (y == w || y == h - w));
        boolean yf = (y >= w && y <= h - w && (x == w || x == h - w));
        return x * y == 0 || (h == x || y == h) || xf || yf;
    }

}
