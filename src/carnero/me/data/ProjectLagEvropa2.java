package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectLagEvropa2 extends Work {

	public ProjectLagEvropa2() {
		year = 2012;
		month = 6;
		name = "Evropa 2";
		description = "Player for one of most prominent czech radio stations";
		client = "Lagardère Active ČR";
		downloads = 50000;
		months = 1;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_evropa2_aligned;
		tapAction = new EntryIntent().setPackage("eu.inmite.lag.radio.evropa2");
	}
}
