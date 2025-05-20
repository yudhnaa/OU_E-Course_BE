package com.ou.utils;

import java.util.Map;

public class ValidateUtils {
    public static void validatePaginationParams(Map<String, String> params) {
        if (params == null) {
            return;
        }

        validatePositiveInteger(params, "page", "Page number");
        validatePositiveInteger(params, "pageSize", "Page size");
    }

    private static void validatePositiveInteger(Map<String, String> params, String key, String label) {
        if (params.containsKey(key)) {
            try {
                int value = Integer.parseInt(params.get(key));
                if (value < 1) {
                    throw new IllegalArgumentException(label + " must be greater than 0");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid " + label.toLowerCase() + " format");
            }
        }
    }
}
