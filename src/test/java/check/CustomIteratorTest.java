package check;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.check.app.service.CustomIterator;
import com.check.app.service.impl.CustomArrayList;

public class CustomIteratorTest {
	private CustomArrayList<String> list;
	private CustomIterator<String> it;

	@Before
	public void setUp() throws Exception {
		list = new CustomArrayList<>();
		list.add("Apple");
		list.add("Xiaomi");
		it = list.getIterator();
	}

	@Test
	public void testNext() {
		assertEquals("Apple", it.next());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testNextException() {
		it.next();
		it.next();
		it.next();
	}

	@Test
	public void testHasNext() {
		assertTrue(it.hasNext());
		it.next();
		it.next();
		assertFalse(it.hasNext());
	}

	@Test
	public void testRemove() {
		it.next();
		it.remove();
		assertEquals(list.size(), 1);
		assertEquals(list.find("Apple"), -1);
	}

	@Test
	public void testAddBefore() {
		String value = "Lenovo";
		it.next();
		it.addBefore(value);
		assertEquals(list.get(0), value);
	}

	@Test
	public void tesAddAfter() {
		String value = "Lenovo";
		it.next();
		it.addAfter(value);
		assertEquals(list.get(1), value);
	}

}
