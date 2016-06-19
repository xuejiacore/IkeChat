/**
 * Project: IkeChat
 * Package Name: PACKAGE_NAME
 * Author: Xuejia
 * Date Time: 2016/6/11 23:09
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/

import com.sun.istack.internal.NotNull;

/**
 * Class Name: Demo
 * Create Date: 2016/6/11 23:09
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Demo {

    public static void test(@NotNull String dd) {
        System.err.println(dd.substring(1));
    }

    public static void main(String[] args) {
        Demo.test(null);
    }
}
