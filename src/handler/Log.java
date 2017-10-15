package handler;

/**
 * 日志类
 * 2017/10/14
 * @author ljy56
 */
public class Log {
    public static void i(String info){
        System.out.println(info);
    }

    public static void e(String error){
        System.err.println(error);
    }

    public static void i(int info){
        System.out.println(info);
    }

    public static void e(int error){
        System.err.println(error);
    }
}
