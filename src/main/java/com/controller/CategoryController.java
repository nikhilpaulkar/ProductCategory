package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Categorydto;
import com.dto.ErrorResponseDto;

import com.dto.SucessResponseDto;
import com.entity.CategoryEntity;
import com.exception.ResourceNotFoundException;
import com.repository.CategoryRepository;
import com.serviceinterface.CategoryServiceInterface;
import com.serviceinterface.ICategoryListDto;


@RestController
@RequestMapping("/category")
public class CategoryController
{
  @Autowired
  private CategoryServiceInterface categoryServiceInterface;
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  
  @PostMapping()
  public ResponseEntity<?>addproduct(@RequestBody Categorydto categoryDto)
  {
       CategoryEntity product=categoryRepository.findBycategoryname(categoryDto.getCategoryname());
		
       if(product!=null)
	   {
			 return new ResponseEntity<>(new ErrorResponseDto("category already Added ","Plese Insert New category"),HttpStatus.NOT_ACCEPTABLE);
	   }
	  else
	  {
		  categoryServiceInterface.addcategory(categoryDto);
		  return new ResponseEntity<> (new SucessResponseDto("Category added", "success", "succesfully"),HttpStatus.ACCEPTED);
	  }
		
	}
  
  
  
  @GetMapping()
	public ResponseEntity<?> getAllproduct(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	{
		
		Page<ICategoryListDto> entity= categoryServiceInterface.getAllCategory(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
	    }
	}
  
  @GetMapping("/{id}")
	public ResponseEntity<?>getbyid(@PathVariable Long id )
	{
     try 
		{
	    Categorydto categoryDto=this.categoryServiceInterface.getbyid(id);
		return new ResponseEntity<>(new SucessResponseDto("Success","Success", categoryDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
		 return new ResponseEntity<>( new ErrorResponseDto("not found","category id Not Found"),HttpStatus.NOT_FOUND);
		}
	}
  
  @PutMapping("/{id}")
	public ResponseEntity<?>updatecategory(@RequestBody Categorydto categorydto,@PathVariable Long id)
	{
		try
		{
			
		  this.categoryServiceInterface.updateCategory(categorydto, id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
	
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid ProductId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
			
	}
	
  @DeleteMapping("/{id}")
	public ResponseEntity<?> deleterole(@PathVariable Long id)
	{
     try 
		{
			this.categoryServiceInterface.deleteCategory(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto("Plese enter valid category id"," Not Found"),HttpStatus.NOT_FOUND);
	    }
	}
  
  
}
