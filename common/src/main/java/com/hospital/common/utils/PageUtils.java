package com.hospital.common.utils;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int pageIndex;
    /**
     * 列表数据
     */
    private List list;

    public PageUtils(List list, long totalCount, int pageIndex, int pageSize) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        //向上取整，然后转化为int，显示为多少页
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
