package pkg.exoad.distrib;

import org.apache.logging.log4j.Level;

import java.io.File;

import static pkg.exoad.distrib.SharedConstants.*;
public final class Exoad
{
	private Exoad() {}

	private static boolean isArmed = false;
	private static RegisteredFolder idFolder;

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
				isArmed = false;
				return;
			}
		}
		else
			LOG.log(
					Level.INFO,
					"Ok, folder exists... moving on!"
			);
		isArmed = true;

		idFolder = _registerProgram(new DistribFolder(ID_FOLDER));
	}

	private static void submitID(long id)
	{
		if (isArmed())
		{
			idFolder.createFile(
					id + ""
			);
		}
		else
			throw new IllegalStateException("Exoad is not armed!");
	}

	private static synchronized RegisteredFolder _registerProgram(DistribFolder folder)
	{
		if (!isArmed)
			throw new IllegalStateException("Exoad is not armed!");
		if (folder == null)
			throw new IllegalArgumentException("Folder cannot be null!");
		return getRegisteredFolder(
				folder,
				false
		);
	}

	private static RegisteredFolder getRegisteredFolder(DistribFolder folder, boolean registerID)
	{
		LOG.log(
				Level.INFO,
				"Trying to register folder " + folder.getPath() + "..."
		);
		File f = new File(folder.getPath());
		if (doesExist(
				f,
				folder.getPath()
		)) return new RegisteredFolder(folder);
		for (String subFolder : folder.subFolders)
		{
			File subF = new File(folder.getPath() + subFolder);
			if (doesExist(
					subF,
					subF.getPath()
			)) return new RegisteredFolder(folder);
		}
		RegisteredFolder rr = new RegisteredFolder(folder);
		rr.createFile(".exoad_key");
		rr.writeToFile(
				folder.id() + "",
				".exoad_key"
		);
		if (registerID)
			submitID(folder.id());
		return rr;
	}

	public static synchronized RegisteredFolder registerProgram(DistribFolder folder)
	{
		if (!isArmed)
			throw new IllegalStateException("Exoad is not armed!");
		if (folder == null)
			throw new IllegalArgumentException("Folder cannot be null!");
		else
			if (folder.name.startsWith("."))
				throw new IllegalArgumentException("Folder name cannot start with a dot!");
		return getRegisteredFolder(
				folder,
				true
		);
	}

	public static boolean isRegistered(String name)
	{
		if (!isArmed)
			throw new IllegalStateException("Exoad is not armed!");
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be null or empty!");
		File f = new File(SharedConstants.FOLDER_LOCATION + PublicConstants.PATH_DELIMITER + name);
		return f.exists() && f.isDirectory();
	}

	private static boolean doesExist(File subF, String path)
	{
		if (!subF.exists())
		{
			LOG.log(
					Level.WARN,
					"Folder " + path + " does not exist. Creating it..."
			);
			if (!subF.mkdir())
			{
				LOG.log(
						Level.ERROR,
						"Could not create folder " + path + "."
				);
				return true;
			}
		}
		else
			LOG.log(
					Level.INFO,
					"Ok, folder exists... moving on!"
			);
		return false;
	}
}
