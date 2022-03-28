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
		return new MyIteratorImpl<E>(values);
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
	public void addAll(Collection<? extends E> c) {
		E[] temp = values;
		E[] array = (E[]) c.toArray();
		int lenghValues = temp.length+c.size();
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
		System.arraycopy(temp, index+1, values, index, k);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void trim() {
		// TODO Auto-generated method stub

	}

}
