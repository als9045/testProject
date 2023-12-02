package com.model2.mvc.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회占쏙옙占쏙옙占쏙옙 Controller
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	private ProductService productService;
	//setter Method 占쏙옙占쏙옙 占쏙옙占쏙옙
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 占쏙옙占쏙옙 占쌀곤옙
	//==> 占싣뤄옙占쏙옙 占싸곤옙占쏙옙 占쌍쇽옙占쏙옙 풀占쏙옙 占실미몌옙 확占쏙옙 占쌀곤옙
	@Value("${search.pageUnit}")
	private int pageUnit;
	
	@Value("${search.pageSize}")
	private int pageSize;
	
	
	
	@RequestMapping(value = "json/addProduct" , method = RequestMethod.POST)
	public Product addProduct( @RequestBody Product product ) throws Exception {

		System.out.println("/product/addProduct :Post");
		//Business Logic
		productService.addProduct(product);
		
		return productService.getProduct(product.getProdNo());
	}
	
	//@RequestMapping("/getProduct.do")
	@RequestMapping(value = "json/getProduct/{prodNo}", method = RequestMethod.GET)
	public Product getProduct( @PathVariable int prodNo) throws Exception {
		/*
		 * System.out.println("No"+No); int prodNo = Integer.parseInt(No);
		 * System.out.println("prodNo"+prodNo);
		 */		System.out.println("/product/json/getProduct : GET");
		//Business Logic

		// Model 占쏙옙 View 占쏙옙占쏙옙
		
		return   productService.getProduct(prodNo);
	}
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping(value = "json/listProduct")
	public Map <String , Object> listProduct(@RequestBody Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/json/listProduct ");
		//Business Logic
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}else {
			
			search.setCurrentPage(search.getCurrentPage());
			
		}
			search.setPageSize(pageSize);
		// Business logic 占쏙옙占쏙옙
		Map<String , Object> map=productService.getProductList(search);
		System.out.println("map ==" +map);
		String menu = request.getParameter("menu");
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		List<String> resultList = new ArrayList<>();
		List<Product> productList = (List<Product>) map.get("list");
		 
		// AutoComplete占쌩곤옙 占쌘듸옙
		for (Product product : productList) {
			 if ("0".equals(search.getSearchCondition())) {
		            // searchCondition占쏙옙 0占쏙옙 占쏙옙占� userId占쏙옙 占쏙옙占쏙옙트占쏙옙 占쌩곤옙
		            resultList.add(""+product.getProdNo());
		        } else if ("1".equals(search.getSearchCondition())) {
		            // searchCondition占쏙옙 1占쏙옙 占쏙옙占� userName占쏙옙 占쏙옙占쏙옙트占쏙옙 占쌩곤옙
		            resultList.add(product.getProdName());
		        }
		}
		
		map.put("resultList", resultList);
		map.put("list", map.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);
	
		return map;
	}
		
	//@RequestMapping("/updateProduct.do")
	@RequestMapping(value = "json/updateProduct", method = RequestMethod.POST)
	public Product updateProduct( @RequestBody Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProduct : POST");
		//Business Logic
		productService.updateProduct(product);
		
		
		return product;
	}

	}
