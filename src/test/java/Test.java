import pkg.exoad.distrib.DistribFolder;
import pkg.exoad.distrib.Exoad;
public class Test
{
	public static void main(String[] args)
	{
		Exoad.arm();
		Exoad.registerProgram(
				new DistribFolder(
						"test"
				).withSubFolder(
						"test2"
				));
		System.out.println(Exoad.isRegistered("test"));
		System.out.println(Exoad.isRegistered("tes2t"));
	}
}
