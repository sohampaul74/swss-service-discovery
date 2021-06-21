package org.swss.registry.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/eureka/**").permitAll()
			.antMatchers("/actuator/**").hasAnyRole("SCOPE_ROLE_ACTUATOR_SUPERVISOR")
			.antMatchers("/**").hasAnyAuthority("SCOPE_ROLE_SERVICE_LEVEL","SCOPE_ROLE_SYSTEM_SUPERVISOR")
			.and()
			.oauth2ResourceServer(oath2->oath2.opaqueToken());//.introspector(tokenIntrospector()));
	}

	@Bean
	public NimbusOpaqueTokenIntrospector tokenIntrospector() {
		String clientId = "discovery";
		String clientSecret = "mobile";
		String introspectionId = "http://localhost:8081/oauth/check_token";
		NimbusOpaqueTokenIntrospector introspector = new NimbusOpaqueTokenIntrospector(introspectionId, clientId, clientSecret);
		introspector.setRequestEntityConverter(new Converter<String, RequestEntity<?>>() {

			@Override
			public RequestEntity<?> convert(String source) {
				System.out.println("source");
				Map<String,String> headers = new HashMap<>();
				headers.put("Authorization", Base64.encodeBase64String((clientId+":"+clientSecret).getBytes()));
				return new RequestEntity<>(headers, HttpMethod.GET, URI.create(introspectionId+"?token="+source));
			}
		});
		return introspector;
	}
	
	/**
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistrationRepository crr = new InMemoryClientRegistrationRepository(clientRegistration());
		return crr;
	}

	@Bean
	public ClientRegistration clientRegistration() {
		return ClientRegistration.withRegistrationId(clientDetail.getRegistrationId())
				.clientId(clientDetail.getId())
				.clientSecret(clientDetail.getSecret())
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.redirectUriTemplate("http://localhost:8761/eureka")
				.authorizationUri("http://localhost:8081/oauth/token")
				.userInfoUri("http://localhost:8081/oauth/check_token")
				.tokenUri("http://localhost:8081/oauth/token")
				.userInfoAuthenticationMethod(AuthenticationMethod.FORM)
				.providerConfigurationMetadata(providerConfiguration())
				.build();
	}

	private Map<String, Object> providerConfiguration() {
		Map<String,Object> map = new HashMap<>();
		map.put("authorization-uri", "http://localhost:8081/oauth/token");
		map.put("token-uri", "http://localhost:8081/oauth/check_token");
		map.put("user-info-uri", "http://localhost:8081/oauth/check_token");
		map.put("user-info-authentication-method", "FORM");
		return map;
	}
	**/
	
	
}
