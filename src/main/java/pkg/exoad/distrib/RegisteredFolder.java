package pkg.exoad.distrib;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
public final class RegisteredFolder
{
	private final DistribFolder folder;

	public RegisteredFolder(DistribFolder name)
	{
		this.folder = name;
	}

	public void createFolder(String path)
	{
		if (path.contains("..") || path.contains("..."))
		{
			SharedConstants.LOG.log(
					Level.ERROR,
					"Path " + path + " contains invalid characters!"
			);
			return;
		}
		File f = new File(folder.getPath() + path);
		if (!f.exists() || !f.isDirectory())
		{
			if (f.mkdir())
				SharedConstants.LOG.log(
						Level.INFO,
						"Created folder " + f.getAbsolutePath()
				);
			else
				SharedConstants.LOG.log(
						Level.ERROR,
						"Could not create folder " + f.getAbsolutePath()
				);
		}
	}

	public File representation()
	{
		return new File(folder.getPath());
	}

	public void writeToFile(String data, String file)
	{
		writeToFile(
				data.getBytes(),
				file
		);
	}

	public void writeToFile(byte[] data, String file)
	{
		File f = new File(folder.getPath() + file);
		if (!f.exists() || !f.isFile())
			createFile(file);
		try
		{
			java.nio.file.Files.write(
					f.toPath(),
					data
			);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public void createFile(String path)
	{
		File f = new File(folder.getPath() + path);
		if (!f.exists() || !f.isFile())
		{
			try
			{
				if (f.createNewFile())
					SharedConstants.LOG.log(
							Level.INFO,
							"Created file " + f.getAbsolutePath()
					);
				else
					SharedConstants.LOG.log(
							Level.ERROR,
							"Could not create file " + f.getAbsolutePath()
					);
			} catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
