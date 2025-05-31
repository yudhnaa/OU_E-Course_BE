package com.ou.helpers;

import com.ou.utils.Pagination;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationHelper {
    public Pagination getPagination(Map<String, String> params, long total) {
        String pageParam = params.get("page");
        return new Pagination(pageParam, total);
    }

    public Map<String, String> extractFilters(Map<String, String> params, String... acceptedKeys) {
        Map<String, String> filters = new HashMap<>();

        for (String key : acceptedKeys) {
            if (params.containsKey(key)) {
                String mappedKey = switch (key) {
                    case "search" -> "name";
                    case "limit" -> "pageSize";
                    case "lecturer" -> "lecturerName";
                    default -> key;
                };
                filters.put(mappedKey, params.get(key));
            }
        }

        return filters;
    }
}
