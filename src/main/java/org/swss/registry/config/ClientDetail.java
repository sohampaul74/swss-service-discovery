package org.swss.registry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.client.detail")
public class ClientDetail {

	private String registrationId;
	private String id;
	private String secret;
	private String authorizationUri;
	private String tokenUri;
	
	public ClientDetail() { super(); }
	
	public String getTokenUri() {
		return tokenUri;
	}

	public void setTokenUri(String tokenUri) {
		this.tokenUri = tokenUri;
	}

	public String getAuthorizationUri() {
		return authorizationUri;
	}

	public void setAuthorizationUri(final String authorizationUri) {
		this.authorizationUri = authorizationUri;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(final String registrationId) {
		this.registrationId = registrationId;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(final String secret) {
		this.secret = secret;
	}
}
