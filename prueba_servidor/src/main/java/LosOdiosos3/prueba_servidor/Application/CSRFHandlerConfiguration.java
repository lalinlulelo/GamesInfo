package LosOdiosos3.prueba_servidor.Application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 	Protección contra ataques Cross Site Request Forgery, mandando tokens en los formularios
 	y verificar que es válido dicho token
 */

// activación del handle
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
}

// handler
class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {	
	@Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
    	modelAndView.addObject("token", token.getToken());    	
    }
}
