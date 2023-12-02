package com.model2.mvc.service.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회占쏙옙占쏙옙占쏙옙 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	private ProductService productService;
	//setter Method 占쏙옙占쏙옙 占쏙옙占쏙옙
	
	@Value("${search.pageUnit}")
	private int pageUnit;
	
	@Value("${search.pageSize}")
	private int pageSize;
	
	public ProductController(){
		System.out.println(this.getClass());
	}

	
	//@RequestMapping("/addProductView.do")
	//public String addProductView() throws Exception {
	@RequestMapping( value = "addProduct", method = RequestMethod.GET)
	public String addProduct( @RequestParam("prodNo") int prodNo, Model model) throws Exception{
		System.out.println("/product/addProduct :GET");
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/readProduct.jsp";
		/*
		 * ModelAndView modelAndView = new ModelAndView();
		 * modelAndView.setViewName("forward:/product/readProduct.jsp");
		 * modelAndView.addObject("product", product);
		 * 
		 * return modelAndView;
		 */
		
	}
	
	//@RequestMapping("/addProduct.do")
	@RequestMapping(value = "addProduct" , method = RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product, Model model,
								@RequestParam("file") MultipartFile[] files,
								HttpServletRequest request) throws Exception {
//		System.out.println(product);
//		if(FileUpload.isMultipartContent(request)) {
//			
//			String temDir ="C:\\workspace\\10.Model2MVCShop(Ajax) (1)\\src\\main\\webapp\\images\\uploadFiles\\";
//			
//			DiskFileUpload fileUpload = new DiskFileUpload();
//			fileUpload.setRepositoryPath(temDir);
//			fileUpload.setSizeMax(1024 * 1024 * 10);
//			fileUpload.setSizeThreshold(1024 * 100);
//			
//			//占쏙옙占싸듸옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 크占쏙옙 占싱놂옙占쏙옙占쏙옙 확占쏙옙占싹댐옙 占쏙옙 占쏙옙占�
//			//占쏙옙占쏙옙 크占쏙옙 占싱놂옙占쏙옙 占쏙옙占� 占쏙옙
//			if(request.getContentLength() < fileUpload.getSizeMax()) {
//			
//				//占쌔쏙옙트 占쏙옙占쏙옙占쏙옙 占식쏙옙占싹거놂옙, 占쌔쏙옙트 占쏙옙占� 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占싹곤옙 처占쏙옙
//				StringTokenizer token = null;
//				
//				//HTTP 占쏙옙청占쏙옙 占싻쇽옙占싹곤옙 占쏙옙占싸듸옙占� 占쏙옙占쏙옙占쏙옙 fileItemList占쏙옙 占쏙옙占쏙옙
//				List fileItemList = fileUpload.parseRequest(request);
//				
//				//占싱뱄옙占쏙옙 占쏘개占쏙옙 占쏙옙占싸듸옙 占쌩댐옙占쏙옙 확占쏙옙
//				int size = fileItemList.size();
//				
//				for(int i = 0; i<size; i++) {
//					
//					FileItem fileItem = (FileItem)fileItemList.get(i);
//					
//					//input type="file"占쏙옙 占쏙옙 占싼억옙占쏙옙占쏙옙占� true
//	                if (fileItem.isFormField()) {
//	                    if (fileItem.getFieldName().equals("manuDate")) {
//	                        token = new StringTokenizer(fileItem.getString("euc-kr"), "-");
//	                        String manuDate = token.nextToken() + token.nextToken() + token.nextToken();
//	                        product.setManuDate(manuDate);
//	                    } else if (fileItem.getFieldName().equals("prodName"))
//	                        product.setProdName(fileItem.getString("euc-kr"));
//	                    else if (fileItem.getFieldName().equals("prodDetail"))
//	                        product.setProdDetail(fileItem.getString("euc-kr"));
//	                    else if (fileItem.getFieldName().equals("price"))
//	                        product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
//	                } else { // 占쏙옙占쏙옙 占쏙옙占쏙옙占싱몌옙...
//	                    if (fileItem.getSize() > 0) { // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 if
//	                        int idx = fileItem.getName().lastIndexOf("\\");
//	                        // getName()占쏙옙 占쏙옙罐占� 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 lastIndexOf占쏙옙 占쌩라낸댐옙
//	                        if (idx == -1) {
//	                            idx = fileItem.getName().lastIndexOf("/");
//	                        }
//	                        String fileName = fileItem.getName().substring(idx + 1);
//	                        product.setFileName(fileName);
//	                        try {
//	                            File uploadedFile = new File(temDir, fileName);
//	                            fileItem.write(uploadedFile);
//	                           
//	                        } catch (IOException e) {
//	                            System.out.println(e);
//	                        }
//	                    } else {
//	                        product.setFileName("../../images/empty.GIF");
//	                    }
//				}
//		}
//	}
//		}
//		List<String> fileNames = new ArrayList<>();
//		for (MultipartFile file : files) {
//	        if (!file.isEmpty()) {
//	            String originalFileName = file.getOriginalFilename();
//	            String uploadPath = request.getServletContext().getRealPath("/images/uploadFiles/");
//	            //String uploadPath = "C:\\Users\\bitcamp\\git\\model2MVCShop\\07.Model2MVCShop(URI,pattern)\\src\\main\\webapp\\images\\uploadFiles";
//	            System.out.println("占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙"+uploadPath);
//	            File uploadDir = new File(uploadPath);
//	            if (!uploadDir.exists()) {
//	                uploadDir.mkdirs(); // 占쏙옙占썰리占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
//	            }
//
//	            try {
//	                String uploadedFilePath = uploadPath + File.separator + originalFileName;
//	                file.transferTo(new File(uploadedFilePath));
//	                // 占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙트占쏙옙 占쌩곤옙
//	                fileNames.add(originalFileName);
//	                product.setFileName(fileNames.toString().replace("[", "").replace("]", ""));
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	                // 占쏙옙占쏙옙 占쏙옙占싸듸옙 占쏙옙 占쏙옙占쏙옙 占쌩삼옙 占쏙옙 占쏙옙占쏙옙 처占쏙옙
//	            }
//	        }
//	    }
					productService.addProduct(product);
		
					// Model 占쏙옙 View 占쏙옙占쏙옙
					model.addAttribute("product", product);
					return "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/getProduct.do")
	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct( @RequestParam int prodNo , Model model ) throws Exception {

		System.out.println("/product/getProduct :GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 占쏙옙 View 占쏙옙占쏙옙
		model.addAttribute("product", product);
		
		return  "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping(value = "listProduct")
	public String listProduct(  @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/listProduct ");
		//Business Logic
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 占쏙옙占쏙옙
		Map<String , Object> map=productService.getProductList(search);
		
		System.out.println("map==="+map);
		String menu = request.getParameter("menu");
		System.out.println("MEMU==="+menu);
		
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 占쏙옙 View 占쏙옙占쏙옙
		model.addAttribute("list", map.get("list"));
		//model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu" , menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	//@RequestMapping("/updateProductView.do")
	@RequestMapping(value ="updateProduct", method = RequestMethod.GET)
	public String updateProductView( @RequestParam("prodNo")int prodNo , Model model  ) throws Exception{

		System.out.println("/product/updateProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 占쏙옙 View 占쏙옙占쏙옙
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	//@RequestMapping("/updateProduct.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProduct : POST");
		//Business Logic
		
		productService.updateProduct(product);
		

		
		return "forward:/product/getProduct.jsp";
	}

	}
