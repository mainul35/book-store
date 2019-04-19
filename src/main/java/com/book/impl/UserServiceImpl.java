package com.book.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.entity.auth.User;
import com.book.entity.auth.PasswordResetToken;
import com.book.repository.PasswordResetTokenRepository;
import com.book.repository.RoleRepository;
import com.book.repository.UserRepository;
import com.book.repository.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
	
	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		if(localUser != null) {
			LOG.error("username {} already taken.", user.getUsername());
			throw new Exception("Username already taken.");
		}
		localUser = userRepository.findByEmail(user.getEmail());
		if (localUser != null) {
            LOG.error("Email address {} already registered.", user.getEmail());
            throw  new  Exception("Email address already registered.");
        }
		else {
			localUser = userRepository.save(user);
		}
		return localUser;
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
