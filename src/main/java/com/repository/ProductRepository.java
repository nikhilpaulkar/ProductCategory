package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.ProductEntity;
import com.serviceinterface.IProductListDto;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>
{

	

	ProductEntity findByproductname(String productname);

	Page<IProductListDto> findByOrderById(Pageable pagable, Class<IProductListDto> class1);

	Page<IProductListDto> findByproductname(String search, Pageable pagable, Class<IProductListDto> class1);

    
	

	

}
