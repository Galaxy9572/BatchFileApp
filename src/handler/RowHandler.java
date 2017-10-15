package handler;

import exception.NotNumberException;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作行的工具
 * 2017/10/14
 * @author ljy56
 */
public class RowHandler {
    /**
     * 检测异常,检查是否是数值型
     * @param strings 初始的行数据
     * @return 检测后的数据
     * @throws NotNumberException e
     */
    public static List<String> parseData(String[] strings) throws NotNumberException{
        List<String> resultList = new ArrayList<>();
        for (String rowDatum : strings) {
            double v = 0;
            try {
                //检测是不是数值
                v = Double.parseDouble(rowDatum);
            } catch (NumberFormatException e) {
                //不是的话抛出异常
                throw new NotNumberException();
            }
            resultList.add(rowDatum);
        }
        return resultList;
    }

}
