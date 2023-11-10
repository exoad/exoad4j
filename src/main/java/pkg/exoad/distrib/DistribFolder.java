package pkg.exoad.distrib;
import java.util.ArrayList;
import java.util.UUID;
public final class DistribFolder
{
	public final String name;
	public ArrayList< String > subFolders;

	public DistribFolder(String name)
	{
		if (!FileSysService.validateFolderName(name))
			throw new IllegalArgumentException("Invalid folder name: " + name);
		this.name = name;
		this.subFolders = new ArrayList<>();
	}

	public DistribFolder withSubFolder(String folder)
	{
		if (subFolders == null)
			subFolders = new ArrayList<>();
		subFolders.add(folder);
		return this;
	}

	public String getPath()
	{
		return SharedConstants.FOLDER_LOCATION + PublicConstants.PATH_DELIMITER + name + PublicConstants.PATH_DELIMITER;
	}

	public long id()
	{
		return UUID.nameUUIDFromBytes(name.getBytes())
		           .getMostSignificantBits();
	}
}
