package ustc.sse.tims;

import org.springframework.context.ConfigurableApplicationContext;
import ustc.sse.tims.excutor.CommandExecutorImpl;
import ustc.sse.tims.model.AppIconMenu;
import ustc.sse.tims.model.Command;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.util
 * @date 2019/3/8-9:33
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description: 启动类：通过java.awt设置托盘图标及右键菜单，并启动spring boot项目
 */
public class TIMSLoader {

    private static TrayIcon icon = null;
    private static final String ICON_PATH = "static/images/logo-icon.png";
    private static SystemTray tray = SystemTray.getSystemTray();
    private static ConfigurableApplicationContext springContext;

    private CommandExecutorImpl executor = new CommandExecutorImpl(new Command("-V"));
    boolean nmapInstalled = executor.getCmd().getOutput().getText().contains("Nmap version");

    public static void main(String[] args) {

        try{
            springContext = TimsApplication.mainExec(args);
        }catch (Exception e){
            e.printStackTrace();
        }

        ImageIcon trayImg = new ImageIcon(
                Objects.requireNonNull(TIMSLoader.class.getClassLoader().getResource(ICON_PATH)));// 托盘图标

        //传入app的popupMenu右键菜单
        icon = new TrayIcon(trayImg.getImage(), "TIMS-设备识别管理系统", AppIconMenu.getMenu());
        icon.setImageAutoSize(true);
        try {

            tray.add(icon);

        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }

}
