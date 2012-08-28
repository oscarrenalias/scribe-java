package org.scribe.extractors;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;

import java.io.IOException;

public class YammerTokenExtractor implements AccessTokenExtractor {

	// This is the kind of responses provided by Yammer:
	//	{
	//	  "access_token": {
	//	    "view_subscriptions": true,
	//	    "modify_subscriptions": true,
	//	    "user_id": 1043042,
	//	    "authorized_at": "2012\/08\/28 21:54:12 +0000",
	//	    "created_at": "2012\/08\/28 21:54:12 +0000",
	//	    "view_tags": true,
	//	    "modify_messages": true,
	//	    "view_members": true,
	//	    "expires_at": null,
	//	    "network_id": 538,
	//	    "view_messages": true,
	//	    "network_name": "Accenture",
	//	    "view_groups": true,
	//	    "network_permalink": "accenture.com",
	//	    "token": "B05y1NqpVVwl8QwngXURuQ"
	//	  },
	//	  "network": {
	//	   ...
	//	}

	@Override
	public Token extract(String response) {
		try {
			JsonNode rootNode = (new ObjectMapper()).readTree(response);
			return(new Token(rootNode.get("access_token").get("token").asText(), response));
		} catch (Exception e) {
			throw new OAuthException("Cannot extract an access token from Yammer OAuth 2.0. Response was: " + response);
		}
	}
}
