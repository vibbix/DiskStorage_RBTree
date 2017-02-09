package edu.wit.cs.comp2350;

import java.util.Collection;

public class RBTree extends LocationHolder {

	private static final DiskLocation nil = new DiskLocation(-1, -1);
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
//	    insert(this.root, d);
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
		if (d.right != null && !d.right.equals(nil))
			return this.minValue(d);
		return up(d);
	}

	private DiskLocation up(DiskLocation d) {
		DiskLocation p = d.parent;
		if ((p.parent == null) || (d.parent.equals(this.nil)) || (d == p.left)) {
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

	private void fixInsert(DiskLocation d){
		while(d.parent.color == RB.RED){
            if (d.parent.equals(d.parent.parent.left)){
                DiskLocation y = d.parent.parent.right;
                if (y.color == RB.RED){
                    d.parent.color = RB.BLACK;
                    y.color = RB.BLACK;
                    d.parent.parent.color = RB.RED;
                    d = d.parent.parent;
                } else {
                    if(d.equals(d.parent.right)){
                        d = d.parent;
                        rotateLeft(d);
                    }
                    d.parent.color = RB.BLACK;
                    d.parent.parent.color = RB.RED;
                    rotateRight(d.parent.parent);
                }
            } else {
                this.swapChild(d);
            }
		}
		this.root.color = RB.BLACK;
	}
	private void rotateRight(DiskLocation d){

    }
    private void rotateLeft(DiskLocation d){

    }
    private void swapChild(DiskLocation d){
        DiskLocation temp = d.left;
        d.left = d.right;
        d.right = temp;
    }
    private static boolean isNil(DiskLocation d){
        return (d == null) || (d.equals(nil));
    }
}