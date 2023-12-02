package com.model2.mvc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service
public class ProductServiceImpl  implements ProductService{

	//Field
	@Autowired
	private ProductDao productDao;
	
	
	///Constructor
	public ProductServiceImpl() {
		System.out.println(this.getClass()+"������");
	}
	

	public void  addProduct(Product product) throws Exception {
		System.out.println("adddService===================");
		productDao.addProduct(product);
	
	}
	
	public Product getProduct(int prodNo) throws Exception {
		System.out.println("GETService===================");
		return productDao.getProduct(prodNo);
		}

	public Map<String, Object> getProductList(Search search) throws Exception {
		
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		System.out.println("list======="+list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateProduct(Product product) throws Exception {
		 productDao.updateProduct(product);;
	}

	@Override
	public void updateProductCount(Map<String, Object> purchase) throws Exception{
		productDao.updateProductCount(purchase);
		
	}
}
