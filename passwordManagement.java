/*设计一个简单的密码管理系统，
 * 根据特定的规则对用户输入的密码(每个字符为数字或者大小写字母，长度不超过16)进行加密和解密操作，
 * 或者判断强度、生成密码，具体功能如下：
1.加密功能：用户输入一个字符串，系统根据规则对字符串进行加密：
  	（1）将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
  	（2）将字符串的第一位和最后一位调换顺序
  	（3）将字符串反转
2.解密功能：用户输入一个字符串，按照上述规则字符串进行解密。*/
package Git;
import java.util.Scanner;
public class passwordManagement {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请选择功能：");
		System.out.println("1.加密");
		System.out.println("2.解密");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("请输入要加密的密码:");
			String plainText=sc.next();
			String encryptedText=encrypt(plainText);
			System.out.println("加密后的密码："+encryptedText);
			break;
		case 2:
			System.out.println("请输入要解密的密码：");
			encryptedText=sc.next();
			plainText=encrypt(encryptedText);
			System.out.println("解密后的密码："+plainText);
			break;
		default:
			System.out.println("无效的选择");
			break;
		}
		sc.close();
	}

	//加密
	public static String encrypt(String plainText) {
		//StringBuilder 一个可变的字符序列
		//将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
		StringBuilder encryptedText=new StringBuilder();
		for(int i=0;i<plainText.length();i++) {
			//charAt() 方法用于返回指定索引处的字符，
			
			//索引范围为从 0 到 length() - 1
			char c=plainText.charAt(i);
			int encryptedChar=(int)c+i+1+3;
			encryptedText.append((char)encryptedChar);
		}
	 // 将字符串的第一位和最后一位调换顺序
        char firstChar = encryptedText.charAt(0);
        char lastChar = encryptedText.charAt(encryptedText.length() - 1);
        encryptedText.setCharAt(0, lastChar);
        encryptedText.setCharAt(encryptedText.length() - 1, firstChar);
     // 将字符串反转
        //reverse()是静态方法，在反转位顺序时不会引发异常
        encryptedText.reverse(); 
        return encryptedText.toString();
	}
	
	//解密
	public static String decrypt(String encryptedText) {
        // 将字符串反转
        StringBuilder decryptedText = new StringBuilder(encryptedText).reverse();
        
        // 将字符串的第一位和最后一位调换顺序
        char firstChar = decryptedText.charAt(0);
        char lastChar = decryptedText.charAt(decryptedText.length() - 1);
        decryptedText.setCharAt(0, lastChar);
        decryptedText.setCharAt(decryptedText.length() - 1, firstChar);
        
        // 将每个字符的ASCII码减去它在字符串中的位置(1开始)和偏移值3
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < decryptedText.length(); i++) {
            char c = decryptedText.charAt(i);
            int decryptedChar = (int) c - i - 1 - 3;
            plainText.append((char) decryptedChar);
        }
        return plainText.toString();
    }
}

