package pl.mprzymus.apidemo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CategoryDTO extends RepresentationModel<CategoryDTO> {
    private Long id;
    private String name;
}