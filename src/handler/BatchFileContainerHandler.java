package handler;

import constants.Constants;
import domain.BatchFile;
import domain.Row;
import exception.ColumnException;
import exception.NotNumberException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static handler.BatchFileHandler.readRows;

/**
 * 操作BatchFile容器的工具类
 * 2017/10/14
 * @author ljy56
 */
public class BatchFileContainerHandler {
    /**
     * 获取所有需要被读取的文件
     * @return File[] files
     */
    public static File[] getAllFiles() {
        //找到存放文件目录下的所有文件
        return new File(Constants.FILE_PATH).listFiles();
    }

    /**
     * 读取单个文件
     * @param file File
     * @return BatchFile
     */
    public static BatchFile readFile(File file) {
        BufferedReader bufferedReader;
        BatchFile batchFile;
        String rowStr;
        //记录当前读到的行号
        int count = 0;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            batchFile = new BatchFile();
            //统计文件的正确列数，返回修改后的batchFile
            batchFile = BatchFileHandler.countRowColumn(batchFile, file);
            List<Row> rows = new ArrayList<>();
            while ((rowStr = bufferedReader.readLine()) != null) {
                //每读完一行，行数加1
                count++;
                try {
                    //读取一行，转换成Row对象,设置了row的属性值
                    Row row = readRows(batchFile, rowStr);
                    rows.add(row);
                } catch (NotNumberException e) {
                    Log.e("检测到非数值:" + count + "行");
                } catch (ColumnException e) {
                    Log.e("检测到列数错误:" + count + "行");
                }
            }
            if (batchFile != null) {
                //将Rows添加到文件
                batchFile.setRows(rows);
            }
            return batchFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将BatchFile写出到文件夹
     * @param batchFile BatchFile
     */
    public static void writeFile(BatchFile batchFile) {
        BufferedWriter bufferedWriter;
        //获取到BatchFile里的所有Row对象
        List<Row> rows = batchFile.getRows();
        //每一行分别处理
        for (Row row : rows) {
            //获取label位置
            Integer labelIndex = row.getLabelIndex();
            //根据label位置获取label
            Integer label = row.getLabel();
            //获取某个label的出现次数
            Integer times = batchFile.getLabelMap().get(label);
            //获取输出文件夹目录
            String dirFilePath = Constants.FILE_PATH;
            //获取输出文件的名字
            String batchFileName = String.format(Constants.FILE_NAME, labelIndex, label, times);
            File dirFile = new File(dirFilePath);
            File rowFile = new File(batchFileName);
            try {
                //如果对应的文件夹不存在，则创建
                if (!dirFile.exists()) {
                    boolean mkdirs = dirFile.mkdirs();
                    if(!mkdirs){
                        Log.e("文件夹"+dirFile.getName()+"创建失败");
                    }
                }
                //如果对应的文件不存在，则创建
                if (!rowFile.exists()) {
                    boolean newFile = rowFile.createNewFile();
                    if(!newFile){
                        Log.e("文件"+rowFile.getName()+"创建失败");
                    }
                }
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rowFile,true)));
                //获取一行的数据
                List<String> data = row.getData();
                //写入一行数据
                for (String datum : data) {
                    bufferedWriter.write(datum+" ");
                    bufferedWriter.flush();
                }
                //一行写完，另起一行
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取标签
     * @param file File
     * @return label
     */
    public static Integer readLabelIndex(File file) {
        if (file != null) {
            //获取文件名
            String fileName = file.getName().replace(".txt", "");
            Log.i("——————————————————————————————————————");
            Log.i("文件名：" + fileName);
            //获取文件名里的label
            String split = fileName.split("_")[1];
            int index = split.lastIndexOf("t");
            String labelIndex = split.substring(index + 1);
            Log.i("标签位置：" + labelIndex);
            return Integer.parseInt(labelIndex);
        } else {
            return null;
        }
    }
}
