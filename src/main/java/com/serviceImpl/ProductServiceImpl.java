package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dto.Productdto;
import com.entity.CategoryEntity;
import com.entity.ProductEntity;
import com.exception.ResourceNotFoundException;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import com.serviceinterface.IProductListDto;

import com.serviceinterface.ProductServiceInterface;
import com.utility.Pagination;




@Service
public class ProductServiceImpl implements ProductServiceInterface
{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepsitory;
	
	
    // Add product 
	@Override
	public void addproduct(Productdto productDto)
	{
		ProductEntity product=new ProductEntity();
		product.setProductname(productDto.getProductname());
		product.setProductprize(productDto.getProductprize());
		
	   Long a=	productDto.getCategoryid();
	   CategoryEntity category=categoryRepsitory.findByid(a);
		
		product.setCategory(category);
		productRepository.save(product);
		
	}

    // Get All Product With Pagination
	@Override
	public Page<IProductListDto> getAllProduct(String search, String pageNumber, String pageSize) 
	{
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return productRepository.findByOrderById(pagable,IProductListDto.class);
		}
		else
		{
			return  productRepository.findByproductname(search,pagable,IProductListDto.class);
		}
		
		
	}

     
	// Get Product By Id
	@Override
	public Productdto getbyid(Long id) 
	{
		
	 ProductEntity productEntity=productRepository.findById(id).orElseThrow(()-> 
	 new com.exception.ResourceNotFoundException("Not Found product Id"));
	 Productdto productDto=new Productdto();
	 
	 productDto.setId(productEntity.getId());
     productDto.setProductname(productEntity.getProductname());
     productDto.setProductprize(productEntity.getProductprize());
     return productDto;
	 
			
	}

	@Override
	public Productdto updateProduct(Productdto productDto, Long id)
	{
		 ProductEntity productEntity=productRepository.findById(id).orElseThrow(()-> 
	     new ResourceNotFoundException("Not Found User Id"));
			
		 productEntity.setProductname(productDto.getProductname());
		 productEntity.setProductprize(productDto.getProductprize());
		 
		 productRepository.save(productEntity);
		 return productDto;
			
		
	}

	@Override
	public void deleteProduct(Long id)
	{
		this.productRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("User Not Found With Id :"+id));
		this.productRepository.deleteById(id);
	}

	
}
