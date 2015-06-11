package game.pack;

import game.pack.common.DialogUtils.DialogCallBack;
import game.pack.common.DialogUtils;

import java.util.List;

import db.AccessInfo;
import db.AccessInfoHelper;

import weibo4android.Paging;
import weibo4android.Status;
import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OAuthActivity extends Activity {
	
	
	protected Activity instance;
	protected Context mContext;
	
	private String userId=new String();
	private String tokenKey=new String();
	private String tokenSecret=new String();
	private AccessInfo accessInfo=new AccessInfo();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline);
		
		
		instance=this;
		mContext=getApplicationContext();
		
		Uri uri=this.getIntent().getData();
		try {
			RequestToken requestToken= OAuthConstant.getInstance().getRequestToken();
			AccessToken accessToken=requestToken.getAccessToken(uri.getQueryParameter("oauth_verifier"));
			OAuthConstant.getInstance().setAccessToken(accessToken);
			userId=Long.toString(accessToken.getUserId());
			tokenKey=accessToken.getToken();
			tokenSecret=accessToken.getTokenSecret();
			
			
			/*TextView textView = (TextView) findViewById(R.id.TextView01);
			textView.setText("得到AccessToken的key和Secret,可以使用这两个参数进行授权登录了.\n Access token:\n"+accessToken.getToken()+"\n Access token secret:\n"+accessToken.getTokenSecret());
*/		} catch (WeiboException e) {
			e.printStackTrace();
		}

DialogUtils.dialogBuilder(OAuthActivity.this, "提示","是否记住该用户", new DialogCallBack(){
	
	
	public void callBack() {
		accessInfo.setUserID(userId);
		accessInfo.setAccessToken(tokenKey);
		accessInfo.setAccessSecret(tokenSecret);
      
    	
		AccessInfoHelper accessInfoHelper=new AccessInfoHelper(mContext);
		accessInfoHelper.open();
		accessInfoHelper.delete();
		accessInfoHelper.create(accessInfo);
		accessInfoHelper.close();
	}
	public void callCancel(){}
	public void callAll()
	{
		Intent intent=new Intent(OAuthActivity.this,ShareImageActivity.class);
    	intent.putExtra("key", tokenKey);
    	intent.putExtra("secret", tokenSecret);
    	OAuthActivity.this.startActivity(intent);	
    	finish();
	}
	
});










		/*Button button=  (Button) findViewById(R.id.Button01);
		button.setText("某一话题下的微博");
		button.setOnClickListener(new Button.OnClickListener()
        {

            public void onClick( View v )
            {
    				Weibo weibo=OAuthConstant.getInstance().getWeibo();
    				weibo.setToken(OAuthConstant.getInstance().getToken(), OAuthConstant.getInstance().getTokenSecret());
    				List<Status> friendsTimeline;
    					try {
							friendsTimeline = weibo.getTrendStatus("seaeast", new Paging(1,20));
							StringBuilder stringBuilder = new StringBuilder("1");
	    					for (Status status : friendsTimeline) {
	    						stringBuilder.append(status.getUser().getScreenName() + "说:"
	    								+ status.getText() + "-------------------------\n");
	    					}
	    					TextView textView = (TextView) findViewById(R.id.TextView01);
	    					textView.setText(stringBuilder.toString());
						} catch (WeiboException e) {
							e.printStackTrace();
						}
    					
            }
        } );*/
//		
	}
}
