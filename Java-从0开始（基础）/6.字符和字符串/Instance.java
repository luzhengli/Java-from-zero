public class Instance {
  public static void main(String[] args) {
    // 字符
    // char a = 'a';
    // char b = '中';
    // int c = 'a'; // 97
    // int d = '中'; // 20013
    // System.out.println(a);
    // System.out.println(b);
    // System.out.println(c);
    // System.out.println(d);

    // char e = '\u0061'; // 字符 'a'
    // char f = '\u4e2d'; // 字符 '中'
    // System.out.println(e);
    // System.out.println(f);
    // 字符串
    // String s = "My age is ";
    // System.out.println(s + 18 + '!');

    // 多行字符串
    // String s1 = "first line \n" + "second line \n" + "end";
    // System.out.println(s1);

    // String s = """
    // SELECT * FROM
    // users
    // WHERE id > 100
    // ORDER BY name DESC""";
    // System.out.println(s);
    // String ss = """
    // this is
    // my best
    // friend!""";
    // System.out.println(ss);

    // 练习：将一组int值视为字符的Unicode编码，然后将它们拼成一个字符串
    int a = 72; // 48H
    int b = 105; // 69H
    int c = 65281; // FF01H

    String s = "" + (char) a + (char) b + (char) c; // 注意。直接 (char)a+(char)b+(char)c 是错的，因为 char 类型相加会被解释成 int 的相加
    System.out.println(s);

  }
}
