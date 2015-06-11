package game.pack;

import game.pack.common.DialogUtils;
import game.pack.common.DialogUtils.DialogCallBack;

import java.io.File;


import weibo4android.Status;
import weibo4android.Weibo;


import db.AccessInfoHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ShareImageActivity extends Activity{
	
	private String tokenKey=new String();
	private String tokenSecret=new String();
	private String fileAddr=new String();
	
	private String[] args=new String[3];
	private String words=new String();
	private EditText textwords;
	private ImageView show; 
	
	private static final int TOOLBAR0 = 0;
	private static final int TOOLBAR1 = 1;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		
		textwords=(EditText)findViewById(R.id.share_content);
		show=(ImageView)findViewById(R.id.share_image_view);
		
		Intent intent=getIntent();
        tokenKey=intent.getStringExtra("key");
        tokenSecret=intent.getStringExtra("secret");       
       
 //       fileAddr="mnt/sdcard/1003536931fc577625.jpg";
        String PREFS_NAME = "game.share";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        fileAddr = settings.getString("fileAddr", "mnt/sdcard/1003536931fc577625.jpg");

  
         
        args[0]=tokenKey;
        args[1]=tokenSecret;
        args[2]=fileAddr;
        
        Bitmap bitmap=BitmapFactory.decodeFile(fileAddr);
        Matrix matrix = new Matrix(); 
        matrix.postScale(0.7f,0.7f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);             
        BitmapDrawable bmd = new BitmapDrawable(resizeBmp);   
   
        Drawable draw=bmd;
       
        show.setBackgroundDrawable(draw);
        
        
        Button button=  (Button) findViewById(R.id.Button01);
		button.setOnClickListener(new Button.OnClickListener()
        {

            @Override
			public void onClick( View v )
            {
            	words=textwords.getText().toString();
            	try {
        			if (args.length < 3) {
        				System.out.println(
        				"Usage: java weibo4j.examples.OAuthUploadByFile token tokenSecret filePath");
        				System.exit( -1);
        			}

        			System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
        			System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);

        			Weibo weibo = new Weibo();
        			
        			weibo.setToken(args[0], args[1]);			
        			try {
        				File file=new File(args[2]);
        				if(file==null){
        					System.out.println("file is null");
        					System.exit(-1);
        				}
        				Status status = weibo.uploadStatus(words, file);

        				System.out.println("Successfully upload the status to ["
        						+ status.getText() + "].");
                     Toast.makeText(ShareImageActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
		               finish();
        			} catch (Exception e1) {
        				e1.printStackTrace();
        			}
        		} catch (Exception ioe) {
        			System.out.println("Failed to read the system input.");
        		}
        		
        		
            	
            }
        } );
        			
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		menu.add(0, TOOLBAR0, 1, "退出分享" ).setIcon( android.R.drawable.ic_menu_revert );
		menu.add(0, TOOLBAR1, 2, "注销登录" ).setIcon( android.R.drawable.ic_menu_delete );
		return super.onCreateOptionsMenu(menu);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
    	if( item.getItemId() == 0 )
    	{
    		finish();
    	}
    	else
    	{	
    		DialogUtils.dialogBuilder(ShareImageActivity.this, "提示","确定要注销登录并退出？", new DialogCallBack(){
				
				@Override
				public void callBack() {
					AccessInfoHelper accessInfoHelper=new AccessInfoHelper(getApplicationContext());
					accessInfoHelper.open();
					accessInfoHelper.delete();
					accessInfoHelper.close();
					finish();
				}
				@Override
				public void callCancel(){}
				@Override
				public void callAll(){}
    		});
    	}
    	return super.onOptionsItemSelected(item);
	}
	
		
	
	
}