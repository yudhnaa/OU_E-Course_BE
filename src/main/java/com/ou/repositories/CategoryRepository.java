package com.ou.repositories;

import com.ou.pojo.Category;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author yudhna
 */
public interface CategoryRepository {
    // Create operations
    Category addCategory(Category category);

    // Read operations with pagination
    List<Category> getCategories(Map<String, String> params);
    List<Category> searchCategories(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<Category> to handle cases where the category is not found
    Optional<Category> getCategoryById(Integer id);
    Optional<Category> getCategoryByName(String name);
    
    // Update operation
    Category updateCategory(Category category);

    // Delete operation
    boolean deleteCategory(Integer id);

    // Count methods for pagination
    long countCategories();
    long countSearchResults(Map<String, String> filters);
}
