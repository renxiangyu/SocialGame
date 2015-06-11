package game.pack;

import game.pack.common.DialogUtils.DialogCallBack;

import game.pack.common.DialogUtils;

import java.util.ArrayList;

import db.AccessInfo;
import db.AccessInfoHelper;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AndroidExample extends Activity {
	
	
	protected Context mContext;
	protected Activity instance;
	
	private String tokenKey=new String();
	private String tokenSecret=new String();
	private AccessInfo accessInfo=new AccessInfo();
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibomain);
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
    	System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
    	
    	mContext = getApplicationContext();
		instance = this;
    	
    	if(getAccessInfo(mContext)!=null)
    	{    	   		    		
    		DialogUtils.dialogBuilder(AndroidExample.this, "提示","是否登录默认用户？", new DialogCallBack(){
				
				
				@Override
				public void callBack() {
					accessInfo=getAccessInfo(mContext);
		    		tokenKey=accessInfo.getAccessToken();
		    		tokenSecret=accessInfo.getAccessSecret();
		    		
		    		Intent intent=new Intent(AndroidExample.this,ShareImageActivity.class);
		        	intent.putExtra("key", tokenKey);
		        	intent.putExtra("secret", tokenSecret);
		        	AndroidExample.this.startActivity(intent);
		            finish();
				}
				@Override
				public void callCancel()
				{
	    			Weibo weibo = OAuthConstant.getInstance().getWeibo();
		        	RequestToken requestToken;
					try {
						requestToken =weibo.getOAuthRequestToken("weibo4android://OAuthActivity");
		    			Uri uri = Uri.parse(requestToken.getAuthenticationURL()+ "&from=xweibo");
		    			OAuthConstant.getInstance().setRequestToken(requestToken);
		    			startActivity(new Intent(Intent.ACTION_VIEW, uri));
		    			finish();
					} catch (WeiboException e) {
						e.printStackTrace();
					}
	    		}
				@Override
				public void callAll(){}
				
				
				
    		});
    		
    		    		
    	}    
    	  	
    	

    	else
    	{
    		Weibo weibo = OAuthConstant.getInstance().getWeibo();
        	RequestToken requestToken;
    		try {
    			requestToken =weibo.getOAuthRequestToken("weibo4android://OAuthActivity");
    			Uri uri = Uri.parse(requestToken.getAuthenticationURL()+ "&from=xweibo");
    			OAuthConstant.getInstance().setRequestToken(requestToken);
    			startActivity(new Intent(Intent.ACTION_VIEW, uri));
    			finish();
    		} catch (WeiboException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    	
    	
    	/*Button beginOuathBtn=  (Button) findViewById(R.id.Button01);
    	

    	beginOuathBtn.setOnClickListener(new Button.OnClickListener()
        {

            public void onClick( View v )
            {
            	Weibo weibo = OAuthConstant.getInstance().getWeibo();
            	RequestToken requestToken;
				try {
					requestToken =weibo.getOAuthRequestToken("weibo4android://OAuthActivity");
	    			Uri uri = Uri.parse(requestToken.getAuthenticationURL()+ "&from=xweibo");
	    			OAuthConstant.getInstance().setRequestToken(requestToken);
	    			startActivity(new Intent(Intent.ACTION_VIEW, uri));
				} catch (WeiboException e) {
					e.printStackTrace();
				}
    			
            }
        } );*/
	}
	
	public static AccessInfo getAccessInfo(Context mContext)
	{
		ArrayList<AccessInfo> list = null;
		AccessInfoHelper accessDBHelper = new AccessInfoHelper(mContext);
		accessDBHelper.open();
		
		try
		{
			 list=accessDBHelper.getAccessInfos();
		}
		finally
		{
			accessDBHelper.close();
		}
			return (list!=null&&list.size()!=0)?list.get(0):null;
	}
	
}