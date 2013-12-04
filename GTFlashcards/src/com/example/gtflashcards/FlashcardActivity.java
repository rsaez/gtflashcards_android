package com.example.gtflashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtflashcards.objects.GTFlashcards;

public class FlashcardActivity extends Activity implements OnClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
    GTFlashcards flashcard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashcard);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        // Do this for each view added to the grid
        findViewById(android.R.id.content).setOnClickListener(FlashcardActivity.this); 
        findViewById(android.R.id.content).setOnTouchListener(gestureListener);
        
        flashcard = MainActivity.getCurrentFlashcard();
        
        TextView t=(TextView)findViewById(R.id.question); 
        t.setText(flashcard.getQuestion());
        
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flashcard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View v) {
		//do nothing?
	}
	
	public void goToNextFlashcard() {
		if (MainActivity.currentFlashcardIndex == MainActivity.getCurrentDeck().getFlashcards().size()-1) {
			MainActivity.currentFlashcardIndex = 0;
		} else {
			MainActivity.currentFlashcardIndex++;
		}
		Intent intent = new Intent(this, FlashcardActivity.class);
        startActivityForResult(intent, 500);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	
	public void goToPreviousFlashcard() {
		if (MainActivity.currentFlashcardIndex == 0) {
			MainActivity.currentFlashcardIndex = MainActivity.getCurrentDeck().getFlashcards().size()-1;
		} else {
			MainActivity.currentFlashcardIndex--;
		}
		Intent intent = new Intent(this, FlashcardActivity.class);
        startActivityForResult(intent, 500);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    goToNextFlashcard();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    goToPreviousFlashcard();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
        
        @Override
        public boolean onDown(MotionEvent e) {
        	return true;
        }

    }


}
