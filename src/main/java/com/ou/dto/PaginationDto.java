package com.ou.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.utils.Pagination}
 */
public class PaginationDto implements Serializable {
    private  int currentPage;
    private  int itemsPerPage;
    private  long totalItems;
    private  int totalPages;

    public PaginationDto(int currentPage, int itemsPerPage, long totalItems, int totalPages) {
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationDto entity = (PaginationDto) o;
        return Objects.equals(this.currentPage, entity.currentPage) &&
                Objects.equals(this.itemsPerPage, entity.itemsPerPage) &&
                Objects.equals(this.totalItems, entity.totalItems) &&
                Objects.equals(this.totalPages, entity.totalPages);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, itemsPerPage, totalItems, totalPages);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "currentPage = " + currentPage + ", " +
                "itemsPerPage = " + itemsPerPage + ", " +
                "totalItems = " + totalItems + ", " +
                "totalPages = " + totalPages + ")";
    }
}