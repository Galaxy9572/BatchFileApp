import domain.BatchFile;
import domain.BatchFileContainer;
import handler.BatchFileContainerHandler;
import handler.Log;

import java.io.File;
import java.util.List;

/**
 * 应用入口类
 * 2017/10/14
 * @author ljy56
 */
public class Application {
    public static void main(String[] args) {
        Log.e("——————————————程序运行开始——————————————");
        //创建BatchFileContainer对象
        BatchFileContainer container = new BatchFileContainer();
        //获取BatchFileContainer中装载BatchFile的列表
        List<BatchFile> batchFileList = container.getFiles();
        //得到需要读入的文件
        File[] allFiles = BatchFileContainerHandler.getAllFiles();
        //将读入的文件转换成BatchFile
        for (File allFile : allFiles) {
            BatchFile batchFile = BatchFileContainerHandler.readFile(allFile);
            batchFileList.add(batchFile);
        }
        //将BatchFile按照要求分批输出
        for (BatchFile batchFile : batchFileList) {
            BatchFileContainerHandler.writeFile(batchFile);
        }
        Log.e("——————————————程序运行结束——————————————");
    }
}
