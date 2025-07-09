package demo_apps.java.spring.bankservice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.method.MethodAuthorizationDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class MaskAuthorizationDeniedHandler implements MethodAuthorizationDeniedHandler {

	@Override
	public Object handleDeniedInvocation(MethodInvocation invocation, AuthorizationResult authResult) {
		// Custom logic to handle authorization denied exceptions
		// Mask the return value since the user is not authorized
		return "****";
	}

}
