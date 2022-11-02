package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.CategoryEntity;
import com.serviceinterface.ICategoryListDto;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> 
{

	
	CategoryEntity findBycategoryname(String categoryname);

	Page<ICategoryListDto> findByOrderById(Pageable pagable, Class<ICategoryListDto> class1);

	Page<ICategoryListDto> findBycategoryname(String search, Pageable pagable, Class<ICategoryListDto> class1);

	CategoryEntity findByid(Long a);


}
