package pl.mprzymus.apidemo.api.mapper;

import org.junit.jupiter.api.Test;
import pl.mprzymus.apidemo.domain.Category;

import javax.print.attribute.standard.MediaSize;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private final Long ID = 1L;
    private final String NAME = "someName";

    @Test
    void categoryToCategoryDTO() {
        var category = new Category();
        category.setId(ID);
        category.setName(NAME);

        var categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }
}