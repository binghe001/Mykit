package io.mykit.annotation.spring.service.impl;

import org.springframework.stereotype.Service;

import io.mykit.annotation.spring.provider.RequiresLogin;
import io.mykit.annotation.spring.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	@RequiresLogin
	public String getUserInfo() {
		try {
			return "[{'id': 1, 'username':'liuyazhuang', 'sex':'mail', 'age':'18', 'address':'chengdu'}]";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
