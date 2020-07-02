package kz.akbar.efc.phonebook.model;

import java.util.List;

public class DataGrid<T> {

    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<T> data;

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public long getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> ClientData) {
        this.data = ClientData;
    }
}

