package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkFoursquare extends Network {

	public NetworkFoursquare() {
		iconOn = R.drawable.ic_foursquare;
		iconOff = R.drawable.ic_foursquare_off;
		title = R.string.network_foursquare;
		description = R.string.network_foursquare_desc;
		tapAction = new EntryIntent().setWeb("https://foursquare.com/carnero_cc");
		packageName = new String[]{
				"com.joelapenna.foursquared",
				"com.foursquare.merchant",
				"com.matthewrathbone.simplecheckin",
				"com.matthewrathbone.wificheckin"
		};
	}
}
