package com.book.repository;

import org.springframework.stereotype.Repository;
import com.book.entity.User;
import com.book.entity.PasswordResetToken;

@Repository
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	public User createUser(User user) throws Exception;
	
	User save(User user);
}
