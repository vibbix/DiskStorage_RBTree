package edu.wit.cs.comp2350;

public class RBTree extends LocationHolder {
	
	private DiskLocation nil = new DiskLocation(-1, -1);

	private void setRed(DiskLocation z) {
		if (z != nil)
			z.color = RB.RED;
	}
	
	@Override
	public DiskLocation find(DiskLocation d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiskLocation next(DiskLocation d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiskLocation prev(DiskLocation d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DiskLocation d) {
		// TODO Auto-generated method stub

	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

}
