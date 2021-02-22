package pl.mprzymus.apidemo.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.mprzymus.apidemo.api.model.CategoryDTO;
import pl.mprzymus.apidemo.domain.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
