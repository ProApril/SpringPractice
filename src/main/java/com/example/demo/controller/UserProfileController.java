package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class UserProfileController {

	private UserProfileMapper mapper;
	
	public UserProfileController(UserProfileMapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	
	@RequestMapping("/index")
	public ModelAndView toMainPage(){

		ModelAndView mav = new ModelAndView();
		String name = mapper.getUserProfileList().get(0).getUser_name();
		String id = mapper.getUserProfileList().get(0).getId();
		String pw = mapper.getUserProfileList().get(0).getPhone();
		String email = mapper.getUserProfileList().get(0).getAddress();
		
	    mav.addObject("name",name);
	 	mav.addObject("id", id);
		return mav;
	}
	
	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return mapper.getUserProfile(id);
	}
	
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return mapper.getUserProfileList();
	}
	
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.insertUserProfile(id, name, phone, address);
	}
	
	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		mapper.updateUserProfile(id, name, phone, address);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		mapper.deleteUserProfile(id);
	}
}
