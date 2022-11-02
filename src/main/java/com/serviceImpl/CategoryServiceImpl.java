package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dto.Categorydto;

import com.entity.CategoryEntity;

import com.exception.ResourceNotFoundException;
import com.repository.CategoryRepository;
import com.serviceinterface.CategoryServiceInterface;
import com.serviceinterface.ICategoryListDto;

import com.utility.Pagination;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void addcategory(Categorydto categoryDto)
	{
		CategoryEntity category=new CategoryEntity();
		category.setCategoryname(categoryDto.getCategoryname());
		categoryRepository.save(category);
		
	}

	@Override
	public Page<ICategoryListDto> getAllCategory(String search, String pageNumber, String pageSize)
	{
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return categoryRepository.findByOrderById(pagable,ICategoryListDto.class);
		}
		else
		{
			return  categoryRepository.findBycategoryname(search,pagable,ICategoryListDto.class);
		}
		
	}

	@Override
	public Categorydto getbyid(Long id)
	{
		 CategoryEntity categoryEntity=categoryRepository.findById(id).orElseThrow(()-> 
		 new com.exception.ResourceNotFoundException("Not Found product Id"));
		 Categorydto categoryDto=new Categorydto();
		 
		 categoryDto.setId(categoryEntity.getId());
		 categoryDto.setCategoryname(categoryEntity.getCategoryname());
		 
		 return categoryDto;
	}

	@Override
	public Categorydto updateCategory(Categorydto categorydto, Long id)
	{
		CategoryEntity categoryEntity=categoryRepository.findById(id).orElseThrow(()-> 
	    new ResourceNotFoundException("Not Found User Id"));
			
		categoryEntity.setCategoryname(categorydto.getCategoryname());
		categoryRepository.save(categoryEntity);
		return categorydto;
		
	}

	@Override
	public void deleteCategory(Long id)
	{
		this.categoryRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("Category Not Found With Id :"+id));
		this.categoryRepository.deleteById(id);
	}
	
	

}
