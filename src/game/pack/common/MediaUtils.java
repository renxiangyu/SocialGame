package game.pack.common;

import java.util.HashMap;
import java.util.Map;

/** 
 * ��˵����   ý�����͹���
 * @author  @Cundong
 * @weibo   http://weibo.com/liucundong
 * @blog    http://www.liucundong.com
 * @date    Apr 29, 2011 2:50:48 PM
 * @version 1.0
 */
public class MediaUtils
{
	private static Map<String, String> FORMAT_TO_CONTENTTYPE = new HashMap<String, String>();
	
	static
	{
		//��Ƶ
		FORMAT_TO_CONTENTTYPE.put( "mp3", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "mid", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "midi", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "asf", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "wm", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "wma", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "wmd", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "amr", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "wav", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "3gpp", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "mod", "audio" );
		FORMAT_TO_CONTENTTYPE.put( "mpc", "audio" );
		
		//��Ƶ
		FORMAT_TO_CONTENTTYPE.put( "fla", "video" );
		FORMAT_TO_CONTENTTYPE.put( "flv", "video" );
		FORMAT_TO_CONTENTTYPE.put( "wav", "video" );
		FORMAT_TO_CONTENTTYPE.put( "wmv", "video" );
		FORMAT_TO_CONTENTTYPE.put( "avi", "video" );
		FORMAT_TO_CONTENTTYPE.put( "rm", "video" );
		FORMAT_TO_CONTENTTYPE.put( "rmvb", "video" );
		FORMAT_TO_CONTENTTYPE.put( "3gp", "video" );
		FORMAT_TO_CONTENTTYPE.put( "mp4", "video" );
		FORMAT_TO_CONTENTTYPE.put( "mov", "video" );
		
		//flash
		FORMAT_TO_CONTENTTYPE.put( "swf", "video" );
		
		FORMAT_TO_CONTENTTYPE.put( "null", "video" );
		
		//ͼƬ
		FORMAT_TO_CONTENTTYPE.put( "jpg", "photo" );
		FORMAT_TO_CONTENTTYPE.put( "jpeg", "photo" );
		FORMAT_TO_CONTENTTYPE.put( "png", "photo" );
		FORMAT_TO_CONTENTTYPE.put( "bmp", "photo" );
		FORMAT_TO_CONTENTTYPE.put( "gif", "photo" );
	}
	
	/**
	 * ���ݸ�����չ����ȡ����
	 * @param attFormat
	 * @return
	 */
	public static String getContentType( String attFormat )
	{
		String contentType = FORMAT_TO_CONTENTTYPE.get("null");
		
		if ( attFormat != null ) 
		{
			contentType = (String)FORMAT_TO_CONTENTTYPE.get( attFormat.toLowerCase() );
		}
		return contentType;
	}
}