package com.ancun.products.core.tool;

import org.jasypt.util.text.BasicTextEncryptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * create at 2016/1/13 下午1:53
 *
 * @author <a href="mailto:taofei@ancun.com">Paul Yao</a>
 * @version %I%, %G%
 *
 */
public class EncryptPropertiesTool {

    private static BasicTextEncryptor encryptor;

    private static final String PASSWORD_ENV_NAME = "APP_ENCRYPTION_PASSWORD";

    private static Map<String, String> commandMap = new HashMap<String, String>() {{
        put("1", "数据库连接URL");
        put("2", "数据库用户名");
        put("3", "数据库密码");
        put("4", "其他字符串");
        put("5", "密钥");
        put("0", "退出");
    }};

    public static void main(String[] args) {
        String key;
        System.out.println("请输入加密密钥：");
        Scanner s = new Scanner(System.in);
        key = s.nextLine();
        initEncryptor(key);
        int count = 0;
        listMenu();
        System.out.print("请选择选项：");
        String commandName = null;
        String command = null;
        while (true) {
            if(count % 2 == 0) {
                // 输入命令选项
                command = s.nextLine().trim();
                if(commandMap.containsKey(command)) {
                    if(command.equals("0")) {
                        break;
                    }
                    commandName = commandMap.get(command);
                    System.out.println("请输入" + commandName + "：");
                } else {
                    System.out.println("选项不存在！请输入正确的选项。");
                    count ++;
                    System.out.print("请选择选项：");
                }
            } else {
                String line = s.nextLine();
                if(command.equals("5")) {
                    // 变更密钥
                    key = line;
                    initEncryptor(key);
                    System.out.println("变更密钥为：" + key);
                } else {
                    // 处理加密
                    System.out.println("加密后的" + commandName + "为：ENC(" + encryptor.encrypt(line) + ")");
                }
                commandName = null;
                System.out.print("请选择选项：");
            }
            count ++;
        }
        s.close();
    }

    private static void listMenu() {
        System.out.println("操作选项列表：");
        System.out.println("1：数据库连接URL加密");
        System.out.println("2：数据库用户名加密");
        System.out.println("3：数据库密码加密");
        System.out.println("4：其他字符串加密");
        System.out.println("5：更改密钥");
        System.out.println("0：退出");
    }

    private static void initEncryptor(String key) {
        encryptor = new BasicTextEncryptor();
        encryptor.setPassword(key);
    }

}
