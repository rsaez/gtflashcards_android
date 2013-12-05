package com.example.gtflashcards;

import java.util.ArrayList;
import java.util.Collections;

import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.GTFlashcards;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class FlashcardActivity extends Activity implements OnClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
    GTFlashcards flashcard;
    
private boolean mShowingBack = false;
    
	/**
     * A fragment representing the front of the card.
     */
	@SuppressLint("ValidFragment")
	public class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            
        	View view = inflater.inflate(R.layout.fragment_flashcard_front, container, false);

        	TextView questionTextView = (TextView)view.findViewById(R.id.question); 
        	questionTextView.setText(flashcard.getQuestion());
          
            TextView tagsTextView = (TextView)view.findViewById(R.id.tags); 
            tagsTextView.setText("Tags: "+flashcard.getTagsString());
            
            return view;
        }
    }

    /**
     * A fragment representing the back of the card.
     */
	@SuppressLint("ValidFragment")
	public class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	View view = inflater.inflate(R.layout.fragment_flashcard_back, container, false);

        	TextView answerTextView = (TextView)view.findViewById(R.id.answer); 
        	answerTextView.setText("Answer: "+flashcard.getAnswer());
            
            TextView tagsTextView = (TextView)view.findViewById(R.id.tags); 
            tagsTextView.setText("Tags: "+flashcard.getTagsString());
            
          
            return view;
        }
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashcard);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
		
		Intent intent = getIntent();
		
		final String deckId = intent.getExtras().getString("deck_id");
		final String deckName = intent.getExtras().getString("deck_name");
		
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
        
        this.setTitle((MainActivity.currentFlashcardIndex+1) + " of " + MainActivity.getCurrentDeck().getFlashcards().size());
        
        ShakeListener mShaker = new ShakeListener(this);
	    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
	    	public void onShake(){
	    		shuffle();
	    	}
	    });
        
        
	}
	
	void shuffle() {
		Deck deck = MainActivity.getCurrentDeck();
		ArrayList<GTFlashcards> list = deck.getFlashcards();
		Collections.shuffle(list);
		MainActivity.currentFlashcardIndex = 0;
		Intent intent = new Intent(this, FlashcardActivity.class);
        startActivityForResult(intent, 500);
	}

	private void flipCard() {
	    if (mShowingBack) {
	        getFragmentManager().popBackStack();
	        return;
	    }

	    // Flip to the back.

	    mShowingBack = true;

	    // Create and commit a new fragment transaction that adds the fragment for the back of
	    // the card, uses custom animations, and is part of the fragment manager's back stack.

	    getFragmentManager()
	            .beginTransaction()

	            // Replace the default fragment animations with animator resources representing
	            // rotations when switching to the back of the card, as well as animator
	            // resources representing rotations when flipping back to the front (e.g. when
	            // the system Back button is pressed).
	            .setCustomAnimations(
	                    R.anim.card_flip_right_in, R.anim.card_flip_right_out,
	                    R.anim.card_flip_left_in, R.anim.card_flip_left_out)

	            // Replace any fragments currently in the container view with a fragment
	            // representing the next page (indicated by the just-incremented currentPage
	            // variable).
	            .replace(R.id.container, new CardBackFragment())

	            // Add this transaction to the back stack, allowing users to press Back
	            // to get to the front of the card.
	            .addToBackStack(null)

	            // Commit the transaction.
	            .commit();
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
	
	@Override
	public void onBackPressed(){
		NavUtils.navigateUpFromSameTask(this);
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
        
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
        	flipCard();
            return true;
        }

    }


}
