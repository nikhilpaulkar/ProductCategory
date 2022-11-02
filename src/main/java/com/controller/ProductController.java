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

import com.dto.ErrorResponseDto;
import com.dto.Productdto;
import com.dto.SucessResponseDto;

import com.entity.ProductEntity;
import com.exception.ResourceNotFoundException;
import com.repository.ProductRepository;
import com.serviceinterface.IProductListDto;

import com.serviceinterface.ProductServiceInterface;



@RestController
@RequestMapping("/product")
public class ProductController 
{
	
	@Autowired
	private ProductServiceInterface productServiceInterface;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping()
	public ResponseEntity<?>addproduct(@RequestBody Productdto productDto)
	{
         ProductEntity product=productRepository.findByproductname(productDto.getProductname());
		
         if(product!=null)
		 {
			 return new ResponseEntity<>(new ErrorResponseDto("product already Added ","Plese Insert New Product"),HttpStatus.NOT_ACCEPTABLE);
		 }
		 else
		 {
		  
		  productServiceInterface.addproduct(productDto);
		  return new ResponseEntity<> (new SucessResponseDto("Product added", "success", "succesfully"),HttpStatus.ACCEPTED);
		 }
		
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllproduct(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	{
		
		Page<IProductListDto> entity= productServiceInterface.getAllProduct(search,pageNumber,pageSize);
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
	            Productdto productDto=this.productServiceInterface.getbyid(id);
				return new ResponseEntity<>(new SucessResponseDto("Success","Success", productDto),HttpStatus.OK);
			}catch(ResourceNotFoundException e) 
			{
				return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"product id Not Found"),HttpStatus.NOT_FOUND);
			}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateproduct(@RequestBody Productdto productdto,@PathVariable Long id)
	{
		try
		{
			
		  this.productServiceInterface.updateProduct(productdto, id);
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
			this.productServiceInterface.deleteProduct(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto("Plese enter valid product id"," Not Found"),HttpStatus.NOT_FOUND);
	    }
	}
}
