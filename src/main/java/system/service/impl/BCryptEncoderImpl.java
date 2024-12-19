package system.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import system.service.BCryptEncoder;
@Repository
public class BCryptEncoderImpl implements BCryptEncoder {
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public String encode(String password) {
		// TODO Auto-generated method stub
		return passwordEncoder.encode(password);
	}
	@Override
	public boolean match(String password, String hash) {
		// TODO Auto-generated method stub
		 return passwordEncoder.matches(password, hash);
	}

}
