package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkTwitter extends Network {

	public NetworkTwitter() {
		iconOn = R.drawable.ic_twitter;
		iconOff = R.drawable.ic_twitter_off;
		title = R.string.network_twitter;
		description = R.string.network_twitter_desc;
		tapAction = new EntryIntent().setWeb("https://twitter.com/carnero_cc");
		packageName = new String[]{
				"com.twitter.android",
				"com.jv.falcon.pro",
				"com.thedeck.android.app",
				"com.levelup.touiteur",
				"com.hootsuite.droid.full",
				"com.dotsandlines.carbon"
		};
	}
}
