package pkg.exoad.distrib;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
final class SharedConstants
{
	private SharedConstants() {}

	public static final String FOLDER_LOCATION = PublicConstants.DEBUG ?
			".exoad" + PublicConstants.PATH_DELIMITER :
			System.getProperty("user.home") + "." + PublicConstants.PATH_DELIMITER + "exoad"
					+ PublicConstants.PATH_DELIMITER;
	public static final String ID_FOLDER = ".ids";
	public static Logger LOG = LogManager.getLogger("exoadfolder-monitorlogger");

	static
	{
		if (!PublicConstants.DEBUG)
			Configurator.setLevel(
					"exoadfolder-monitorlogger",
					Level.OFF
			);
	}
}
