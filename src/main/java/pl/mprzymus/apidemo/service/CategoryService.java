package pl.mprzymus.apidemo.service;

import pl.mprzymus.apidemo.api.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
