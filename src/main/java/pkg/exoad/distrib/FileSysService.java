package pkg.exoad.distrib;
import java.io.File;
public final class FileSysService
{
	private FileSysService() {}

	public static boolean ensureExistence(String name)
	{
		File f = new File(name);
		return f.exists() && (f.isDirectory() || f.isFile());
	}
}
