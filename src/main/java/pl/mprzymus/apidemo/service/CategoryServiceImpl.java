package pl.mprzymus.apidemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.apidemo.api.mapper.CategoryMapper;
import pl.mprzymus.apidemo.api.model.CategoryDTO;
import pl.mprzymus.apidemo.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        var category = categoryRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("No category with such name"));
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
