package com.spring.app.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.app.entity.User;
import com.spring.app.repository.UserRepository;
import com.spring.app.service.EmailService;

@Controller
public class ResetPasswordController {

	@Autowired
	private EmailService mailservice;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/forgot_password")
	public String forgotpassw() {
		return "forgotPass";
	}
	
	@PostMapping("/send_otp")
	public String getOTP(@RequestParam("email") String email,HttpSession session) {
		Random random = new Random(1000);
		int otp = random.nextInt(9999);
		
		String subject = "OTP : ";
		String message = "your opt is : " + otp;
		String to = email;
		
		session.setAttribute("exOTP", otp);
		session.setAttribute("memail", email);
		
		this.mailservice.sendEmail(subject, message, to);
		
		return "VerifyOtp";
	}
	
	@PostMapping("/verify_otp")
	public String verifyOTP(@RequestParam("otp") int opt,HttpSession session) {
		int votp = (int)session.getAttribute("exOTP");
		String email = (String)session.getAttribute("memail");
		
		if(votp==opt) {
		User user = urepo.findUser(email);

			if(user == null) {
				System.out.println("user with email :"+email+"not exist");
			}
			
			return "changePass";
		}else {
			return "forgotPass";
		}		
	}
	
	@PostMapping("/change_pass")
	public String changePassword(@RequestParam("newpassword") String newpassword,HttpSession session) {
		String email = (String)session.getAttribute("memail");
		User user = this.urepo.findUser(email);
		user.setPassword(encoder.encode(newpassword));
		urepo.save(user);
		return "login";
	}
}
