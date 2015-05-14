package jp.canetrash.vicuna.logic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import jp.canetrash.vicuna.Const;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;

/**
 * @author tfunato
 *
 */
@Component
public class OAuthLogic {

	private static final String SCOPE = "https://www.googleapis.com/auth/gmail.readonly";
	private static final String APP_NAME = "Gmail API";
	private static final String CLIENT_SECRET_PATH = Const.STORE_BASE_DIR
			+ "client_secret.json";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			Const.STORE_BASE_DIR, "oauth2");

	private GoogleAuthorizationCodeFlow flow;

	private HttpTransport httpTransport = new NetHttpTransport();
	private JsonFactory jsonFactory = new JacksonFactory();

	public OAuthLogic() {

		try {
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
					jsonFactory, new FileReader(CLIENT_SECRET_PATH));

			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					httpTransport, jsonFactory, clientSecrets,
					Arrays.asList(SCOPE)).setDataStoreFactory(
					new FileDataStoreFactory(DATA_STORE_DIR)).build();
			this.flow = flow;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isAuthorized() {
		try {
			Credential credential = flow.loadCredential("user");
			if (credential != null
					&& (credential.getRefreshToken() != null || credential
							.getExpiresInSeconds() > 60)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getAuthPage() {

		String url = flow.newAuthorizationUrl()
				.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).build();
		return url;
	}

	public Gmail getGmailService() {
		try {
			Credential credential = flow.loadCredential("user");
			return new Gmail.Builder(httpTransport, jsonFactory, credential)
					.setApplicationName(APP_NAME).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void storeCredential(String code) {
		try {
			GoogleTokenResponse response = flow.newTokenRequest(code)
					.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
					.execute();
			flow.createAndStoreCredential(response, "user");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}