package com.model2.mvc.service.user;

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

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	private UserService userService;
	//setter Method ���� ����
	@Value("${search.pageUnit}")
	private int pageUnit;
	
	@Value("${search.pageSize}")
	private int pageSize;
	
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}

	@RequestMapping( value="json/listUser", method=RequestMethod.POST )
	public Map<String, Object> login(	@RequestBody Search search, Model model,
									HttpServletRequest request ) throws Exception{
		System.out.println("/user/json/listUser : POST");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}else {
			
			search.setCurrentPage(search.getCurrentPage()+1);
			
		}

		search.setPageSize(pageSize);
		
		
		
		
		Map<String, Object> map =userService.autoUserList(search);
		List<User>  users = (List<User>)map.get("list");
		List<String> lists = new ArrayList();
		List<String> userName = new ArrayList();
		
		for(User user : users) {
			lists.add(user.getUserId());
			userName.add(user.getUserName());
		}
		System.out.println("lists-====="+lists);
		System.out.println("listName======"+userName);
		map.put("list", lists);
		map.put("listName", userName);
	
		
		
		return map;
		
	}

}