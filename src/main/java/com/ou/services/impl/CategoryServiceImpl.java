package com.ou.services.impl;

import com.ou.pojo.Category;
import com.ou.repositories.CategoryRepository;
import com.ou.services.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public Category addCategory(Category category) throws Exception {
        // Validate category name is not null or empty
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new Exception("Category name cannot be empty");
        }
        
        // Check if category with the same name already exists
        Optional<Category> existingCategory = categoryRepository.getCategoryByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new Exception("Category with name '" + category.getName() + "' already exists");
        }
        
        // Additional validation as needed (e.g., description length, etc.)
        
        return categoryRepository.addCategory(category);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategories(Map<String, String> params) {
        return categoryRepository.getCategories(params);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Category> searchCategories(Map<String, String> filters, Map<String, String> params) {
        return categoryRepository.searchCategories(filters, params);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return categoryRepository.getCategoryById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.empty();
        }

        return categoryRepository.getCategoryByName(StringUtils.capitalize(name));
    }
    
    @Override
    public Category updateCategory(Category category) throws Exception {
        // Check if category exists
        if (category.getId() == null) {
            throw new Exception("Category ID cannot be null for update operation");
        }
        
        Optional<Category> existingCategoryOpt = categoryRepository.getCategoryById(category.getId());
        if (!existingCategoryOpt.isPresent()) {
            throw new Exception("Category with ID " + category.getId() + " does not exist");
        }
        
        // Validate category name is not null or empty
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new Exception("Category name cannot be empty");
        }
        
        // Check if the updated name conflicts with another category's name
        Optional<Category> categoryWithSameName = categoryRepository.getCategoryByName(category.getName());
        if (categoryWithSameName.isPresent() && !categoryWithSameName.get().getId().equals(category.getId())) {
            throw new Exception("Another category with name '" + category.getName() + "' already exists");
        }
        
        // Additional validation as needed
        
        return categoryRepository.updateCategory(category);
    }
    
    @Override
    public boolean deleteCategory(Integer id) throws Exception {
        // Check if category exists
        Optional<Category> categoryToDelete = categoryRepository.getCategoryById(id);
        if (!categoryToDelete.isPresent()) {
            throw new Exception("Category with ID " + id + " does not exist");
        }
        
        // Here you could add additional checks to see if the category is being used
        // For example, check if courses are using this category
        // if (courseRepository.countByCategoryId(id) > 0) {
        //     throw new Exception("Cannot delete category because it is being used by courses");
        // }
        
        return categoryRepository.deleteCategory(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countCategories() {
        return categoryRepository.countCategories();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return categoryRepository.countSearchResults(filters);
    }
}
