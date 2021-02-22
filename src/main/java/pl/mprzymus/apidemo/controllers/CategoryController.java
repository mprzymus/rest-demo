package pl.mprzymus.apidemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.apidemo.api.model.CategoryDTO;
import pl.mprzymus.apidemo.api.model.CategoryListDTO;
import pl.mprzymus.apidemo.service.CategoryService;

@RestController
@RequestMapping(CategoryController.URL)
@RequiredArgsConstructor
public class CategoryController {
    public static final String URL = "/api/categories";
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
