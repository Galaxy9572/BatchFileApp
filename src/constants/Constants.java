package constants;

/**
 * 常量类
 * 2017/10/14
 * @author ljy56
 */
public interface Constants {
    /**
     * 项目路径
     */
    String FILE_PATH = Constants.class.getResource("/").toString().replace("file:/","")+"file";
    /**
     * 文件名
     */
    String FILE_NAME = Constants.class.getResource("/").toString().replace("file:/","")+"batch1_labelAt%s_c%s_%s.txt";
}
