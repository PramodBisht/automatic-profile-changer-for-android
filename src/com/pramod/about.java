package com.pramod;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Button feedbackbtn=(Button)findViewById(R.id.feedbackbt);
		feedbackbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri ur=Uri.parse("http://automaticprofilechanger.wordpress.com/feedback");
				startActivity( new Intent( Intent.ACTION_VIEW, ur ) );
				
			}
		});
		Button bugreport_btn=(Button)findViewById(R.id.bugreport);
		
		bugreport_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://automaticprofilechanger.wordpress.com/bugs-report")));
				
			}
		});
		
		Button website=(Button)findViewById(R.id.ourwebsitebtn);
		website.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://automaticprofilechanger.wordpress.com/")));
				
			}
		});
	
		
	}
	
        
        

}

