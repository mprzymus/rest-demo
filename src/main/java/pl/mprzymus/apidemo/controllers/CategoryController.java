package pl.mprzymus.apidemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.apidemo.api.model.CategoryDTO;
import pl.mprzymus.apidemo.api.model.CategoryListDTO;
import pl.mprzymus.apidemo.service.CategoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(CategoryController.URL)
@RequiredArgsConstructor
public class CategoryController {
    public static final String URL = "/api/categories";
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        var listOfCategoryDto = categoryService.getAllCategories();
        listOfCategoryDto.forEach(this::addLinkToCategory);
        var listDto = new CategoryListDTO(listOfCategoryDto);
        listDto.add(linkTo(methodOn(CategoryController.class).getAllCategories()).withSelfRel());
        return listDto;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        var categoryDto = categoryService.getCategoryByName(name);
        addLinkToCategory(categoryDto);
        return categoryDto;
    }

    private void addLinkToCategory(CategoryDTO categoryDto) {
        categoryDto.add(linkTo(methodOn(CategoryController.class).getCategoryByName(categoryDto.getName())).withSelfRel());
    }
}
