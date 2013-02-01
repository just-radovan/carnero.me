package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Network;

@SuppressWarnings("UnusedDeclaration")
public class NetworkFlickr extends Network {

	public NetworkFlickr() {
		iconOn = R.drawable.ic_flickr;
		iconOff = R.drawable.ic_flickr_off;
		title = R.string.network_flickr;
		description = R.string.network_flickr_desc;
		tapAction = EntryIntent.getWebIntent("http://www.flickr.com/photos/carnero/");
		packageName = "com.yahoo.mobile.client.android.flickr";
	}
}