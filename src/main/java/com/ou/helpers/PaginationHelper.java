package com.ou.helpers;

import com.ou.utils.Pagination;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaginationHelper {
    public Pagination getPagination(Map<String, String> params, long total) {
        String pageParam = params.get("page");
        return new Pagination(pageParam, total);
    }
}