package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * BatchFile 容器对象
 * 2017/10/14
 * @author ljy56
 */
public class BatchFileContainer {
    /**
     * BatchFile列表
     */
    private List<BatchFile> files;

    {
        files = new ArrayList<>();
    }

    public List<BatchFile> getFiles() {
        return files;
    }

    public void setFiles(List<BatchFile> files) {
        this.files = files;
    }
}
