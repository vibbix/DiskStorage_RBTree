package edu.wit.cs.comp2350;

import java.util.Collection;

public class RBTree extends LocationHolder {

	private DiskLocation nil = new DiskLocation(-1, -1);
	private DiskLocation root = null;

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
		return this.succ(d);
	}

	@Override
	public DiskLocation prev(DiskLocation d) {
		return d.parent;
	}

	@Override
	public void insert(DiskLocation d) {
		d.parent = this.findParent(d, this.root, nil);
		if (d.parent == null || d.parent.equals(nil)) {
			this.root = d;
		} else {
			if (d.parent.isGreaterThan(d)) {
				d.parent.left = d;
			} else {
				d.parent.right = d;
			}
		}
		d.left = nil;
		d.right = nil;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	private DiskLocation findParent(DiskLocation n, DiskLocation curr, DiskLocation parr) {
		if (curr == null) {
			return parr;
		}
		if (curr.isGreaterThan(n)) {
			return findParent(n, curr.left, curr);
		} else {
			return findParent(n, curr.right, curr);
		}
	}

	private DiskLocation succ(DiskLocation d) {
		if (d.right != null)
			return this.minValue(d);
		return up(d);
	}

	private DiskLocation up(DiskLocation d) {
		DiskLocation p = d.parent;
		if ((p.parent == null) || (d.parent.equals(this.nil))|| (d == p.left)) {
			return p;
		}
		return up(p);
	}

	private DiskLocation minValue(DiskLocation d) {
		DiskLocation dl = d;
		while (dl.right != null) {
			dl = dl.right;
		}
		return dl;
	}

	//private void fixInsert(DiskLocation d){
	//	while(d.parent.color == RB.RED){
	//		if (d.parent)
	//	}
	//}
}