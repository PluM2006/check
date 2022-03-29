package com.check.app.service.impl;

import java.util.Iterator;

import com.check.app.service.CustomIterator;
import com.check.app.service.CustomList;

public class CustomArrayList<E> implements CustomList<E> {

	private E[] values;
	private int size;
	private int maxSize = Integer.MAX_VALUE;

	@SuppressWarnings("unchecked")
	public CustomArrayList() {
		this.values = (E[]) new Object[10];
	}

	@SuppressWarnings("unchecked")
	public CustomArrayList(int size) {
		if (size >= 0) {
			this.values = (E[]) new Object[size];
		} else {
			throw new IllegalArgumentException("Неверный аргумент " + size);
		}
	}

	@Override
	public CustomIterator<E> getIterator() {
		return new CustomIteratorImpl(values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setMaxSize(int initMaxSize) {

		maxSize = initMaxSize;
		if (maxSize >= 0) {
			if (maxSize >= size) {
				E[] temp = values;
				values = (E[]) new Object[maxSize];
				System.arraycopy(temp, 0, values, 0, size);
			} else {
				E[] temp = values;
				values = (E[]) new Object[maxSize];
				System.arraycopy(temp, 0, values, 0, maxSize);
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
			System.out.println("Достигнут максимальный размер листа: " + maxSize);
		} else {
			E[] temp = values;
			values = (E[]) new Object[size + 1];
			System.arraycopy(temp, 0, values, 0, size);
			values[size] = e;
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	private void add(E e, int index) {
		if (size + 1 > maxSize) {
			System.out.println("Достигнут максимальный размер листа: " + maxSize);
		} else {
			E[] temp = values;
			values = (E[]) new Object[temp.length + 1];
			System.arraycopy(temp, 0, values, 0, index);
			values[index] = e;
			int k = temp.length - index;
			System.arraycopy(temp, index, values, index + 1, k);
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addAll(CustomList<? extends E> c) {
		if (c != null) {
			E[] temp = values;
			E[] array = (E[]) c.toArray();
			int lenghValues = size + c.size();
			values = (E[]) new Object[lenghValues];
			System.arraycopy(temp, 0, values, 0, size);
			System.arraycopy(array, 0, values, size, array.length);
			size = size + array.length;
		}
	}

	@Override
	public E set(int index, E e) {
		E oldValue = get(index);
		values[index] = e;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		E removeValue = get(index);
		E[] temp = values;
		values = (E[]) new Object[size - 1];
		System.arraycopy(temp, 0, values, 0, index);
		int k = size - index - 1;
		if (k > 0)
			System.arraycopy(temp, index + 1, values, index, k);
		size--;
		return removeValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		values = (E[]) new Object[0];
		size = 0;
	}

	@Override
	public int find(E e) {
		if (e == null) {
			for (int i = 1; i < size; i++) {
				if (values[i] == null)
					return i;
			}
		} else {
			for (int i = 1; i < size; i++) {
				if (e.equals(values[i]))
					return i;
			}
		}
		return -1;
	}

	@Override
	public E get(int index) {
		return this.values[index];
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = values[i];
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
			if (values[i] != null)
				list.add(values[i]);
			else
				size--;
		}
		values = (E[]) list.toArray();

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			if (sb.length() > 1)
				sb.append(", " + values[i]);
			else
				sb.append(values[i]);
		}
		sb.append("]");
		return sb.toString();
	}

	private class CustomIteratorImpl implements CustomIterator<E> {
		private int index = 0;
		private int indexDel = 0;
		private E[] arrays;

		public CustomIteratorImpl(E[] arrays) {
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
			indexDel = index - arrays.length - size - 1;
			CustomArrayList.this.remove(indexDel);
		}

		@Override
		public void addBefore(E e) {
			CustomArrayList.this.add(e, getAddIndex(size, arrays.length, index) - 1);
		}

		@Override
		public void addAfter(E e) {
			CustomArrayList.this.add(e, getAddIndex(size, arrays.length, index));
		}

		private int getAddIndex(int size, int sizeArrays, int index) {
			return size - sizeArrays + index;
		}

	}

	@Override
	public Iterator<E> iterator() {
		return new CustomIteratorImpl(values);
	}

}
