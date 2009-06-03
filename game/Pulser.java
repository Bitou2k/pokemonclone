
package game;

/**
*Metronome?
*/
public abstract class Pulser
{
	private final Object outer=this;
	private long period; //ms
	private volatile Thread runner;
	
	public Pulser(long periodInMilliseconds)
	{
		period=periodInMilliseconds;
	}
	
	public synchronized final void start()
	{
		stop();
		
		runner = new Thread(){
			public void run()
			{
				while(runner==Thread.currentThread())
				{
					try
					{
						Thread.sleep(period);
						synchronized(outer)
						{
							pulse();
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		};
		runner.start();
	}
	public synchronized final void stop()
	{
		runner = null;
	}
	
	public abstract void pulse();
}