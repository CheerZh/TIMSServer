package ustc.sse.tims.model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model
 * @date 2019/3/8-9:31
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class app {

    private static PopupMenu popupMenu = new PopupMenu();

    public static PopupMenu getMenu() {
        MenuItem itemSetting,itemDisplay,itemExit;
        itemSetting=new MenuItem("setting");
        itemDisplay=new MenuItem("display");
        itemExit=new MenuItem("exit");
        popupMenu.add(itemSetting);
        popupMenu.addSeparator();//分割线
        popupMenu.add(itemDisplay);
        popupMenu.addSeparator();
        popupMenu.add(itemExit);

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("点击退出");

                System.exit(0);
            }
        });

        itemSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击设置");
            }
        });

        itemDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击显示");
                try {
                    openWebpage(new URL("http://localhost:8080/tims/index"));
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return popupMenu;
    }

    public static void openWebpage(URI uri) {

        //本地Desktop
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
