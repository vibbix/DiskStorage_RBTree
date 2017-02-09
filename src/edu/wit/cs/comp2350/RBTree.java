package edu.wit.cs.comp2350;

import java.awt.*;
import java.util.Collection;

public class RBTree extends LocationHolder {
	private final static DiskLocation nil = new DiskLocation(-1, -1);
    private DiskLocation root = null;
	private void setRed(DiskLocation d) {
        if (isNull(d)){
            return;
        }
		if (d != nil)
			d.color = RB.RED;
	}
	/**
	 * If dislocation <em>d</em> is already in the tree, this method returns
	 * the equivalent location with the relevant metadeta. Otherwise returns
	 * null.
	 * @param d disk location to search for
	 * @return disk location with metadeta; if not in database, it's in the
	 */
	@Override
	public DiskLocation find(DiskLocation d) {
        return find(d, this.root);
	}



    /**
     * Gets the next disklocation.
     * @param d DiskLocation
     * @return the next disklocation
     */
    @Override
	public DiskLocation next(DiskLocation d) {
        if (isNull(d)){
            return nil;
        }
        // TODO Auto-generated method stub
        // TODO make sure doesn't fail if next items are all equal
		return null;
	}

	/**
	 * @param d disk location use for search
	 * @return The previous location.
	 */
	@Override
	public DiskLocation prev(DiskLocation d) {
        if (isNull(d)){
            return nil;
        }
		return d.parent;
	}

	/**
     * Inserts a new disk order
	 * @param d DiskLocation to insert
	 */
	@Override
	public void insert(DiskLocation d) {
	    if (isNull(d)){
	        return;
        }
        if (isNull(this.root)) {
            this.root = d;
            return;
        }
        DiskLocation s = this.root;
        while(true) {
            int compare = (d.isGreaterThan(s))? 1 : -1;
            if (compare == -1)
                if(d.equals(s))
                    compare = 0;
            if (compare == 0) {
                this.root = s;
            } else if (compare < 0){
                if (isNull(getLeftOf(s)))
                {
                    s.left = d;
                    d.right = s;
                    fixAfterInsert(s.left);
                    break;
                }
            } else {
                if (isNull(getRightOf(s))){
                    s.right = d;
                    d.left = s;
                    fixAfterInsert(s.right);
                    break;
                }
            }
            s = getRightOf(s);
        }
	}

    /**
     * Gets the black-node leaning height of the tree
     * @return height of the tree
     */
    @Override
	public int height() {
        // TODO Auto-generated method stub
		return 0;
	}
	// beginregion helpers
    /**
     * @param d disklocation to search
     * @return grandparent of d; otherwise null
     */
    private DiskLocation getGrandParentOf(DiskLocation d) {
        return  isNull(d) || isNull(d.parent) ? nil : d.parent.parent;
    }
    private DiskLocation getSiblingOf(DiskLocation d) {
        return isNull(d) || isNull(d.parent) ? nil :
                (d.equals(d.parent.left)) ? d.parent.right : d.parent.left;
    }
    private boolean isNull(DiskLocation d) {
        return d == null || d.equals(nil);
    }
    private DiskLocation getLeftOf(DiskLocation d){
        return isNull(d) ? nil : d.left;
    }
    private DiskLocation getRightOf(DiskLocation d){
        return isNull(d) ? nil : d.right;
    }
    private DiskLocation find(DiskLocation find, DiskLocation current){
        if (isNull(find) || isNull(current)){
            if (find.equals(current))
                return current;
            if (current.isGreaterThan(current))
                return find(find, current.left);
            return find(find, current.right);
        }
        return nil;
    }
    private void fixAfterInsert(DiskLocation d){
        if (isNull(d)){
            return;
        }
        d.color = RB.RED;
        if (!d.equals(root))
            if (d.parent.color == RB.RED){
                if(getSiblingOf(d.parent).color == RB.RED){
                    d.parent.color = RB.BLACK;
                    getSiblingOf(d.parent).color = RB.BLACK;
                    getGrandParentOf(d).color = RB.BLACK;
                    fixAfterInsert(getGrandParentOf(d));
                } else if (d.parent.equals(getGrandParentOf(d).left)){
                    if (getRightOf(d.parent).equals(d)){
                        rotateLeft(d = d.parent);
                    }
                    d.parent.color = RB.BLACK;
                    getGrandParentOf(d).color = RB.RED;
                    rotateRight(getGrandParentOf(d));
                } else if (d.parent.equals(getGrandParentOf(d).right)){
                    if (getLeftOf(d.parent).equals(d)) {
                        rotateRight(d = d.parent);
                    }
                    d.parent.color = RB.BLACK;
                    getGrandParentOf(d).color = RB.RED;
                    rotateLeft(getGrandParentOf(d));
                }
            }
        this.root.color = RB.BLACK;
    }
    private void rotateLeft(DiskLocation d){
        if  (isNull(getRightOf(d)))
            return;
        DiskLocation oldright = d.right;
        d.right = oldright.right;
        if (isNull(d.parent)) {
            this.root = oldright;
        } else if (d.parent.left.equals(d)){
            d.parent.left = oldright;
        } else {
            d.parent.right = oldright;
        }
        oldright.left = d;
    }
    private void rotateRight(DiskLocation d){
        if  (isNull(getLeftOf(d)))
            return;
        DiskLocation oldleft = d.left;
        d.left = oldleft.left;
        if (isNull(d.parent)) {
            this.root = oldleft;
        } else if (d.parent.left.equals(d)){
            d.parent.left = oldleft;
        } else {
            d.parent.right = oldleft;
        }
        oldleft.right = d;
    }
    // endregion
}
