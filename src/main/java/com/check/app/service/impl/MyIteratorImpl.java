package com.check.app.service.impl;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.check.app.service.MyIterator;

public class MyIteratorImpl<E> implements MyIterator<E> {
	private int index = 0;
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
		System.arraycopy(temp, 0, arrays, 0, index);
		int k = temp.length - index - 1;
		System.arraycopy(temp, index+1, arrays, index, k);
	}

	@Override
	public void addBefore() {
		E[] temp = arrays;
		arrays = (E[]) new Object[temp.length+1];
		System.arraycopy(temp, 0, arrays, 0, temp.length);
		arrays[arrays.length-1] = (E) new Object[index-1];

	}

	@Override
	public void addAfter() {
		// TODO Auto-generated method stub

	}

}
