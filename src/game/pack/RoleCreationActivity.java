package game.pack;



import game.pack.common.FileUtils;
import game.pack.common.InfoHelper;
import game.pack.common.MediaUtils;
import game.pack.common.StringUtils;
import game.pack.db.RoleInfo;

import game.pack.db.RoleInfoHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RoleCreationActivity extends Activity {
    /** Called when the activity is first created. */
	


	private Integer[] imageIDs=
	{
			R.drawable.startfirst,
			R.drawable.startsecond
	};

	private static final int PHOTO_PICKED_WITH_DATA  = 1234567;  
	private static final int REQUEST_CODE_GETIMAGE_BYSDCARD=0;
	private EditText nameText ;
	private  ImageView headImage ;
	private  ImageView bodyImage ;
	private Button buttonOK ;
	private Button buttonCancel;	
	private static String name ="";
	private int bodyImageID = 0;
	//private String fileName = "";
	private static String photoPath = null;
	//private File photoDir   = new File(Environment.getExternalStorageDirectory() + "//DCIM//Camera");  
	//private File mCurrentPhotoFile ;
	//private Uri photoUri ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
             
        //��idȡ�ÿؼ�
        headImage = (ImageView)findViewById(R.id.headImage);
        bodyImage =(ImageView)findViewById(R.id.bodyImage);
        nameText = (EditText)findViewById(R.id.nameEditText);
        buttonOK = (Button)findViewById(R.id.buttonOK);
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        
        
        //Ϊ�����ؼ�������Դ
        
        if(photoPath==null)
        {
        	headImage.setBackgroundResource(R.drawable.head);
        }
        else
        {
        	Bitmap bitmap=BitmapFactory.decodeFile(photoPath);
        	BitmapDrawable bmd = new BitmapDrawable(bitmap);           	   
            Drawable draw=bmd;                      
        	headImage.setBackgroundDrawable(draw);
        }
        Intent body = getIntent();
        bodyImageID = body.getIntExtra("bodyImage",-1);                
        if(bodyImageID != -1)
        {
        	bodyImage.setImageResource(imageIDs[bodyImageID]);
        }
        else bodyImage.setImageResource(R.drawable.body); 
        
      

        
      //ͷ�񴴽�������
        headImage.setOnClickListener(new ImageView.OnClickListener()
        {
			@Override
			public void onClick(View arg0) 
			{
				CharSequence[] items = {"�ֻ����", "�ֻ�����", "ȡ���ϴ�"};
				imageChooseItem(items);
				//����ͷ�񴴽�Activity
				//Intent intent = new Intent();
				//intent.setClass(RoleCreationActivity.this,headUploadActivity.class);
				//RoleCreationActivity.this.startActivity(intent);
				
				//����ϵͳ���������1
				//Intent intent = new Intent();
				//intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
				//startActivity(intent);
				
				//photoDir.mkdirs();    // ������Ƭ�Ĵ洢Ŀ¼ 
				//mCurrentPhotoFile = new File(photoDir, getPhotoFileName());   // �����յ���Ƭ�ļ�����
				
//				final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE,null);
//				//cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(mCurrentPhotoFile));	
//	            startActivityForResult(cameraIntent, PHOTO_PICKED_WITH_DATA );  
	            
	           //create parameters for Intent with filename
	           //ContentValues values = new ContentValues();
	          // values.put(MediaStore.Images.Media.TITLE, fileName);
	           //values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
	               
	            //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
	             //Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	           //Uri imageUri = Uri.fromFile(mCurrentPhotoFile);
	            //final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	            //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
	            //startActivityForResult(intent, PHOTO_PICKED_WITH_DATA );
			}
        });
                    
        //����ѡ�������
        bodyImage.setOnClickListener(new ImageView.OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				// ��������ѡ��Activity
				Intent intent = new Intent();
				intent.setClass(RoleCreationActivity.this, bodySelectionActivity.class);
				SaveData();
				startActivity(intent);
				finish();
			}        	
        });
        
       
       buttonOK.setOnClickListener(new Button.OnClickListener()
       {
		@Override
		public void onClick(View v)
		{
			//���ݴ洢����
			//ȡ�����ݿ����
			//roleDataBaseAdapter = new MyDataBaseAdapter(RoleCreationActivity.this);
			//roleDataBaseAdapter.open();

			//�����ݿ����������		        
			//roleDataBaseAdapter.insertData(nameText.getText().toString(), genderText, interestText.getText().toString(),
			//										"Level One", 0, "", bodyImageID);
			
				RoleInfo roleInfo=new RoleInfo() ;
	        	roleInfo.setRoleName(nameText.getText().toString());	        	
	        	roleInfo.setRolePlayTime(0);
	        	roleInfo.setRoleLevel(0);
	        	roleInfo.setRoleHeadAddr(photoPath);
	        	roleInfo.setRoleBodyId(bodyImageID);
	        	//��role��7��������ֵ1-7
	        	
	        	RoleInfoHelper roleInfoHelper=new RoleInfoHelper(RoleCreationActivity.this);
	        	roleInfoHelper.open();
	        	//����һ����¼  
	        	roleInfoHelper.create(roleInfo);	
	        	roleInfoHelper.close();
	        	
	        	//������Ϸ����
	        	Intent intent = new Intent();
				intent.setClass(RoleCreationActivity.this, GameActivity.class);
				startActivity(intent);
				finish();
	        	
		}
       });
       
       buttonCancel.setOnClickListener(new Button.OnClickListener()
       {
		@Override
		public void onClick(View arg0)
		{
			finish();
		}  	   
       });
    }
	
    protected String getPhotoFileName()
    {

    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
    	String fileName = format.format( new Timestamp( System.currentTimeMillis()) );
    	return fileName;
    	 // Date date = new Date(System.currentTimeMillis());  
    	 // SimpleDateFormat dateFormat = new SimpleDateFormat( "'IMG'_yyyy-MM-dd HH:mm:ss");  
    	 // return dateFormat.format(date) + ".jpg";  
	}

	@Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{ 
		
		if(requestCode == PHOTO_PICKED_WITH_DATA  && resultCode == RESULT_OK)
		{
				Bundle extras = data.getExtras(); 
				Bitmap facebmp = (Bitmap)extras.get("data");
				if(facebmp != null)
				{
					BitmapDrawable headbmd = new BitmapDrawable(facebmp);           	   
		            Drawable headdraw=headbmd;
					headImage.setBackgroundDrawable(headdraw);
					photoPath =android.os.Environment.getExternalStorageDirectory()+File.separator+getPhotoFileName()+"image.png";
					File file = new File(photoPath);
						try{
						file.createNewFile();}
						catch(IOException e)
						{
							Log.e("zero",e.toString());
	                        e.printStackTrace(); 
						}
						if(file.exists())
						{Toast.makeText(RoleCreationActivity.this, "ͼƬ����", Toast.LENGTH_SHORT).show(); }
					FileOutputStream out;
					try{
	                    out = new FileOutputStream(file);
	                    //Toast.makeText(RoleCreationActivity.this, "����", Toast.LENGTH_SHORT).show(); 
	                    if(facebmp.compress(Bitmap.CompressFormat.PNG, 70, out)) 
	                    {
	                    	Toast.makeText(RoleCreationActivity.this, "����ɹ�", Toast.LENGTH_SHORT).show(); 
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
			}
			//nameText.setText(photoPath);
			}
		else
			if ( requestCode == REQUEST_CODE_GETIMAGE_BYSDCARD ) 
	        { 
	        	if (resultCode != RESULT_OK) 
	    		{   
	    	        return;   
	    	    }
	        	
	        	if(data == null)    return;
	        	
	        	Uri thisUri = data.getData();
	        	String thePath = InfoHelper.getAbsolutePathFromNoStandardUri(thisUri);
	        	
	        	//����Ǳ�׼Uri
	        	if( StringUtils.isBlank(thePath) )
	        	{
	        		photoPath = getAbsoluteImagePath(thisUri);
	        	}
	        	else
	        	{
	        		photoPath=thePath;
	        	}
	        	
	        	String attFormat = FileUtils.getFileFormat(photoPath);
	        	if( !"photo".equals(MediaUtils.getContentType(attFormat)) )
	        	{
	        		Toast.makeText(getApplicationContext(), "��ѡ��ͼƬ�ļ���", Toast.LENGTH_SHORT).show();
	        		return;
	        	}
	        	Bitmap bitmap=BitmapFactory.decodeFile(photoPath);
	        	BitmapDrawable bmd = new BitmapDrawable(bitmap);           	   
	            Drawable draw=bmd;                      
	        	headImage.setBackgroundDrawable(draw);
	    		//nameText.setText(photoPath);
	        }
		super.onActivityResult(requestCode, resultCode, data); 
    } 
    private String getAbsoluteImagePath(Uri uri)
    {
    	String imagePath = "";
        String [] proj={MediaColumns.DATA};
        Cursor cursor = managedQuery( uri,
                        proj, 		// Which columns to return
                        null,       // WHERE clause; which rows to return (all rows)
                        null,       // WHERE clause selection arguments (none)
                        null); 		// Order-by clause (ascending by name)
        
        if(cursor!=null)
        {
        	int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        	if(  cursor.getCount()>0 && cursor.moveToFirst() )
            {
            	imagePath = cursor.getString(column_index);
            }
        }
        
        return imagePath;
	}

	private boolean Compare(int[] rCurrent, int[] gCurrent, int[] bCurrent,
			int[] rNext, int[] gNext, int[] bNext, int k) 
    {
		//�Ƚ��ݶȲ�
    	if(rCurrent[k] == rNext[k] && gCurrent[k]==gNext[k] && bCurrent[k]== bNext[k])
    	{
    		return false;
    	}
    	return true;
		
	}
	public void imageChooseItem(CharSequence[] items )
	{
		AlertDialog imageDialog = new AlertDialog.Builder(RoleCreationActivity.this).setTitle("����ͼƬ").setItems(items,
			new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int item)
				{
					//�ֻ�ѡͼ
					if( item == 0 )
					{
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
						intent.setType("image/*"); 
						startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYSDCARD); 
					}
					//����
					else if( item == 1 )
					{	  
						final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE,null);
//						//cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(mCurrentPhotoFile));	
			            startActivityForResult(cameraIntent, PHOTO_PICKED_WITH_DATA );  
					}   
					else if( item == 2 )
					{
						
						
					}
				}}).create();
		
		 imageDialog.show();
	}

	private void SaveData()
	{
		// ��������Activityʱ�����Ѿ����������
		name = nameText.getText().toString();
		
	}
	// InputStream fileInputStream = new InputStream();
    //BitmapFactory.de
    /*
    InputStream fileInputStream = new InputStream() {		
		@Override
		public int read() throws IOException 
		{
			return 0;
		}
	}
	facebmp = BitmapFactory.decodeStream(fileInputStream);;		    
    int[] rCurrent =new int[1000];
    int[] gCurrent =new int[1000];
    int[] bCurrent =new int[1000];
    int[] rNext = new int[1000];
    int[] gNext = new int[1000];
    int[] bNext = new int[1000];
    int k = 0,m = 0,pixelCurrent=0,pixelNext = 0;
    int[] face = new int[2000];
    
    System.out.print(facebmp.getWidth());
    System.out.print(facebmp.getHeight());
    for(int i = 0; i<facebmp.getWidth(); i++)
    {
    	for(int j=0; j<facebmp.getHeight()-1; j++)
    	{
    		 pixelCurrent = facebmp.getPixel(i, j);
    		 rCurrent[k] =  Color.red(pixelCurrent); 
    		 gCurrent[k] = Color.green(pixelCurrent); 
    		 bCurrent[k] = Color.blue(pixelCurrent);
    		 
    		 pixelNext = facebmp.getPixel(i, j+1);
    		 rNext[k] = Color.red(pixelNext);
    		 gNext[k]= Color.green(pixelNext);
    		 bNext[k]= Color.blue(pixelNext);
    		 
    		 if(Compare(rCurrent,gCurrent,bCurrent,rNext,gNext,bNext,k) == true)
    		 {
    			 face[m++] = pixelNext;
    			 System.out.print(face[m]);
    		 }
    		 k++;
    	}
    }*/
    //Bitmap faced = new Bitmap()

}