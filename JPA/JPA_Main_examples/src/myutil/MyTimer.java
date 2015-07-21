package myutil;

public class MyTimer {

	private long begin;

	private boolean isActive;

	private static final long NANOSECOND_TO_SECOND = 1000000000;
	public void start() {
		begin = System.nanoTime();
		isActive = true;
	}

	public String stop() {
		float diff = 1;
		long end = System.nanoTime();
		if (isActive) {
			diff = (end - begin);
			isActive = false;
		} else
			diff = -1;
		return String.format("%.5f", diff/NANOSECOND_TO_SECOND);
	}
}
