package com.check.app.service;

import java.util.Collection;

public interface MyList<E>{
	
	MyIterator<E> getIterator();
	
	void setMaxSize(int maxSize);
	
	void add(E e);
	
	void addAll(MyList<? extends E> c);
	
	E set(int index, E e);
	
	E remove(int index);
	
	void clear();
	
	int find(E e);
	
	E get(int index);
	
	Object[] toArray();
	
	int size();
	
	void trim();

}
