import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import models.User;

@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		// Check if the database is empty
		if (User.count() == 0) {
			Fixtures.load("data.yml");
		}
	}

}
