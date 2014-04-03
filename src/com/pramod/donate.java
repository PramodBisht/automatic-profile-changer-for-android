package com.pramod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class donate extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.donatescreen);
		Button bt=(Button)findViewById(R.id.donatescreenbutton1);
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	}
	

}
