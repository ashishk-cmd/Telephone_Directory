package aiims.cf.td_api.payload.request;

public class MyRequestBody {

	private String client_id;
    private String client_secret;
    private String client_serID;
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getClient_serID() {
		return client_serID;
	}
	public void setClient_serID(String client_serID) {
		this.client_serID = client_serID;
	}

}
