package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件对象
 * 2017/10/14
 * @author ljy56
 */
public class BatchFile {
    /**
     * 表示label在第几位
     */
    private Integer labelIndex;
    /**
     * 所有的行数据
     */
    private List<Row> rows;
    /**
     * 正确的列数
     */
    private int columns;
    /**
     * key:列数,value:出现次数
     */
    private Map<Integer,Integer> frequencyMap;
    /**
     * key:列数,value:出现次数
     */
    private Map<Integer,Integer> labelMap;

    {
        frequencyMap = new HashMap<>();
        labelMap = new HashMap<>();
    }

    public Integer getLabelIndex() {
        return labelIndex;
    }

    public void setLabelIndex(Integer labelIndex) {
        this.labelIndex = labelIndex;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public Map<Integer, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public void setFrequencyMap(Map<Integer, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }

    public Map<Integer, Integer> getLabelMap() {
        return labelMap;
    }

    public void setLabelMap(Map<Integer, Integer> labelMap) {
        this.labelMap = labelMap;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
