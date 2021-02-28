package pl.mprzymus.apidemo.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryListDTO extends RepresentationModel<CategoryDTO> {
    List<CategoryDTO> categories;
}
