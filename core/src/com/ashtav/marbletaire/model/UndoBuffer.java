package com.ashtav.marbletaire.model;

import java.util.LinkedList;

public class UndoBuffer<E> extends LinkedList<E> {

	private static final long serialVersionUID = 1L;
	private int limit;

    public UndoBuffer(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        boolean added = super.add(o);
        while (added && size() > limit) {
           super.remove();
        }
        return added;
    }

	public E get() {
		if(this.size() == 0) {
			return null;
		}
		return super.removeLast();
	}
	
	public static class UndoItem {
		private Integer fromRow;
		private Integer fromColumn;
		private Integer toRow;
		private Integer toColumn;

		public UndoItem(Integer fromRow, Integer fromColumn, Integer toRow, Integer toColumn) {
			super();
			this.fromRow = fromRow;
			this.fromColumn = fromColumn;
			this.toRow = toRow;
			this.toColumn = toColumn;
		}

		public Integer getFromRow() {
			return fromRow;
		}

		public void setFromRow(Integer fromRow) {
			this.fromRow = fromRow;
		}

		public Integer getFromColumn() {
			return fromColumn;
		}

		public void setFromColumn(Integer fromColumn) {
			this.fromColumn = fromColumn;
		}

		public Integer getToRow() {
			return toRow;
		}

		public void setToRow(Integer toRow) {
			this.toRow = toRow;
		}

		public Integer getToColumn() {
			return toColumn;
		}

		public void setToColumn(Integer toColumn) {
			this.toColumn = toColumn;
		}
	}
}