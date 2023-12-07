/*设计一个简单的密码管理系统，
 * 根据特定的规则对用户输入的密码(每个字符为数字或者大小写字母，长度不超过16)进行加密和解密操作，
 * 或者判断强度、生成密码，具体功能如下：
1.加密功能：用户输入一个字符串，系统根据规则对字符串进行加密：
  	（1）将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
  	（2）将字符串的第一位和最后一位调换顺序
  	（3）将字符串反转
2.解密功能：用户输入一个字符串，按照上述规则字符串进行解密。*/
//注释完成//
package Git;
import java.util.Scanner;

public class passwordManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("请选择操作：");
            System.out.println("1.加密密码");
            System.out.println("2.解密密码");
            System.out.println("3.判断密码强度");
            System.out.println("4.生成密码");
            System.out.println("5.退出");
            System.out.print("请输入选项序号：");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("请输入要加密的密码：");
                    String password = scanner.next();
                    String encryptedPassword = encrypt(password);
                    System.out.println("加密后的密码为：" + encryptedPassword);
                    break;
                case 2:
                    System.out.print("请输入要解密的密码：");
                    String encryptedPassword2 = scanner.next();
                    String decryptedPassword = decrypt(encryptedPassword2);
                    System.out.println("解密后的密码为：" + decryptedPassword);
                    break;
                case 3:
                    System.out.print("请输入要判断强度的密码：");
                    String password2 = scanner.next();
                    String strength = checkStrength(password2);
                    System.out.println("该密码的强度为：" + strength);
                    break;
                case 4:
                    System.out.print("请输入要生成密码的长度：");
                    int length = scanner.nextInt();
                    String generatedPassword = generatePassword(length);
                    System.out.println("生成的密码为：" + generatedPassword);
                    break;
                case 5:
                    System.out.println("退出程序");
                    break;
                default:
                    System.out.println("无效的选项，请重新输入");
                    break;
            }
        } while (choice != 5);
    }

    // 加密密码
    public static String encrypt(String password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int ascii = (int) c + i + 3;
            sb.append(ascii);
        }
        String encryptedPassword = sb.toString();
        encryptedPassword = swapFirstAndLastChar(encryptedPassword);
        encryptedPassword = reverseString(encryptedPassword);
        return encryptedPassword;
    }

    // 解密密码
    public static String decrypt(String encryptedPassword) {
        encryptedPassword = reverseString(encryptedPassword);
        encryptedPassword = swapFirstAndLastChar(encryptedPassword);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encryptedPassword.length(); i += 3) {
            String asciiStr = encryptedPassword.substring(i, i + 3);
            int ascii = Integer.parseInt(asciiStr) - i - 3;
            sb.append((char) ascii);
        }
        String decryptedPassword = sb.toString();
        return decryptedPassword;
    }

    // 判断密码强度
    public static String checkStrength(String password) {
        if (password.length() < 8) {
            return "弱强度";
        }
        boolean hasDigit = false;
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
        }
        if (hasDigit && hasLowercase && hasUppercase) {
            return "高强度";
        } else if (hasDigit || hasLowercase || hasUppercase) {
            return "中强度";
        } else {
            return "弱强度";
        }
    }

    // 生成密码
    public static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * 62);
            char c;
            if (random < 10) {
                c = (char) ('0' + random);
            } else if (random < 36) {
                c = (char) ('a' + random - 10);
            } else {
                c = (char) ('A' + random - 36);
            }
            sb.append(c);
        }
        String generatedPassword = sb.toString();
        return generatedPassword;
    }

    // 将字符串的第一位和最后一位调换顺序
    public static String swapFirstAndLastChar(String str) {
        if (str.length() <= 1) {
            return str;
        }
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length() - 1);
        String middle = str.substring(1, str.length() - 1);
        return lastChar + middle + firstChar;
    }

    // 将字符串反转
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}