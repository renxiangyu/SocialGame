package db;



/** 
 * 类说明：   OAuth认证返回的数据集合
 
 */
public class AccessInfo 
{
	//用户Id
	private String userID;
	
	//accessToken
	private String accessToken;
	
	//accessSecret
	private String accessSecret;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessSecret() {
		return accessSecret;
	}
	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}
}