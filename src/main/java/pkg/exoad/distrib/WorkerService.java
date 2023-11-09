package pkg.exoad.distrib;
import java.util.Timer;
import java.util.TimerTask;
final class WorkerService
{
	private WorkerService() {}

	private static final Timer EXOAD_FOLDER_MONITOR = new Timer(
			"Exoad Folder Monitor",
			true
	);

	private static TimerTask _wrap(Runnable r)
	{
		return new TimerTask()
		{
			@Override
			public void run()
			{
				r.run();
			}
		};
	}

	public static void submitTask(Runnable task)
	{
		EXOAD_FOLDER_MONITOR.schedule(
				_wrap(task),
				0
		);
	}

	public static void submitTask(Runnable task, long delay)
	{
		EXOAD_FOLDER_MONITOR.schedule(
				_wrap(task),
				delay
		);
	}

	public static void submitTask(Runnable task, long delay, long period)
	{
		EXOAD_FOLDER_MONITOR.scheduleAtFixedRate(
				_wrap(task),
				delay,
				period
		);
	}
}
