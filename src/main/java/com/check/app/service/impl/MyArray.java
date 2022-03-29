package com.check.app.service.impl;

import java.util.Collection;

import com.check.app.service.MyIterator;
import com.check.app.service.MyList;

public class MyArray<E> implements MyList<E> {

	private E[] values;

	public MyArray() {
		this.values = (E[]) new Object[0];
	}

	@Override
	public MyIterator<E> getIterator() {

	return new MyIteratorImpl(values);
	}

	@Override
	public void setMaxSize(int maxSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(E e) {
		E[] temp = values;
		values = (E[]) new Object[temp.length + 1];
		System.arraycopy(temp, 0, values, 0, temp.length);
		values[values.length - 1] = e;
	}

	@Override
	public void addAll(MyList<? extends E> c) {
		E[] temp = values;
		E[] array = (E[]) c.toArray();
		int lenghValues = temp.length + c.size();
		values = (E[]) new Object[lenghValues];
		System.arraycopy(temp, 0, values, 0, temp.length);
		System.arraycopy(array, 0, values, temp.length, array.length);

	}

	@Override
	public E set(int index, E e) {
		E oldValue = get(index);
		values[index] = e;
		return oldValue;
	}

	@Override
	public E remove(int index) {
		E removeValue = get(index);
		E[] temp = values;
		values = (E[]) new Object[temp.length - 1];
		System.arraycopy(temp, 0, values, 0, index);
		int k = temp.length - index - 1;
		if (k > 0)
			System.arraycopy(temp, index + 1, values, index, k);

		return removeValue;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public int find(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		return values[index];
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[values.length];
		for (int i = 0; i < values.length; i++) {
			array[i] = values[i];
		}
		return array;
	}

	@Override
	public int size() {
		return values.length;
	}

	@Override
	public void trim() {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<values.length; i++) {
			sb.append(values[i]+", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.insert(0, "[");
		sb.insert(sb.length(), "]");
		return sb.toString();
	}

	private class MyIteratorImpl implements MyIterator<E> {
		private int index = 0;
		private int test = -1;
		private E[] arrays;

		public MyIteratorImpl(E[] arrays) {
			this.arrays = arrays;
		}

		@Override
		public E next() {
			return (E) arrays[index++];
		}

		@Override
		public boolean hasNext() {
			return index < arrays.length;
		}

		@Override
		public void remove() {
			E[] temp = arrays;
			arrays = (E[]) new Object[temp.length - 1];
			System.arraycopy(temp, 0, arrays, 0, index-1);
			int k = temp.length - index;
			if (k > 0)
				System.arraycopy(temp, index, arrays, index-1, k);

			MyArray.this.remove(index - 1);
			
		}

		@Override
		public void addBefore() {
			E[] temp = arrays;
			arrays = (E[]) new Object[temp.length + 1];
			System.arraycopy(temp, 0, arrays, 0, temp.length);
			arrays[arrays.length - 1] = (E) new Object[index - 1];

		}

		@Override
		public void addAfter() {
			// TODO Auto-generated method stub

		}

	}

}
