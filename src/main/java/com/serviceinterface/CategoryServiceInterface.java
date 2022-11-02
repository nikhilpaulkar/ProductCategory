package com.serviceinterface;

import org.springframework.data.domain.Page;

import com.dto.Categorydto;


public interface CategoryServiceInterface 
{
	void addcategory(Categorydto  categoryDto);
	Page<ICategoryListDto> getAllCategory(String search, String pageNumber, String pageSize);

	Categorydto getbyid(Long id);
	Categorydto updateCategory(Categorydto categorydto,Long id);
	void deleteCategory(Long id);
}


