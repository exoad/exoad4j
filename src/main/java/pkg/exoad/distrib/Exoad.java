package pkg.exoad.distrib;

import org.apache.logging.log4j.Level;

import java.io.File;

import static pkg.exoad.distrib.SharedConstants.*;
public final class Exoad
{
	private Exoad() {}

	private static boolean isArmed = false;

	public static boolean isArmed()
	{
		return isArmed;
	}

	public static void arm()
	{
		LOG.log(
				Level.INFO,
				"Using debug mode? " + PublicConstants.DEBUG
		);
		if (!FileSysService.ensureExistence(SharedConstants.FOLDER_LOCATION))
		{
			LOG.log(
					Level.WARN,
					"Folder " + SharedConstants.FOLDER_LOCATION + " does not exist. Creating it..."
			);
			File f = new File(SharedConstants.FOLDER_LOCATION);
			if (!f.mkdir())
			{
				LOG.log(
						Level.ERROR,
						"Could not create folder " + SharedConstants.FOLDER_LOCATION + "."
				);
				return;
			}
		}
		else
			LOG.log(
					Level.INFO,
					"Ok, folder exists... moving on!"
			);

		isArmed = true;
	}
}
