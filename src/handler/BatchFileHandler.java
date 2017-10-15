package handler;

import domain.BatchFile;
import domain.Row;
import exception.ColumnException;
import exception.NotNumberException;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 操作BatchFile的工具类
 * 2017/10/14
 * @author ljy56
 */
public class BatchFileHandler {

    /**
     * 读取每一行的数据
     * @param batchFile BatchFile
     * @param rowStr 每一行的原始数据
     * @return Row
     * @throws NotNumberException e1
     * @throws ColumnException e2
     */
    public static Row readRows(BatchFile batchFile, String rowStr) throws NotNumberException, ColumnException {
        String[] strings = rowStr.split("\\s|,");
        Map<Integer, Integer> labelMap = batchFile.getLabelMap();
        //检测列数是否正确
        detectRowColumnException(batchFile, strings);
        //将一行的数据转为数值
        List<String> resultList = RowHandler.parseData(strings);
        //获取行的label
        Integer rowLabel = Integer.valueOf(resultList.get(batchFile.getLabelIndex()));
        //获取label出现的次数
        Integer times = labelMap.get(rowLabel);
        if (labelMap.containsKey(rowLabel)) {
            //出现多次，则设置次数
            labelMap.put(rowLabel, ++times);
        } else {
            //如果label第一次出现，则设置为1
            labelMap.put(rowLabel, 1);
        }
        Row row = new Row();
        //给行设置label的位置
        row.setLabelIndex(batchFile.getLabelIndex());
        //给行设置label
        row.setLabel(rowLabel);
        //给行设置数据
        row.setData(resultList);
        //给行设置列数
        row.setColumnNum(resultList.size());
        return row;
    }

    /**
     * 统计文件的正确列数
     *
     * @param batchFile BatchFile
     * @param file      File
     */
    public static BatchFile countRowColumn(BatchFile batchFile, File file) {
        BufferedReader bufferedReader;
        //获取统计列数出现频率的Map
        Map<Integer, Integer> map = batchFile.getFrequencyMap();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String rowStr;
            while ((rowStr = bufferedReader.readLine()) != null) {
                String[] strings = rowStr.split("\\s|,");
                Integer times = map.get(strings.length);
                if (null == times || times == 0) {
                    //如果某个列数第一次出现，则设置为1
                    map.put(strings.length, 1);
                } else {
                    //如果某个列数多次出现，则设置对应的次数
                    map.put(strings.length, ++times);
                }
            }
            //正确的列数
            int currentColumn = 0;
            //出现的最大次数
            int maxTime = 0;
            //遍历Map
            for (Integer columnNum : map.keySet()) {
                //获取某个列数对应的出现次数
                Integer times = map.get(columnNum);
                //如果次数比目前的所知的最大次数还大，则把这个次数设置为最大次数
                if (times > maxTime) {
                    //把这个次数设置为最大次数
                    maxTime = times;
                    //该列数是正确的列数
                    currentColumn = columnNum;
                }
            }
            //设置正确的列数
            batchFile.setColumns(currentColumn);
            Integer labelIndex = BatchFileContainerHandler.readLabelIndex(file);
            //设置label的位置（第几列）
            batchFile.setLabelIndex(labelIndex);
            Log.i("每行正确长度：" + currentColumn);
            return batchFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检测某列的数据长度
     * @param batchFile BatchFile
     * @param strings 每行的数据
     * @throws ColumnException e
     */
    public static void detectRowColumnException(BatchFile batchFile, String[] strings) throws ColumnException {
        //如果某列的数据长度不等于正确列数，则报错
        if (strings.length != batchFile.getColumns()) {
            throw new ColumnException("检测到列数错误");
        }
    }
}
