package game.pack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class bodySelectionActivity extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodyselection);
        
        Gallery g = (Gallery)findViewById(R.id.bodyGallery);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemClickListener(new OnItemClickListener()
        {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			{
				Intent intent = new Intent();
				intent.setClass(bodySelectionActivity.this,RoleCreationActivity.class);
				intent.putExtra("bodyImage",arg2);
				bodySelectionActivity.this.finish();
				startActivity(intent);
			}
        	
        });
	}
}
