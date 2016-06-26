/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean
 * Author: Xuejia
 * Date Time: 2016/6/26 15:21
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: Menu
 * Create Date: 2016/6/26 15:21
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Menu {
    private List<Button> button;

    public Menu() {
        this.button = new ArrayList<Button>();
    }

    public Menu(List<Button> button) {
        this.button = button;
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Button main1 = new Button("主菜单1");
        Button main2 = new Button("主菜单2");
        Button main3 = new Button("click", "主菜单3", "key0");

        Button subBtn11 = new Button("click", "子菜单11", "key11");
        Button subBtn12 = new Button("click", "子菜单12", "key12");
        main1.addSubButton(subBtn11);
        main1.addSubButton(subBtn12);

        Button subBtn21 = new Button("click", "子菜单21", "key21");
        Button subBtn22 = new Button("click", "子菜单22", "key22");
        Button subBtn23 = new Button("click", "子菜单23", "key23");
        main2.addSubButton(subBtn21);
        main2.addSubButton(subBtn22);
        main2.addSubButton(subBtn23);

        menu.getButton().add(main1);
        menu.getButton().add(main2);
        menu.getButton().add(main3);

        System.err.println(new Gson().toJson(menu));
    }

    @Override
    public String toString() {
        return "Menu{" +
                "button=" + button +
                '}';
    }
}
