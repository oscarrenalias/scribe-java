package org.scribe.examples;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi20;
import org.scribe.builder.api.Yammer20;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

public class YammerExample20 {

	public static final String YAMMER_APP_KEY = "bvrAHFxYcL69uaAuGItrdg";
	public static final String YAMMER_SECRET_KEY = "crHSrpY1OIR1LpeEhyqqY5ymIZnXcEvFMK0uffAVM";
	public static final String YAMMER_CALLBACK = "http://localhost:9000/oauth/authorize/yammer";

	public static void main(String args[]) {
		OAuthService service = new ServiceBuilder().
				provider( Yammer20.class ).
				apiKey( YAMMER_APP_KEY ).
				apiSecret( YAMMER_SECRET_KEY ).
				callback(YAMMER_CALLBACK).
				build();

		System.out.println( "=== Yammer OAuth 2.0 Workflow ===" );
		System.out.println();

		// Obtain the authorization url
		String url = service.getAuthorizationUrl( null );

		System.out.println( "go there : " + url );
		System.out.println( "paste the authorization code >>" );
		Scanner in = new Scanner( System.in );
		Verifier verifier = new Verifier( in.nextLine() );

		Token accessToken = service.getAccessToken( null, verifier );

		System.out.println( "Got the access Token!" );
		System.out.println( "(if you're curious it looks like this: " + accessToken + " )" );
		System.out.println();
	}
}
