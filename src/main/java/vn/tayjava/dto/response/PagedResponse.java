package vn.tayjava.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize
public class PagedResponse<T> implements Serializable {
    private List<T> listdata;
    private int pageNumber;
    private int pageSize;
    private long totalSize;
    private boolean hasNextPage;
    private boolean hasPrevPage;

    // Constructor
    public PagedResponse(List<T> data, int pageNumber, int pageSize, long totalSize, boolean hasNextPage, boolean hasPrevPage) {
        this.listdata = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.hasNextPage = hasNextPage;
        this.hasPrevPage = hasPrevPage;
    }

    // Thêm constructor mặc định
    public PagedResponse() {
    }

    // Getters and setters
    public List<T> getData() {
        return listdata;
    }

    public void setData(List<T> data) {
        this.listdata = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrevPage() {
        return hasPrevPage;
    }

    public void setHasPrevPage(boolean hasPrevPage) {
        this.hasPrevPage = hasPrevPage;
    }
}
