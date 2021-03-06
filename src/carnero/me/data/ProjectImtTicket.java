package carnero.me.data;

import carnero.me.R;
import carnero.me.model.EntryIntent;
import carnero.me.model.Work;

@SuppressWarnings("UnusedDeclaration")
public class ProjectImtTicket extends Work {

	public ProjectImtTicket() {
		year = 2012;
		month = 6;
		name = "SMS Ticket";
		description = "User friendly way to order ticket in Czech republic";
		client = "Inmite";
		downloads = 50000;
		months = 1;
		iconResource = R.drawable.ic_android;
		background = R.drawable.bcg_project_ticket_aligned;
		tapAction = new EntryIntent().setPackage("eu.inmite.apps.smsjizdenka");
	}
}
