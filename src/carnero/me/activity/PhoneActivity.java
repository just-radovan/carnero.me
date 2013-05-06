package carnero.me.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import carnero.me.Constants;
import carnero.me.R;
import carnero.me.Utils;
import carnero.me.fragment.NetworksFragment;
import carnero.me.fragment.TimelineFragment;
import carnero.me.fragment.VcardFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class PhoneActivity extends SlidingFragmentActivity {

	private SharedPreferences mPrefs;
	private View mContainerVcard;
	private View mVerticalLeft;
	private View mVerticalRight;
	private View mHintLeft;
	private View mHintRight;
	private Tracker mTracker;
	private static final Handler sHandler = new Handler();
	// consts
	public static final int SIDE_LEFT = 1;
	public static final int SIDE_RIGHT = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Utils.isWide(this)) { // wide enough, switch to tablet layout
			final Intent intent = new Intent();
			intent.setClass(this, TabletActivity.class);
			startActivity(intent);
			finish();

			return;
		}

		mPrefs = getPreferences(MODE_PRIVATE);

		final SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset((int) getResources().getDimension(R.dimen.sliding_menu_offset));
		menu.setFadeDegree(0.5f);
		menu.setShadowWidthRes(R.dimen.sliding_menu_shadow);
		menu.setShadowDrawable(R.drawable.shadow_left);
		menu.setSecondaryShadowDrawable(R.drawable.shadow_right);

		// activity content
		setContentView(R.layout.activity_phone);

		mContainerVcard = findViewById(R.id.container_vcard);
		mVerticalLeft = findViewById(R.id.vertical_left);
		mVerticalRight = findViewById(R.id.vertical_right);
		mHintLeft = findViewById(R.id.side_hint_left);
		mHintRight = findViewById(R.id.side_hint_right);

		setBehindContentView(R.layout.menu_primary);
		menu.setSecondaryMenu(R.layout.menu_secondary);

		if (savedInstanceState == null) {
			// vcard
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.container_vcard, new VcardFragment())
					.commit();

			/*
			 *  displaying of layout waits until onCreate completes
			 *  load another fragments little bit later to allow fast displaying of activity_phone one
			 */
			sHandler.postDelayed(new Runnable() {
				@Override
				public void run() {

					// networks
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.container_networks, new NetworksFragment())
							.commit();

					// timeline
					getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.container_timeline, new TimelineFragment())
							.commit();

				}
			}, 250);
		}

		menu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			@Override
			public void onOpened() {
				// TODO carnero: animation
				mContainerVcard.setVisibility(View.INVISIBLE);
				if (menu.isSecondaryMenuShowing()) {
					mVerticalRight.setVisibility(View.VISIBLE);
				} else {
					mVerticalLeft.setVisibility(View.VISIBLE);
				}

				final SharedPreferences.Editor edit = mPrefs.edit();
				edit.putBoolean(Constants.PREF_SIDE_USED, true);
				edit.commit();

				if (mTracker != null) {
					mTracker.sendEvent("activity_phone", "open", "slide_menu", 0l);
				}
			}
		});

		menu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
			@Override
			public void onClose() {
				mVerticalLeft.setVisibility(View.GONE);
				mVerticalRight.setVisibility(View.GONE);
				mContainerVcard.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

		EasyTracker.getInstance().activityStart(this);

		final GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
		mTracker = analytics.getTracker(getString(R.string.ga_trackingId));
		mTracker.sendEvent("activity_phone", "start", "phone", 0l);
	}

	@Override
	public void onResume() {
		super.onResume();

		if (!mPrefs.getBoolean(Constants.PREF_SIDE_USED, false)) {
			final Animator animatorHintLeft = AnimatorInflater.loadAnimator(this, R.animator.side_hint);
			final Animator animatorHintRight = AnimatorInflater.loadAnimator(this, R.animator.side_hint);

			animatorHintLeft.addListener(new HintAnimatorListener(SIDE_LEFT));
			animatorHintRight.addListener(new HintAnimatorListener(SIDE_RIGHT));

			animatorHintLeft.setTarget(mHintLeft);
			animatorHintRight.setTarget(mHintRight);

			animatorHintLeft.start();
			animatorHintRight.start();
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		EasyTracker.getInstance().activityStop(this);
	}

	private class HintAnimatorListener implements Animator.AnimatorListener {

		private int mSide;

		public HintAnimatorListener(int side) {
			mSide = side;
		}

		@Override
		public void onAnimationStart(Animator animator) {
			switch (mSide) {
				case SIDE_LEFT:
					mHintLeft.setVisibility(View.VISIBLE);
					break;
				case SIDE_RIGHT:
					mHintRight.setVisibility(View.VISIBLE);
					break;
			}
		}

		@Override
		public void onAnimationEnd(Animator animator) {
			switch (mSide) {
				case SIDE_LEFT:
					mHintLeft.setVisibility(View.GONE);
					break;
				case SIDE_RIGHT:
					mHintRight.setVisibility(View.GONE);
					break;
			}
		}

		@Override
		public void onAnimationRepeat(Animator animator) {
			// nothing
		}

		@Override
		public void onAnimationCancel(Animator animator) {
			// nothing
		}
	}
}