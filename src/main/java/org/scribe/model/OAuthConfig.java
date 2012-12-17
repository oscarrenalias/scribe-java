package org.scribe.model;

import java.io.OutputStream;

/**
 * Parameter object that groups OAuth config values
 *
 * @author Pablo Fernandez
 */
public class OAuthConfig
{
  private final String apiKey;
  private final String apiSecret;
  private final String callback;
  private final SignatureType signatureType;
  private final String scope;
  private final String grantType;
  private final boolean forceAuth;
  private final OutputStream debugStream;

  public OAuthConfig(String key, String secret )
  {
    this(key, secret, null, null, null, null);
  }

  public OAuthConfig(String key, String secret, String callback, SignatureType type, String scope, OutputStream stream)
  {
    this(key, secret, callback, type, scope, stream, null, false);
  }

  public OAuthConfig(String key, String secret, String callback, SignatureType type, String scope, OutputStream stream,
                     String grantType, boolean forceAuth)
  {
    this.apiKey = key;
    this.apiSecret = secret;
    this.callback = callback != null ? callback : OAuthConstants.OUT_OF_BAND;
    this.signatureType = (type != null) ? type : SignatureType.Header;
    this.scope = scope;
    this.grantType = grantType;
    this.forceAuth = forceAuth;
    this.debugStream = stream;
  }

  public String getApiKey()
  {
    return apiKey;
  }

  public String getApiSecret()
  {
    return apiSecret;
  }

  public String getCallback()
  {
    return callback;
  }

  public SignatureType getSignatureType()
  {
    return signatureType;
  }

  public String getScope()
  {
    return scope;
  }

  public String getGrantType()
  {
    return grantType;
  }

  public boolean getForceAuth() {
      return forceAuth;
  }

  public boolean hasScope()
  {
    return scope != null;
  }

  public boolean hasGrantType()
  {
    return grantType != null;
  }

  public boolean hasForceAuth() {
      return(forceAuth);
  }

  public void log(String message)
  {
    if (debugStream != null)
    {
      message = message + "\n";
      try
      {
        debugStream.write(message.getBytes("UTF8"));
      }
      catch (Exception e)
      {
        throw new RuntimeException("there were problems while writting to the debug stream", e);
      }
    }
  }

    @Override
    public String toString() {
        return "OAuthConfig{" +
                "apiKey='" + apiKey + '\'' +
                ", apiSecret='" + apiSecret + '\'' +
                ", callback='" + callback + '\'' +
                ", signatureType=" + signatureType +
                ", scope='" + scope + '\'' +
                ", grantType='" + grantType + '\'' +
                ", forceAuth=" + forceAuth +
                ", debugStream=" + debugStream +
                '}';
    }
}
