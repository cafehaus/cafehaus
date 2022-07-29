import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {
    public static void main(String[] args) {
        String str = "第三长边: 4.0cm<x<7.0cm4.555";
        String reg = "\\d+|(.\\d+)?"; // 定义正则表达式

        Pattern patten = Pattern.compile(reg); // 编译正则表达式
        Matcher matcher = patten.matcher(str); // 指定要匹配的字符串

        List matchStrs = new ArrayList<>();

        while (matcher.find()) { // 此处find（）每次被调用后，会偏移到下一个匹配
           matchStrs.add(matcher.group()); // 获取当前匹配的值
        }

        for (int i = 0; i < matchStrs.size(); i++) {
            System.out.println(matchStrs.get(i));
        }
    }
}
