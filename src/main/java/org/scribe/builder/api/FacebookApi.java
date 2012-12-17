package org.scribe.builder.api;

import org.scribe.model.*;

import org.scribe.utils.*;

public class FacebookApi extends DefaultApi20
{
    private static final String AUTHORIZE_URL = "https://www.facebook.com/dialog/oauth?client_id=%s&redirect_uri=%s";
    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    private static final String FORCE_AUTH_PARAMS = "&auth_type=reauthenticate";

    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://graph.facebook.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config)
    {
        Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Facebook does not support OOB");

        // Append scope if present
        String authorizationUrl;
        if(config.hasScope()) {
            authorizationUrl = String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
        }
        else {
            authorizationUrl = String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
        }

        if(config.hasForceAuth())
            authorizationUrl = authorizationUrl + FORCE_AUTH_PARAMS;

        return(authorizationUrl);
    }

  @Override
  public String getRefreshTokenParameterName()
  {
    return "fb_exchange_token";
  }
}