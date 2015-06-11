package game.pack;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import game.pack.common.DialogUtils;
import game.pack.common.DialogUtils.DialogCallBack;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import game.pack.db.*;

 

public class GameActivity extends Activity {
    /** Called when the activity is first created. */
	

	protected Activity instance;
	protected Context mContext;
	//������ű�activity�ͱ�applicationcontext
	
	private static final int TOOLBAR0 = 0;
	private static final int TOOLBAR1 = 1;
	
	//���ּ��ؼ�
	private Button camera;
	private Button cback;
	private Button sound;
	private Button help;
	private Button create; 
	private ImageView imageView;
	private ImageView point;
	private ImageView belowpoint;
	private ImageView rolehead;
	private ImageView rolebody1;
	private ImageView rolebody2;
	private LinearLayout toplayout;
	private LinearLayout belowlayout;
	private LinearLayout topbuttonlayout;
	private LinearLayout belowbuttonlayout;
	private LinearLayout mainlayout;
	private TextView name;
	private TextView level;
	private TextView playtime;
	//���ּ��ؼ�
	
	
	
	 private String imageAdr;//���ɵĽ�ͼ��ַ
	
	
	private final static int BPICAMT=5;
	private int bpicno=0 ;
	private int backpic[]={R.drawable.beijing_1,R.drawable.beijing_2,R.drawable.beijing_3 ,R.drawable.beijing_4 ,R.drawable.beijing_5 };
	 //����ͼƬ����������ʼͼƬ��ͼƬid��bpicno��Ҫ����Ϸ��ʼʱ��ȡ��ʷ��¼��
	
	
    private Boolean isSound=true;
    private static int volum; 
    private AudioManager mAM ;
    private MediaPlayer player; 
    //���ֲ��ŵĲ�����volumeΪ�ֻ�ϵͳ����������С����Ҫ����Ϸ��ʼʱ��ȡ����
    //isSound��Ҫ��ȡ��ʷ��¼
    
    private boolean mtophide=true;//�Ϸ��˵����ر��
    private boolean mbelowhide=true;
    

    private RoleInfo roleInfo=new RoleInfo();
    private int slevel[]={R.string.level0,R.string.level1,R.string.level2,R.string.level3};
    //��ɫ����Ϣ,��������
	
  //����������
	//����
	 private AnimationDrawable drawcro ;
	 private AnimationDrawable drawdan ;
	 private AnimationDrawable drawkfj ;
	 private AnimationDrawable drawsmi ;
	 private AnimationDrawable drawtjz ;
	 private AnimationDrawable drawpla ;
	 
	 //ͼ��
	 public ImageView cro ;
	 public ImageView dance ;
	 public ImageView kfj ;
	 public ImageView star ;
	 public ImageView starters ;
	 public ImageView starteres ;
	 public ImageView bask ;
	 
     //����
	 public ImageView croa ;
	 public ImageView dancea ;
	 public ImageView kfja  ;
	 public ImageView diza1 ;
	 public ImageView diza2 ;
	 public ImageView diza3 ;
	 public ImageView diza4 ;
	 public ImageView plaa ;
	 public ImageView tjza ;
	//����������ӦͼƬ����
	 public  ImageView  pbasketball0 ,pbasketball2 ;
	 
	 private long initTime = 0;  
	 private long lastTime = 0;  
	 private long curTime = 0;  
	 private long duration = 0;  

		
     private float last_x = 0.0f;  
	 private float last_y = 0.0f;  
     private float last_z = 0.0f;  
	    
	 private float shake = 0.0f;  
	 private float totalShake = 0.0f;
	 
     public int d1 = 0 ;
	 public int d2 = 0 ;
	 public int d3 = 0 ;
	 public int d4 = 0 ;
	 
	 public int total = 0 ;
	 
	 private long pinitTime = 0;  
	 private long plastTime = 0;  
	 private long pcurTime = 0;  
	 private long pduration = 0;  

		
     private float plast_x = 0.0f;  
	 private float plast_y = 0.0f;  
	 private float plast_z = 0.0f;  
	    
	 private float pshake = 0.0f;  
     private float ptotalShake = 0.0f;
	    
	 public int  b = 0 ;
	 public int i = 0 , j = 0 , ptotal = 0 ;
	 //�������ֽ���
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        instance = this;
		mContext = getApplicationContext();
        
		
		mAM = (AudioManager) GameActivity.this.getSystemService(Context.AUDIO_SERVICE);
		
        //Ϊ���ּ��ؼ���ֵ
		cback=(Button)findViewById(R.id.cback);
        camera = (Button)findViewById(R.id.camera);
        sound = (Button)findViewById(R.id.sound);
        help = (Button)findViewById(R.id.help);
        create = (Button)findViewById(R.id.create);
        toplayout=(LinearLayout)findViewById(R.id.toplayout);
        belowlayout=(LinearLayout)findViewById(R.id.belowlayout);
        topbuttonlayout=(LinearLayout)findViewById(R.id.topbuttonlayout);
        belowbuttonlayout=(LinearLayout)findViewById(R.id.belowbuttonlayout);
        mainlayout=(LinearLayout)findViewById(R.id.mainlayout);
        imageView = new ImageView(this);
        point=(ImageView)findViewById(R.id.point);
        belowpoint=(ImageView)findViewById(R.id.belowpoint);
        rolehead=(ImageView)findViewById(R.id.rolehead);
        rolebody1=(ImageView)findViewById(R.id.imageview001);
        rolebody2=(ImageView)findViewById(R.id.imageview002);
        name=(TextView)findViewById(R.id.name);
        level=(TextView)findViewById(R.id.level);
        playtime=(TextView)findViewById(R.id.playtime);
        //Ϊ���ּ��ؼ���ֵ
        
        //���ּ��ؼ��ĵ���¼�
        camera.setOnClickListener(new CameraListener());
        sound.setOnClickListener(new SoundListener());
        help.setOnClickListener(new HelpListener());
        create.setOnClickListener(new CreateListener());
        cback.setOnClickListener(new CbackListener());
        point.setOnClickListener(new PointListener());
        belowpoint.setOnClickListener(new BelowPointListener());
        
        
    
        
  	  //����������
  	  //��ʼͼƬ
  	  starters = (ImageView)findViewById(R.id.imageview001);//�ϰ���
  	  starteres =(ImageView)findViewById(R.id.imageview002);//�°���
  	  
  	  //����ͼƬͼ��
  	   cro  =  (ImageView)findViewById(R.id.imageview2) ;
  	   dance = (ImageView)findViewById(R.id.imageview3) ;
  	   kfj   = (ImageView)findViewById(R.id.imageview1) ;
  	   star =  (ImageView)findViewById(R.id.imageview5) ;
  	   bask =  (ImageView)findViewById(R.id.imageview4) ;
  	  
  	  //���嶯��
  	  final ImageView croa = (ImageView)findViewById(R.id.imageview02);
  	  croa.setVisibility(View.INVISIBLE);
  	  drawcro = (AnimationDrawable)croa.getDrawable();
  	  
  	  final ImageView dancea = (ImageView)findViewById(R.id.imageview03);
  	  dancea.setVisibility(View.INVISIBLE);
  	  drawdan = (AnimationDrawable)dancea.getDrawable();
  	  
  	  final ImageView kfja = (ImageView)findViewById(R.id.imageview01);
  	  kfja.setVisibility(View.INVISIBLE);
  	  drawkfj = (AnimationDrawable)kfja.getDrawable();
  	  
  	  final ImageView smia = (ImageView)findViewById(R.id.imageview04);
  	  smia.setVisibility(View.INVISIBLE);
  	  drawsmi = (AnimationDrawable)smia.getDrawable();
  	  
  	  final ImageView tjza = (ImageView)findViewById(R.id.imageview05);
	  tjza.setVisibility(View.INVISIBLE);
	  drawtjz = (AnimationDrawable)tjza.getDrawable();
  	  
  	  final ImageView plaa = (ImageView)findViewById(R.id.imageview11);
  	  plaa.setVisibility(View.INVISIBLE);
  	  drawpla = (AnimationDrawable)plaa.getDrawable();
  	  
  	  final ImageView diz1 = (ImageView)findViewById(R.id.imageview07);
      diz1.setVisibility(View.INVISIBLE);
    	  
      final ImageView diz2 = (ImageView)findViewById(R.id.imageview08);
  	  diz2.setVisibility(View.INVISIBLE);
  	  
  	  final ImageView diz3 = (ImageView)findViewById(R.id.imageview09);
	  diz3.setVisibility(View.INVISIBLE);
	  
	  final ImageView diz4 = (ImageView)findViewById(R.id.imageview10);
      diz4.setVisibility(View.INVISIBLE);
  	  
        //����Ͷ��ͼƬ
  	  pbasketball0 = (ImageView)findViewById(R.id.imageview12);
  	  pbasketball0.setVisibility(View.INVISIBLE);
  	  
  	  pbasketball2 = (ImageView)findViewById(R.id.imageview13);
  	  pbasketball2.setVisibility(View.INVISIBLE);
  	  
  	 
  	  
  	  star.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
       public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		 croa.setVisibility(View.INVISIBLE);
  	     dancea.setVisibility(View.INVISIBLE);
  		 kfja.setVisibility(View.INVISIBLE);
  		 starters.setVisibility(View.VISIBLE);
  		 smia.setVisibility(View.INVISIBLE);
  		 diz1.setVisibility(View.INVISIBLE);  
  		 diz2.setVisibility(View.INVISIBLE);
  		 plaa.setVisibility(View.INVISIBLE);
  		 starteres.setVisibility(View.VISIBLE);
  		 diz3.setVisibility(View.INVISIBLE);  
 		 diz4.setVisibility(View.INVISIBLE);
 		 tjza.setVisibility(View.INVISIBLE);
  		 b = 0 ;
  		 player.stop();
  	   }
  	   
  	  });
  	  
  	  cro.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
	    public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		 croa.setVisibility(View.VISIBLE);
  	     dancea.setVisibility(View.INVISIBLE);
  		 kfja.setVisibility(View.INVISIBLE);
  		 starters.setVisibility(View.INVISIBLE);
  		 smia.setVisibility(View.INVISIBLE);
  		 diz1.setVisibility(View.INVISIBLE);  
  		 diz2.setVisibility(View.INVISIBLE);
  		 plaa.setVisibility(View.INVISIBLE);
  		 starteres.setVisibility(View.INVISIBLE);
 		 diz3.setVisibility(View.INVISIBLE);  
		 diz4.setVisibility(View.INVISIBLE);
		 tjza.setVisibility(View.INVISIBLE);
  	    startAnimationcroc();
  	    b = 0 ;
  	    player.stop();
  	   }
  	   
  	  });
  	  
  	  dance.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
	    public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		   
  		dancea.setVisibility(View.VISIBLE);
  		   croa.setVisibility(View.INVISIBLE);
  		   kfja.setVisibility(View.INVISIBLE);
  		   starters.setVisibility(View.INVISIBLE);
  		   smia.setVisibility(View.INVISIBLE);
  		   diz1.setVisibility(View.INVISIBLE);  
  			 diz2.setVisibility(View.INVISIBLE);
  			 plaa.setVisibility(View.INVISIBLE);
  			starteres.setVisibility(View.INVISIBLE);
  	 		 diz3.setVisibility(View.INVISIBLE);  
  			 diz4.setVisibility(View.INVISIBLE);
  			tjza.setVisibility(View.INVISIBLE);
  	    startAnimationdan();
  	    b = 0 ;
  	      	    
  	   }
  	   
  	  });
  	  
  	  
  	  kfj.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
	   public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		kfja.setVisibility(View.VISIBLE);
  		   croa.setVisibility(View.INVISIBLE);
  		   dancea.setVisibility(View.INVISIBLE);
  		   starters.setVisibility(View.INVISIBLE);
  		   smia.setVisibility(View.INVISIBLE);
  		   diz1.setVisibility(View.INVISIBLE);  
  			 diz2.setVisibility(View.INVISIBLE);
  			 plaa.setVisibility(View.INVISIBLE);
  			starteres.setVisibility(View.INVISIBLE);
  	 		 diz3.setVisibility(View.INVISIBLE);  
  			 diz4.setVisibility(View.INVISIBLE);
  			tjza.setVisibility(View.INVISIBLE);
  	    startAnimationkfj();
  	    b = 0 ;
  	    player.stop();
  	   }
  	   
  	  });
  	  
  	  starters.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
	   public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		smia.setVisibility(View.VISIBLE);
  		   kfja.setVisibility(View.INVISIBLE);
  		   croa.setVisibility(View.INVISIBLE);
  		   dancea.setVisibility(View.INVISIBLE);
  		   starters.setVisibility(View.INVISIBLE);
  		   diz1.setVisibility(View.INVISIBLE);  
  			 diz2.setVisibility(View.INVISIBLE);
  			 plaa.setVisibility(View.INVISIBLE);
  			starteres.setVisibility(View.INVISIBLE);
  	 		 diz3.setVisibility(View.INVISIBLE);  
  			 diz4.setVisibility(View.INVISIBLE);
  			tjza.setVisibility(View.INVISIBLE);
  	    startAnimationsmi();
  	    b = 0 ;
  	   }
  	   
  	  });
  	  
  	starteres.setOnClickListener(new OnClickListener()
	  {
	   @Override
	    public void onClick(View v) {
	    // TODO Auto-generated method stub
		   tjza.setVisibility(View.VISIBLE);
		   smia.setVisibility(View.INVISIBLE);
		   kfja.setVisibility(View.INVISIBLE);
		   croa.setVisibility(View.INVISIBLE);
		   dancea.setVisibility(View.INVISIBLE);
		   starters.setVisibility(View.INVISIBLE);
		   diz1.setVisibility(View.INVISIBLE);  
			 diz2.setVisibility(View.INVISIBLE);
			 plaa.setVisibility(View.INVISIBLE);
			starteres.setVisibility(View.INVISIBLE);
	 		 diz3.setVisibility(View.INVISIBLE);  
			 diz4.setVisibility(View.INVISIBLE);
	    startAnimationtjz();
	    b = 0 ;
	   }
	   
	  });
  	  
  	  bask.setOnClickListener(new OnClickListener()
  	  {
  	   @Override
	     public void onClick(View v) {
  	    // TODO Auto-generated method stub
  		   smia.setVisibility(View.INVISIBLE);
  		   kfja.setVisibility(View.INVISIBLE);
  		   croa.setVisibility(View.INVISIBLE);
  		   dancea.setVisibility(View.INVISIBLE);
  		   starters.setVisibility(View.INVISIBLE);
  		   diz1.setVisibility(View.INVISIBLE);  
  			 diz2.setVisibility(View.INVISIBLE);
  		  plaa.setVisibility(View.VISIBLE);
  		starteres.setVisibility(View.INVISIBLE);
		 diz3.setVisibility(View.INVISIBLE);  
		 diz4.setVisibility(View.INVISIBLE);
		 tjza.setVisibility(View.INVISIBLE);
  	    startAnimationbas();
  	    b = 1 ;
  	    player.stop();
  	   }
  	   
  	  });
  	  
  	  // ��ȡ������������  
  	  SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);  

  	  // ��ȡ���ٶȴ�����  
  	  Sensor acceleromererSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); 

  	 // ���崫�����¼�������  
  	 SensorEventListener acceleromererListener = new SensorEventListener() {  

  	 @Override 
  	 public void onAccuracyChanged(Sensor sensor, int accuracy) {  
  	 //ʲôҲ����  
  	 }  
  	 //���������ݱ䶯�¼�  
  	 @Override 
  	public void onSensorChanged(SensorEvent event) 
  	{      
  	   //��ȡ���ٶȴ���������������  
  	    float x = event.values[SensorManager.DATA_X];  
  	    float y = event.values[SensorManager.DATA_Y];  
  	    float z = event.values[SensorManager.DATA_Z];  
  	    //��ȡ��ǰʱ�̵ĺ�����  
  	    curTime = System.currentTimeMillis();  

  	    if ((curTime - lastTime) > 100 && b == 0 ) 
  	    {  
  	       duration = (curTime - lastTime);  
  	       // ���ǲ��Ǹտ�ʼ�ζ�  

  	       if (last_x == 0.0f && last_y == 0.0f && last_z == 0.0f) 
  	       {  
  	           //last_x��last_y��last_zͬʱΪ0ʱ����ʾ�ոտ�ʼ��¼  
  	           initTime = System.currentTimeMillis();  
  	       } 
  	       else 
  	       {  
  	          // ���λζ�����  
  	          shake = (Math.abs(x - last_x) + Math.abs(y - last_y) + Math.abs(z - last_z)) / duration * 100;  
  	       }
  	       totalShake += shake;  
  	 
  	       if ( totalShake > 180 && b == 0 ) 
  	       {  
  	    	 smia.setVisibility(View.INVISIBLE);
    		 kfja.setVisibility(View.INVISIBLE);
    		 croa.setVisibility(View.INVISIBLE);
    		 dancea.setVisibility(View.INVISIBLE);
    		 starters.setVisibility(View.INVISIBLE);
    		 plaa.setVisibility(View.VISIBLE);
    		 starteres.setVisibility(View.INVISIBLE);
  		   	 tjza.setVisibility(View.INVISIBLE);
  		   plaa.setVisibility(View.INVISIBLE);
  			 b = 0 ;
  			   
  	    	   if( (d1 == 0) && (d2 == 0) &&(d3 == 0) && (d4 == 0) )
  	 			{
  	 			    diz1.setVisibility(View.VISIBLE);
  	 			    d1 = 1 ;
  	 			    total++ ;
  	 			}
  	 			   
  	 		    else if( (d1 == 1) && (d2 == 0) &&(d3 == 0) && (d4 == 0) )
  	 			{
  	 			     diz1.setVisibility(View.INVISIBLE);  
  	 				 diz2.setVisibility(View.VISIBLE);
  	 				 diz3.setVisibility(View.INVISIBLE);
  	 				 diz4.setVisibility(View.INVISIBLE);
  	 				 d1 = 0 ;
  	 				 d2 = 1 ;
  	 				 d3 = 0 ;
  	 				 d4 = 0 ;
  	 				 total++ ;
  	 			}
  	 			   
  	 			else if( (d1 == 0) && (d2 == 1)&&(d3 == 0) && (d4 == 0) )
  	 			{
  	 				 diz1.setVisibility(View.INVISIBLE); 
  	 				 diz2.setVisibility(View.INVISIBLE);  
  	 				 diz3.setVisibility(View.VISIBLE); 
 	 				 diz4.setVisibility(View.INVISIBLE); 
  	 				 d1 = 0 ;
  	 				 d2 = 0 ;
  	 				 d3 = 1 ;
 	 				 d4 = 0 ;
  	 				total++ ;
  	 			}  
  	    	   
  	 			else if( (d1 == 0) && (d2 == 0)&&(d3 == 1) && (d4 == 0) )
  	 			{
  	 				 diz1.setVisibility(View.INVISIBLE); 
  	 				 diz2.setVisibility(View.INVISIBLE);  
  	 				 diz3.setVisibility(View.INVISIBLE); 
 	 				 diz4.setVisibility(View.VISIBLE); 
  	 				 d1 = 0 ;
  	 				 d2 = 0 ;
  	 				 d3 = 0 ;
 	 				 d4 = 1 ;
  	 				total++ ;
  	 			}  
  	    	   
  	 			else if( (d1 == 0) && (d2 == 0)&&(d3 == 0) && (d4 == 1) )
  	 			{
  	 				 diz1.setVisibility(View.VISIBLE); 
  	 				 diz2.setVisibility(View.INVISIBLE);  
  	 				 diz3.setVisibility(View.INVISIBLE); 
 	 				 diz4.setVisibility(View.INVISIBLE); 
  	 				 d1 = 1 ;
  	 				 d2 = 0 ;
  	 				 d3 = 0 ;
 	 				 d4 = 0 ;
  	 				total++ ;
  	 			} 
  	 			
  	    	   if( total >= 12 )
                 { 
  	    		   initShake(); 
  	    		   total = 0 ; 
  	    		   diz2.setVisibility(View.INVISIBLE); 
  	 			   diz1.setVisibility(View.INVISIBLE);
  	 			   diz3.setVisibility(View.INVISIBLE); 
	 			   diz4.setVisibility(View.INVISIBLE);
  	 			   starters.setVisibility(View.VISIBLE);
  	 			   starteres.setVisibility(View.VISIBLE);
  	 			   ptotal = 0 ;
  	    	   }
  	        }   
  	           
  	       last_x = x;  
  	       last_y = y;  
  	       last_z = z;  
  	       lastTime = curTime; 
  	        }
  	      }
  	   };
  	   
  	 //�ڴ�������������ע������� 
  	 sm.registerListener(acceleromererListener, acceleromererSensor, SensorManager.SENSOR_DELAY_NORMAL ) ; 
  	 
  	// ��ȡ������������  
       SensorManager sbask = (SensorManager) getSystemService(Context.SENSOR_SERVICE);  

       // ��ȡ���ٶȴ�����  
       Sensor pacceleromererSensor = sbask.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       
       // ���崫�����¼�������  
       SensorEventListener pacceleromererListener = new SensorEventListener() {  

       @Override 
       public void onAccuracyChanged(Sensor sensor, int accuracy) {  
       //ʲôҲ����  
       }  
       //���������ݱ䶯�¼�  
       @Override 
       public void onSensorChanged(SensorEvent event) 
       {      
           //��ȡ���ٶȴ���������������  
            float px = event.values[SensorManager.DATA_X];  
            float py = event.values[SensorManager.DATA_Y];  
            float pz = event.values[SensorManager.DATA_Z];  
            //��ȡ��ǰʱ�̵ĺ�����  
            pcurTime = System.currentTimeMillis(); 
            
            
           if( b == 1 )
           { 
        	   if ((pcurTime - plastTime) > 100) 
              {  
               pduration = (pcurTime - plastTime);  
               // ���ǲ��Ǹտ�ʼ�ζ�  
               
               if (plast_x == 0.0f && plast_y == 0.0f && plast_z == 0.0f) 
               {  
                   //last_x��last_y��last_zͬʱΪ0ʱ����ʾ�ոտ�ʼ��¼  
                   pinitTime = System.currentTimeMillis();  
               } 
               else 
               {  
                  // ���λζ�����  
                  pshake = (Math.abs(px - plast_x) + Math.abs(py - plast_y) + Math.abs(pz - plast_z)) / pduration * 100;  
               }
               ptotalShake += pshake;  
    
               if ( ptotalShake > 30 && b == 1 ) 
               {   
            	   plaa.setVisibility(View.INVISIBLE);
            	   
            	   if( ( i == 0 ) && ( j == 0) )
             	  {
             		  pbasketball0.setVisibility(View.VISIBLE);
             		  ptotal++ ;
             		  i = 1 ;
             	  }
             	  
             	  else if(( i == 1 ) && ( j == 0) )
             	  {
             		  pbasketball2.setVisibility(View.VISIBLE);
             		  pbasketball0.setVisibility(View.INVISIBLE);
             		  ptotal++ ;
             		  i = 0 ;
             		  j = 1 ;
             	  } 
             	  
             	  else if(( i == 0 ) && ( j == 1) )
             	  {
             		  pbasketball0.setVisibility(View.VISIBLE);
             		  pbasketball2.setVisibility(View.INVISIBLE);
             		  ptotal++ ;
             		  i = 1 ;
             		  j = 0 ;
             	  } 
             	    	  
               }   
               
               if( ptotal >= 6 )
               {
             	   initpshake(); 
  	    		   ptotal = 0 ; 
  	    		   pbasketball0.setVisibility(View.INVISIBLE);
          		   pbasketball2.setVisibility(View.INVISIBLE);
             	   plaa.setVisibility(View.VISIBLE);
             	   total = 0 ;
               }
               
             	   plast_x = px;  
                 plast_y = py;  
                 plast_z = pz;  
                 plastTime = pcurTime; 

            }
          }
        }
      };
      
    //�ڴ�������������ע������� 
    sbask.registerListener(pacceleromererListener, pacceleromererSensor, SensorManager.SENSOR_DELAY_NORMAL ) ;
  	//�������ֽ���
      
    
    
       //��ȡϵͳ����
    //ϵͳ����    
    volum=mAM.getStreamVolume(3);
    //��������///����ͼƬ
   String PREFS_NAME = "game.set";
    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    boolean getisSound = settings.getBoolean("isSound", true);
    isSound=(!getisSound);
    bpicno = settings.getInt("backpic", 0);
   
    setSound();
    mainlayout.setBackgroundResource(backpic[bpicno]);    
    
        //��ȡ��ɫ��Ϣ
        if(getRoleInfo(mContext)==null)//���ݿ����޼�¼,������ɫ
        {
        	belowpoint.setVisibility(View.INVISIBLE);
        	 DialogUtils.dialogBuilder(GameActivity.this, "��ʾ","��ӭ������Ϸ������û�д�����ɫ���Ƿ񴴽���", new DialogCallBack(){
 				//��Intent�������ͼƬActivity
             	
 				@Override
				public void callBack() {
 					Intent intent =new Intent(GameActivity.this,RoleCreationActivity.class);
             		GameActivity.this.startActivity(intent);   
             	
 				}
             	@Override
				public void callAll(){
             		             	
             	}
             	@Override
				public void callCancel(){}
     		});
        	
        	
//        	roleInfo.setRoleName("Lucy");
//            roleInfo.setRoleLevel(0);
//            roleInfo.setRolePlayTime(0);
//           //���ý�ɫ��Ϣ
//        	
//        	RoleInfoHelper roleInfoHelper=new RoleInfoHelper(mContext);
//        	roleInfoHelper.open();
//        	roleInfoHelper.create(roleInfo);//����һ����¼  	
//        	roleInfoHelper.close();
        }
        else
        	{
        	belowpoint.setVisibility(View.VISIBLE);        	
        	roleInfo=getRoleInfo(mContext);  
        	Bitmap bitmap=BitmapFactory.decodeFile(roleInfo.getRoleHeadAddr());        	                                       
            rolehead.setImageBitmap(bitmap); 
            
            if(roleInfo.getRoleBodyId()==0)
            {rolebody1.setImageResource(R.drawable.start_1);
             rolebody2.setImageResource(R.drawable.start_2);
            }
            if(roleInfo.getRoleBodyId()==1)
            {
            rolebody1.setImageResource(R.drawable.start_3);
            rolebody2.setImageResource(R.drawable.start_4);
           }
            name.setText(roleInfo.getRoleName());
            level.setText(slevel[roleInfo.getRoleLevel()]);
            playtime.setText(Integer.toString(roleInfo.getRolePlayTime()));
           //��ʾ��ɫ��Ϣ
            roleStart();//��ɫ�ɳ�
                	}
        
       //���ű�������
        
        
        try {
        	player = MediaPlayer.create(GameActivity.this, R.raw.start);  
        	//player.setLooping(true);  ѭ������
        	if(player != null)
        	{
        		player.stop();            //ֹͣ
        	}
            player.prepare();
            player.start();
        }
        catch (Exception e) {
             //TODO Auto-generated catch block
            e.printStackTrace();
        }
        //���ű�������
        
        
        
        
	}
        
	
	
	//�ؼ������ֵ�ClickListener
	
	//������ť��ClickListener
	class CameraListener implements OnClickListener{
        	@Override
			public void onClick(View v){
        		//Toast.makeText(GameActivity.this, "1", Toast.LENGTH_SHORT).show();
        		//���ذ�ť��
        		toplayout.setVisibility(View.INVISIBLE);
        		belowlayout.setVisibility(View.INVISIBLE);
        		//Toast.makeText(GameActivity.this, "2", Toast.LENGTH_SHORT).show();
        		//�õ���ǰview����view�ṹ�еĸ�view
        		View vv = v.getRootView();
        		//��������
        		//Toast.makeText(GameActivity.this, "3", Toast.LENGTH_SHORT).show();
				vv.setDrawingCacheEnabled(true);
				//ȡ��λͼ
				Bitmap bm = vv.getDrawingCache();
				//��imageView��ʾ�ղŽص�ͼ
				imageView.setImageBitmap(bm);
				GameActivity.this.setContentView(imageView);
				
				
				imageAdr=android.os.Environment.getExternalStorageDirectory()+File.separator+getFileName()+"image.png";
				File file = new File(imageAdr);
					try{
					file.createNewFile();}
					catch(IOException e)
					{
						Log.e("zero",e.toString());
                        e.printStackTrace(); 
					}
					if(file.exists())
					{Toast.makeText(GameActivity.this, "�ļ�����", Toast.LENGTH_SHORT).show(); }
				FileOutputStream out;
				try{
                    out = new FileOutputStream(file);
                    Toast.makeText(GameActivity.this, "����", Toast.LENGTH_SHORT).show(); 
                    if(bm.compress(Bitmap.CompressFormat.PNG, 70, out)) 
                    {
                    	Toast.makeText(GameActivity.this, "�ɹ�", Toast.LENGTH_SHORT).show(); 
                            out.flush();
                            out.close();
                    }
            } 
            catch (FileNotFoundException e) 
            {
            	Log.e("one",e.toString());
                    e.printStackTrace();
            } 
            catch (IOException e) 
            {
            	Log.e("two",e.toString());
                    e.printStackTrace(); 
            }
            
            
            DialogUtils.dialogBuilder(GameActivity.this, "��ʾ","��ͼƬ�ϴ���΢����", new DialogCallBack(){
				//��Intent�������ͼƬActivity
            	
				@Override
				public void callBack() {
					String PREFS_NAME = "game.share";
					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("fileAddr", imageAdr);
					editor.commit();


		                

					
            		Intent intent =new Intent(GameActivity.this,AndroidExample.class);
            		GameActivity.this.startActivity(intent);
            		
            		
				}
            	@Override
				public void callAll(){
            		
            		//����Ļ��ʾ��������
                	GameActivity.this.setContentView(mainlayout);
                	toplayout.setVisibility(View.VISIBLE);
                	belowlayout.setVisibility(View.VISIBLE);
            		
            	}
            	@Override
				public void callCancel(){}
    		});
            
    
            
        	}    	
        }
	
	
	
	//�ر�������ClickListener
	class SoundListener implements OnClickListener{
    	@Override
		public void onClick(View v){
    		setSound();
         
    	}
	}
	
	
	
	//������ť��ClickListener
	class HelpListener implements OnClickListener{
        	@Override
			public void onClick(View v){
        		Intent intent =new Intent(GameActivity.this,HelperInfo.class);
        		GameActivity.this.startActivity(intent);      		
        		}
        	}
	
	
	//�л�����ͼƬ��ClickListener
	class CbackListener implements OnClickListener{
        	@Override
			public void onClick(View v){
        		bpicno=(bpicno+1)%BPICAMT;
    			mainlayout.setBackgroundResource(backpic[bpicno]);
        		
        		}
        	}
	
	
	//������ɫ��ClickListener
	class CreateListener implements OnClickListener{
    	@Override
		public void onClick(View v){
    		if(getRoleInfo(mContext)!=null)
    		{
    			DialogUtils.dialogBuilder(GameActivity.this, "��ʾ","�Ƿ���ע�����н�ɫ���´�����ɫ��", new DialogCallBack(){
				
            	
				@Override
				public void callBack() {
					handler.removeCallbacks(updateThread);//������ɫ�ɳ����߳�
					RoleInfoHelper roleInfoHelper=new RoleInfoHelper(getApplicationContext());
					roleInfoHelper.open();
					roleInfoHelper.delete();
					roleInfoHelper.close();
					Intent intent =new Intent(GameActivity.this,RoleCreationActivity.class);
		    		GameActivity.this.startActivity(intent);
		    	
				}
            	@Override
				public void callAll(){}
            	@Override
				public void callCancel(){}
    		});
    			
    		}
    		else{
    			Intent intent =new Intent(GameActivity.this,RoleCreationActivity.class);
	    		GameActivity.this.startActivity(intent);
    		}
          
    		}
    	}
	
	
	
		
	
	
	//�˵����ص�ClickListener
	class PointListener implements OnClickListener{
		@Override
		public void onClick(View v){
    		if(mtophide==true){
    			point.setBackgroundResource(R.drawable.view);
    			topbuttonlayout.setVisibility(View.VISIBLE);
    			mtophide=false;
    		}
    		else{
    			point.setBackgroundResource(R.drawable.hide);
    			topbuttonlayout.setVisibility(View.INVISIBLE);
    			mtophide=true;
    		}	
		}		
	}
	
	class BelowPointListener implements OnClickListener{
		@Override
		public void onClick(View v){
    		if(mbelowhide==true){
    			belowpoint.setBackgroundResource(R.drawable.belowview);
    			belowbuttonlayout.setVisibility(View.VISIBLE);
    			mbelowhide=false;
    		}
    		else{
    			belowpoint.setBackgroundResource(R.drawable.belowhide);
    			belowbuttonlayout.setVisibility(View.INVISIBLE);
    			mbelowhide=true;
    		}	
		}		
	}
	
	
	//��Ϸ����ʱ
	public void roleStart(){	
		
		//;//������Ϸ��ʼ��ϵͳ�����������Ĵ�С
		handler.post(updateThread);//��ʼ��ɫ�ɳ����߳�
	}
	
	
	//ʹ�õ�ǰʱ���ƴ��һ��Ψһ���ļ���
	public static String getFileName() 
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
    	String fileName = format.format( new Timestamp( System.currentTimeMillis()) );
    	return fileName;
    }
	
	
	
	    //���ƽ�ɫ�ɳ����Ƶ��߳�
		Handler handler=new Handler();
		Runnable updateThread=new Runnable(){
			
			
			@Override
			public void run(){
				RoleInfoHelper roleDBHelper = new RoleInfoHelper(mContext);
				roleDBHelper.open();
				roleDBHelper.grow(roleInfo.getRoleName(),GameActivity.this);
				roleInfo=getRoleInfo(mContext);		         
				roleDBHelper.close();
				name.setText(roleInfo.getRoleName());
		        level.setText(slevel[roleInfo.getRoleLevel()]);
		        playtime.setText(Integer.toString(roleInfo.getRolePlayTime()));
		        //��ʾ��ɫ��Ϣ
				handler.postDelayed(updateThread, 6000);
			}
			
		};
	//���ƽ�ɫ�ɳ����Ƶ��߳�
		
		
		public void setSound()
		{
			if(isSound)
    		{
    			
    			sound.setBackgroundResource(R.drawable.soundoff);
    			if(mAM.getStreamVolume(3)!=0)   			 
    			mAM.setStreamVolume(3, 0, 0);   
                isSound=false;
                Toast.makeText(GameActivity.this, "�����ѹر�", Toast.LENGTH_SHORT).show();
    		}
    		else
    		{
    			Toast.makeText(GameActivity.this, "�����ѿ���", Toast.LENGTH_SHORT).show(); 
    			sound.setBackgroundResource(R.drawable.sound);
    			mAM.setStreamVolume(3, volum, 0);		 
    			 isSound=true;
    		}
		}
		
		 /**
		 * ֻҪ�������ݿ��������ݣ��ͱ�ʾ��¼��
		 * �������ݷ���null�����򷵻����ݿ��һ����¼

		 */
		public static RoleInfo getRoleInfo(Context mContext)
		{
			ArrayList<RoleInfo> list = null;
			RoleInfoHelper roleDBHelper = new RoleInfoHelper(mContext);
			roleDBHelper.open();
			
			try
			{
				 list=roleDBHelper.getRoleInfos();
			}
			finally
			{
				roleDBHelper.close();
			}
  			return (list!=null&&list.size()!=0)?list.get(0):null;
		}
	
		
		 //����������
		 private void startAnimationcroc()
		 {
		  if(drawcro.isRunning())
		  {
			  drawcro.stop();
		  }
		  else
		  {
		     drawcro.start();
		  }
		 }
		 
		 private void startAnimationdan()
		 {
		  if(drawdan.isRunning())
		  {
			drawdan.stop();
			player.stop();
		  }
		  else
		  {
		   drawdan.start();
		   //������������               
	        try {
	        	player = MediaPlayer.create(GameActivity.this, R.raw.dance);  
	        	player.setLooping(true);  //ѭ������
	        	if(player != null)
	        	{
	        		player.stop();            //ֹͣ
	        	}
	            player.prepare();
	            player.start();
	        }
	        catch (Exception e) {
	             //TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //������������
		  }
		 }
		 
		 
		 private void startAnimationkfj()
		 {
		  if(drawkfj.isRunning())
		  {
			drawkfj.stop();
		  }
		  else
		  {
		   drawkfj.start();
		  }
		 }
		 
		 private void startAnimationsmi()
		 {
		  if(!drawsmi.isRunning())
		   drawsmi.start();
		 }
		 
		 public void startAnimationbas()
		{
		   if(drawpla.isRunning())
		   {
		  	  drawpla.stop();
		   }
		   else
		   {
		  	 drawpla.start();
		   }
		  }
		 
		 public void startAnimationtjz()
			{
			   if(drawtjz.isRunning())
			   {
			  	  drawtjz.stop();
			   }
			   else
			   {
			  	 drawtjz.start();
			   }
			  }
		 
		 public void initShake() 
		 {  
	        duration = 0;  
	        curTime = 0;  
	        initTime = 0;    
	        shake = 0.0f;  
	        totalShake = 0.0f;  
	      }  
		 
		 public void initpshake() 
		 {  
	       pduration = 0;  
	       pcurTime = 0;  
	       pinitTime = 0;    
	       pshake = 0.0f;  
	       ptotalShake = 0.0f;  
	     } 
		 //�������ֽ���
		 
		 
		 @Override
		public boolean onCreateOptionsMenu(Menu menu) 
			{
				menu.add(0, TOOLBAR0, 1, "�˳���Ϸ" ).setIcon( android.R.drawable.ic_menu_revert );
				menu.add(0, TOOLBAR1, 2, "ע����¼" ).setIcon( android.R.drawable.ic_menu_delete );
				return super.onCreateOptionsMenu(menu);
			}

		    @Override
			public boolean onOptionsItemSelected(MenuItem item) 
			{
		    	if( item.getItemId() == 0 )
		    	{
		    		handler.removeCallbacks(updateThread);//������ɫ�ɳ����߳�
		    		
		    		//����ϵͳ��Ϣ
					String PREFS_NAME = "game.set";
					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putBoolean("isSound", isSound);
					editor.putInt("backpic", bpicno);
					editor.commit();
					mAM.setStreamVolume(3, volum, 0);
					
		    		
		    		//�˳�����
		    		android.os.Process.killProcess(android.os.Process.myPid());
		    	}
		    	else
		    	{	
		    		DialogUtils.dialogBuilder(GameActivity.this, "��ʾ","ȷ��Ҫע����¼���˳���", new DialogCallBack(){
						
						@Override
						public void callBack() {
							handler.removeCallbacks(updateThread);//������ɫ�ɳ����߳�
							RoleInfoHelper roleInfoHelper=new RoleInfoHelper(getApplicationContext());
							roleInfoHelper.open();
							roleInfoHelper.delete();
							roleInfoHelper.close();
							
							//ɾ��ϵͳ��¼
							String PREFS_NAME = "game.set";
							SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
							SharedPreferences.Editor editor = settings.edit();
							editor.remove("isSound");
							editor.remove("backpic");
							editor.commit();
							mAM.setStreamVolume(3, volum, 0);
				    		//�˳�����
				    		android.os.Process.killProcess(android.os.Process.myPid());
						}
						@Override
						public void callCancel(){}
						@Override
						public void callAll(){}
		    		});
		    	}
		    	return super.onOptionsItemSelected(item);
			}
			
		    public boolean onKeyDown(int keyCode, KeyEvent event) {    
		    	   
		        // ���¼����Ϸ��ذ�ť    
		        if (keyCode == KeyEvent.KEYCODE_BACK) {    
		        	handler.removeCallbacks(updateThread);//������ɫ�ɳ����߳�
		    		
		    		//����ϵͳ��Ϣ
					String PREFS_NAME = "game.set";
					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putBoolean("isSound", isSound);
					editor.putInt("backpic", bpicno);
					editor.commit();
					mAM.setStreamVolume(3, volum, 0);
					//�˳�����
		    		android.os.Process.killProcess(android.os.Process.myPid());
		    
		            return true;    
		        } else {    
		            return super.onKeyDown(keyCode, event);    
		        }    
		    }    
}