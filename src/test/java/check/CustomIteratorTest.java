package check;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.check.app.service.CustomIterator;
import com.check.app.service.impl.CustomArrayList;

public class CustomIteratorTest {
	private Random random;
	private CustomArrayList<Integer> list;
	@Before
	public void setUp() throws Exception {
		list = new CustomArrayList<>();
		random = new Random();
		
	}
	@Test
	public void testNext() {
		getList(random.nextInt(100), list);
		int size = 0;
		CustomIterator<Integer> it = list.getIterator();
		while (it.hasNext()) {
			assertEquals(it.next(), list.get(size));
			size++;
		}
		assertEquals(size, list.size());
		
	}
	@Test
	public void testHasNext() {
		CustomIterator<Integer> it = list.getIterator();
		it.next();
		assertFalse(it.hasNext());
		list.add(1);
		it = list.getIterator();
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
		assertNotNull(it.next());
		assertFalse(it.hasNext());
	}
	@Test
	public void testRemove() {
		list.add(1);
		CustomIterator<Integer> it = list.getIterator();
		it.next();
		it.remove();
		assertEquals(list.size(), 0);
	}
	@Test
	public void testAddBefore() {
		Integer value = random.nextInt();
		list.add(random.nextInt());
		list.add(random.nextInt());
		CustomIterator<Integer> it = list.getIterator();
		it.next();
		it.next();
		it.addBefore(value);
		assertEquals(list.get(1), value);
		
	}
	@Test
	public void tesAddAfter() {
		Integer value = random.nextInt();
		list.add(random.nextInt());
		list.add(random.nextInt());
		CustomIterator<Integer> it = list.getIterator();
		it.next();
		it.addAfter(value);
		assertEquals(list.get(1), value);
	}
	
	private int getList(int size, CustomArrayList<Integer> list) {
		int sizeArray = 0;
		for (int i = 0; i < size; i++) {
			addItem(list);
			sizeArray++;
		}
		return sizeArray;
	}

	private void addItem(CustomArrayList<Integer> list) {
		int max = 100;
		int min = -100;
		int diff = max - min;
		int randomElement = random.nextInt(2);
		if (randomElement == 0)
			list.add(null);
		else
			list.add(random.nextInt(diff) + min);
	}


}
