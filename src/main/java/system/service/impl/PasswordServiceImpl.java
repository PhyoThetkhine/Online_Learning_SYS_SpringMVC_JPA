package system.service.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import system.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {
	private static final String PASSWORD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    private static final int PASSWORD_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();
	@Override
	public String generatePassword() {
		StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(PASSWORD_CHARS.charAt(random.nextInt(PASSWORD_CHARS.length())));
        }
        return password.toString();
    }

}
