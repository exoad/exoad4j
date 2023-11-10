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

	public static boolean validateFolderName(String name)
	{
		return name != null && !name.isEmpty() && !name.contains("/") && !name.contains("\\") && !name.contains(":")
				&& !name.contains("*") && !name.contains("?") && !name.contains("\"") && !name.contains("<")
				&& !name.contains(">") && !name.contains("|") && !name.contains(" ");
	}
}