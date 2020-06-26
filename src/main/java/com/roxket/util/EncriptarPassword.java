package com.roxket.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Roxket
 */
public class EncriptarPassword {
	public static void main(String[] args) {
		
		var password = "123";
		System.out.println("Password: " + password);
		System.out.println("Password encriptado: " + encriptarPassword(password));
		
	}
	
	public static String encriptarPassword(String password){
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		return enconder.encode(password);
	}
}
