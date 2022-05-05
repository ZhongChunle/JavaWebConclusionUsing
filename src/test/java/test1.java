import com.zcl.util.CheckCodeUtil;

import java.io.FileOutputStream;

public class test1 {
    public static void main(String[] args) throws Exception {
        FileOutputStream oup = new FileOutputStream("d:yzm.jpg"); // 输出验证码的图片
        String s = CheckCodeUtil.outputVerifyImage(100, 50, oup, 5);
        System.out.println("输出输出验证码的内容："+s);
    }

}
