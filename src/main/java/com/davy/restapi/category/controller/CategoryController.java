package com.davy.restapi.category.controller;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.service.CategoryService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        var data = categoryService.findAllCategories();
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(value = "id")
                                                  final Long id){
        var data = categoryService.findCategoryById(id);
        return ResponseHandler.generateResponse("successful",  HttpStatus.OK, data);
    }

    @GetMapping("/subcategories/{SubCatId}")
    public ResponseEntity<?> findCategoryBySubCategoryId(@PathVariable(value = "SubCatId")
                                                             final  Long SubCatId){
        var data = categoryService.findCategoryBySubCategoryId(SubCatId);
        return ResponseHandler.generateResponse("successful",  HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id")
                                                    final Long id,
                                                @RequestBody CategoryUpdateRequest request){
        var data = categoryService.updateCategoryById(id, request);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateRequest request){
        var data = categoryService.saveCategory(request);
        return ResponseHandler.generateResponse("successful", HttpStatus.CREATED, data);
    }
}
