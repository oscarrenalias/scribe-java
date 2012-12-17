package org.scribe.builder.api;

import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Verb;

public class GoogleApi20
    extends DefaultApi20
{
    private static final String AUTHORIZATION_URL =
        "https://accounts.google.com/o/oauth2/auth?client_id=%s&redirect_uri=%s&scope=%s&response_type=code";
    private static final String FORCE_AUTH = "&approval_prompt=force";

    @Override
    public String getAuthorizationUrl( OAuthConfig config )
    {
        String authorizationUrl;
        authorizationUrl = String.format( AUTHORIZATION_URL, config.getApiKey(), config.getCallback(), config.getScope() );
        if(config.getForceAuth())
            authorizationUrl = authorizationUrl + FORCE_AUTH;

        return(authorizationUrl);
    }

    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://accounts.google.com/o/oauth2/token";
    }

    @Override
    public Verb getAccessTokenVerb()
    {
        return Verb.POST;
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor()
    {
        return new JsonTokenExtractor();
    }

    @Override
    public String getTokenParameterName()
    {
        /* google OAuth2.0 specs needs oauth_token parameter name instead of access_token */
        return OAuthConstants.TOKEN;
    }
}
