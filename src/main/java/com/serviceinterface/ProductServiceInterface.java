package com.serviceinterface;


import org.springframework.data.domain.Page;

import com.dto.Productdto;



public interface ProductServiceInterface
{
	
	void addproduct(Productdto  productDto);
	Page<IProductListDto> getAllProduct(String search, String pageNumber, String pageSize);

	Productdto getbyid(Long id);
	Productdto updateProduct(Productdto productDto,Long id);
	void deleteProduct(Long id);
}
