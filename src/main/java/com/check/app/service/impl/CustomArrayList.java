package com.check.app.service.impl;

import java.util.Iterator;

import com.check.app.service.CustomIterator;
import com.check.app.service.CustomList;

public class CustomArrayList<E> implements CustomList<E> {

	private E[] arrays;
	private int size;
	private int maxSize = Integer.MAX_VALUE;

	@SuppressWarnings("unchecked")
	public CustomArrayList() {
		this.arrays = (E[]) new Object[10];
	}

	@SuppressWarnings("unchecked")
	public CustomArrayList(int size) {
		if (size >= 0) {
			this.arrays = (E[]) new Object[size];
		} else {
			throw new IllegalArgumentException("Неверный аргумент " + size);
		}
	}

	@Override
	public CustomIterator<E> getIterator() {
		return new CustomIteratorImpl();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setMaxSize(int initMaxSize) {

		maxSize = initMaxSize;
		if (maxSize >= 0) {
			if (maxSize >= size) {
				E[] temp = arrays;
				arrays = (E[]) new Object[maxSize];
				System.arraycopy(temp, 0, arrays, 0, size);
			} else {
				E[] temp = arrays;
				arrays = (E[]) new Object[maxSize];
				System.arraycopy(temp, 0, arrays, 0, maxSize);
				size = maxSize;
			}
		} else {
			throw new IllegalArgumentException("Неверный аргумент " + initMaxSize);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(E e) {
		if (size + 1 > maxSize) {
			System.err.println("Достигнут максимальный размер листа: " + maxSize);
		} else {
			E[] temp = arrays;
			arrays = (E[]) new Object[size + 1];
			System.arraycopy(temp, 0, arrays, 0, size);
			arrays[size] = e;
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	private void add(E e, int index) {
		if (size + 1 > maxSize) {
			System.err.println("Достигнут максимальный размер листа: " + maxSize);
		} else {
			E[] temp = arrays;
			arrays = (E[]) new Object[temp.length + 1];
			System.arraycopy(temp, 0, arrays, 0, index);
			arrays[index] = e;
			int k = temp.length - index;
			System.arraycopy(temp, index, arrays, index + 1, k);
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addAll(CustomList<? extends E> c) {
		if (c != null) {
			E[] temp = arrays;
			E[] array = (E[]) c.toArray();
			int lenghValues = size + c.size();
			arrays = (E[]) new Object[lenghValues];
			System.arraycopy(temp, 0, arrays, 0, size);
			System.arraycopy(array, 0, arrays, size, array.length);
			size = size + array.length;
		}
	}

	@Override
	public E set(int index, E e) {
		E oldValue = get(index);
		arrays[index] = e;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		E removeValue = get(index);
		E[] temp = arrays;
		arrays = (E[]) new Object[size - 1];
		System.arraycopy(temp, 0, arrays, 0, index);
		int k = size - index - 1;
		if (k > 0)
			System.arraycopy(temp, index + 1, arrays, index, k);
		size--;
		return removeValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arrays = (E[]) new Object[0];
		size = 0;
	}

	@Override
	public int find(E e) {
		if (e == null) {
			for (int i = 0; i < size; i++) {
				if (arrays[i] == null)
					return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (e.equals(arrays[i]))
					return i;
			}
		}
		return -1;
	}

	@Override
	public E get(int index) {
		return this.arrays[index];
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = arrays[i];
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void trim() {
		CustomList<E> list = new CustomArrayList<E>();
		for (int i = 0; i < size; i++) {
			if (arrays[i] != null)
				list.add(arrays[i]);
		}
		arrays = (E[]) list.toArray();
		size = arrays.length;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			if (sb.length() > 1)
				sb.append(", " + arrays[i]);
			else
				sb.append(arrays[i]);
		}
		sb.append("]");
		return sb.toString();
	}
	private class CustomIteratorImpl implements CustomIterator<E> {
		private int indexPos = 0;

		public CustomIteratorImpl() {
		}

		@Override
		public E next() {
			int i = indexPos;
			indexPos++;
			return get(i);
			
		}

		@Override
		public boolean hasNext() {
			return indexPos < size;
		}

		@Override
		public void remove() {
			CustomArrayList.this.remove(indexPos-1);
			indexPos--;
		}

		@Override
		public void addBefore(E e) {
			CustomArrayList.this.add(e, indexPos-1);
			indexPos++;
			
		}

		@Override
		public void addAfter(E e) {
			CustomArrayList.this.add(e, indexPos);
			indexPos++;
			
		}

	}
	
	@Override
	public Iterator<E> iterator() {
		return new CustomIteratorImpl();
	}

}
