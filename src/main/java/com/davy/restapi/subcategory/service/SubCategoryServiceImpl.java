package com.davy.restapi.subcategory.service;

import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.subcategory.entity.SubCategory;
import com.davy.restapi.subcategory.mapper.SubCategoryItemsMapper;
import com.davy.restapi.subcategory.repository.SubCategoryRepository;
import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final SubCategoryItemsMapper subCategoryItemsMapper;

    @Override
    public SubCategoryListResponse findAllSubCategories() {
        var response = new SubCategoryListResponse();
        if(subCategoryRepository.getAllSubCategories().isEmpty()){
            ThrowException.objectException("Subcategories");
        }
        response.subCategories = subCategoryRepository.getAllSubCategories()
                .stream()
                .map(subCategoryItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public SubCategoryListResponse findSubCategoriesByCategoryId(Long id) {

        if(id == null){
            return findAllSubCategories();
        }
        var response = new SubCategoryListResponse();
        if(subCategoryRepository.findByCategoryId(id).isEmpty()){
            ThrowException.objectException("Categories");
        }
        response.subCategories = subCategoryRepository.findByCategoryId(id)
                .stream()
                .map(subCategoryItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public SubCategoryResponse findSubCategoryById(Long id) {
        var response = new SubCategoryResponse();
        if(subCategoryRepository.getSubCategoryById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Subcategory");
        }
        response.subCategory = subCategoryRepository.getSubCategoryById(id)
                .stream()
                .map(subCategoryItemsMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public SubCategoryResponse updateSubCategoryById(Long id, SubCategoryRequest request) {
        var subCategory = subCategoryRepository.getSubCategoryById(id);
        if(subCategory.isEmpty()){
            ThrowException.objectByIdException(id, "Subcategory");
        }
        subCategory.get().setName(request.getName());
        subCategoryRepository.updateSubCategory(subCategory.get());
        return this.findSubCategoryById(subCategory.get().getId());
    }

    @Override
    public SubCategoryListResponse saveSubCategory(SubCategoryRequest request) {
        var subCategory = SubCategory.builder()
                .name(request.getName())
                .build();
        subCategoryRepository.saveSubCategory(subCategory);
        return this.findAllSubCategories();
    }

    @Override
    public Boolean existsBySubCategoryId(Long id) {
        return subCategoryRepository.existsById(id);
    }
}
