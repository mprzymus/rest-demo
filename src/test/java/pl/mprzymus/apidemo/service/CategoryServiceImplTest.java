package pl.mprzymus.apidemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.apidemo.api.mapper.CategoryMapper;
import pl.mprzymus.apidemo.domain.Category;
import pl.mprzymus.apidemo.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    public static final long ID = 1L;
    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        var categories = List.of(new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        var result = categoryService.getAllCategories();

        assertEquals(2, result.size());
    }

    @Test
    void getCategoryByNotExistingName() {
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.getCategoryByName(""));
    }

    @Test
    void getCategoryByNameTest() {
        var category = new Category();
        category.setId(ID);
        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        var result = categoryService.getCategoryByName("");

        assertEquals(category.getId(), result.getId());
    }
}