package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagPigy extends Work {

	public ProjectLagPigy() {
		year = 2011;
		month = 11;
		name = "Pigy.cz";
		description = "Radio player for children";
		client = "Lagardère Active ČR";
		downloads = 1000;
		months = 1;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_pigy_aligned;
		tapAction = new EntryIntent().setPackage("eu.inmite.lag.pigy.player");
	}
}
