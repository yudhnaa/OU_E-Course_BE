package com.ou.services;

import com.ou.pojo.Category;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    // Create operations with validation
    Category addCategory(Category category) throws Exception;

    // Read operations with pagination
    List<Category> getCategories(Map<String, String> params);
    List<Category> searchCategories(Map<String, String> filters, Map<String, String> params);
    
    Optional<Category> getCategoryById(Integer id);
    Optional<Category> getCategoryByName(String name);
    
    // Update operation with validation
    Category updateCategory(Category category) throws Exception;

    // Delete operation with validation
    boolean deleteCategory(Integer id) throws Exception;

    // Count methods for pagination
    long countCategories();
    long countSearchResults(Map<String, String> filters);
}
