package pkg.exoad.distrib;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
final class SharedConstants
{
	private SharedConstants() {}

	public static final String FOLDER_LOCATION = PublicConstants.DEBUG ?
			".exoad" :
			System.getProperty("user.home") + "/.exoad/";

	public static final Logger LOG = LogManager.getLogger("ExoadDistrib-FolderMonitor");
}
