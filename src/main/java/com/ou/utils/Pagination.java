package com.ou.utils;

import static com.ou.configs.WebApplicationSettings.PAGE_SIZE;

public class Pagination {
    private int currentPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private int startIndex;
    private int endIndex;

    public Pagination(String pageParam, long totalItems) {
        this.pageSize =  PAGE_SIZE;
        this.totalItems = totalItems;

        int page = (pageParam != null && pageParam.matches("\\d+")) ? Integer.parseInt(pageParam) : 1;

        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
        this.currentPage = Math.max(1, Math.min(page, totalPages == 0 ? 1 : totalPages));

        this.startIndex = (currentPage - 1) * pageSize + 1;
        this.endIndex = Math.min(startIndex + pageSize - 1, (int) totalItems);
    }

    public int getCurrentPage() { return currentPage; }
    public int getPageSize() { return pageSize; }
    public long getTotalItems() { return totalItems; }
    public int getTotalPages() { return totalPages; }
    public int getStartIndex() { return startIndex; }
    public int getEndIndex() { return endIndex; }
}
