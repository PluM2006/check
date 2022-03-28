package com.check.app.service;

public interface MyIterator<E> {

	E next();

	boolean hasNext();

	void remove();

	void addBefore();

	void addAfter();

}
