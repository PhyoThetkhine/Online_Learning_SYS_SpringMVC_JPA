package system.service;


public interface BCryptEncoder {
	String encode(String password);
	boolean match(String password, String hash);
}
