package domain;

import java.util.List;

/**
 * 行对象
 * 2017/10/14
 * @author ljy56
 */
public class Row {
    /**
     * label在行里的位置
     */
    private Integer labelIndex;
    /**
     * label的值
     */
    private Integer label;
    /**
     * 行数据
     */
    private List<String> data;
    /**
     * 列数
     */
    private Integer columnNum;

    public Integer getLabelIndex() {
        return labelIndex;
    }

    public void setLabelIndex(Integer labelIndex) {
        this.labelIndex = labelIndex;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
}
