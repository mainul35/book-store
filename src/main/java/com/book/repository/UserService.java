package com.book.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.book.entity.User;
import com.book.entity.PasswordResetToken;
import com.book.entity.UserRole;

@Repository
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	public User createUser(User user) throws Exception;
	
	User save(User user);
}
