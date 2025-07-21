package org.kobus.spring.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customLoginSuccessHandler")
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication
			) throws IOException, ServletException {
		log.warn("😘😘😘 Login Success...");
		// 인증사용자가 가지고 있는 롤(Role) == 권한
		
		// 로그인한 사용자 ID (username)
	    String username = authentication.getName();

	    // 세션에 id 저장 -> header에 auth로 불러오기 때문에 auth로 설정 
	    request.getSession().setAttribute("auth", username);
		
		List<String> roleNames = new ArrayList<String>();			
		authentication.getAuthorities().forEach( auth -> {
			roleNames.add(auth.getAuthority());
		} );
		
		log.warn("👍 > ROLE NAMES : " + roleNames );
		
		if ( roleNames.contains("ROLE_USER") ) {
			response.sendRedirect("/koBus/main.do");
			return;
		} else {
			response.sendRedirect("/koBus/main.do");
			return;
		}
		
	}

}
