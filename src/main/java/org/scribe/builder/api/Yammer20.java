package org.scribe.builder.api;

import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.extractors.YammerTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;

public class Yammer20 extends DefaultApi20 {

	protected static final String AUTHORIZATION_URL = "https://www.yammer.com/dialog/oauth?client_id=%s&redirect_uri=%s";

	protected static final String ACCESS_TOKEN_ENDPOINT = "https://www.yammer.com/oauth2/access_token.json";

	@Override
	public String getAccessTokenEndpoint() {
		return (ACCESS_TOKEN_ENDPOINT);
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		return (String.format(AUTHORIZATION_URL, config.getApiKey(), config.getCallback()));
	}

	@Override
	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

	@Override
	public AccessTokenExtractor getAccessTokenExtractor() {
		return new YammerTokenExtractor();
	}
}
