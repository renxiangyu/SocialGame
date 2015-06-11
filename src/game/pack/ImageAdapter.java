package game.pack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{

	private Context context;
	private Integer[] imageIDs=
	{
			R.drawable.startfirst,
			R.drawable.startsecond
			
	};
	
	public ImageAdapter(Context c)
	{
		context = c;
	}
	public int getCount() 
	{
		return imageIDs.length;
	}

	public Object getItem(int position)
	{
		return position;
	}

	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageview = new ImageView(context);
		imageview.setImageResource(imageIDs[position]);
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}

}
