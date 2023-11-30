package books.serverlet.utils;

import java.util.List;

/**
 * ClassName:pageUtil
 * Package:tianjiao.serverlet.utils
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-0822:53
 * Version 1.0
 */
public class PageUtil<T> {
    private List<T> dataList;
    private Integer pageIndex;
    private Integer pageSize=DataUtils.PAGESIZE;
    private Integer totalCount;
    private Integer totalPage;
    private Double minPrice;
    private Double maxPrice;

    public PageUtil() {
    }

    public PageUtil(List<T> dataList, Integer pageIndex, Integer pageSize, Integer totalCount, Integer totalPage, Double minPrice, Double maxPrice) {
        this.dataList = dataList;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    //服务器上存储时，比如pu.totalPage时实质是调用了gettotalPage方法，所以这里的totalPage不赋值也没什么关系。
    public Integer getTotalPage() {
        return totalCount%2>0?totalCount/ pageSize+1:totalCount/pageSize;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "dataList=" + dataList +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
