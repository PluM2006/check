package check;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.check.app.service.impl.CustomArrayList;

public class CustomArrayListTest {

	private Random random;;
	private CustomArrayList<Integer> listA;
	private CustomArrayList<Integer> listB;

	@Before
	public void setUp() throws Exception {
		listA = new CustomArrayList<>();
		listB = new CustomArrayList<>();
		random= new Random();
	}

	@Test
	public void testsetMaxSize() {
		int sizeArray = random.nextInt(100) + 10;
		int maxSizeL = sizeArray / 2;
		int maxSizeM = sizeArray * 2;
		getList(sizeArray, listA);
		listA.setMaxSize(maxSizeL);
		assertEquals(maxSizeL, listA.size());
		// Дополним list до + 5
		int k = maxSizeL - listA.size() + 5;
		for (int i = 0; i < k; i++) {
			addItem(listA);
		}
		assertEquals(maxSizeL, listA.size());
		listA.setMaxSize(maxSizeM);
		// Дополним list до конца
		k = maxSizeM - listA.size();
		for (int i = 0; i <= k; i++) {
			addItem(listA);
		}
		assertEquals(maxSizeM, listA.size());
	}

	@Test
	public void testAdd() {
		int sizeList = getList(random.nextInt(20), listA);
		assertEquals(listA.size(), sizeList);
	}

	@Test
	public void testAddAll() {
		int sizelistA = random.nextInt(100)+10;
		int sizelistB = random.nextInt(100)+10;
		getList(sizelistA, listA);
		getList(sizelistB, listB);
		listA.addAll(listB);
		assertEquals(listA.size(), sizelistB + sizelistA);
	}

	@Test
	public void testSet() {
		int sizeList = random.nextInt(100)+10;
		getList(sizeList, listA);
		int value = random.nextInt(10);
		int index = random.nextInt(sizeList);
		listA.set(index, value);
		assertEquals(listA.get(index), Integer.valueOf(value));
	}

	@Test
	public void testRemove() {
		getList(random.nextInt(100) + 10, listA);
		int size = listA.size();
		listA.remove(size / 2);
		assertEquals(size - 1, listA.size());

	}

	@Test
	public void testClear() {
		getList(random.nextInt(100) + 10, listA);
		listA.clear();
		assertEquals(listA.size(), 0);
	}

	@Test
	public void findTest() {
		getList(random.nextInt(100) + 10, listA);
		int value = random.nextInt();
		listA.set(random.nextInt(listA.size()), value);
		int index = listA.find(value);
		assertEquals(Integer.valueOf(value), listA.get(index));
	}

	@Test
	public void testGet() {
		getList(random.nextInt(100) + 10, listA);
		int value = random.nextInt();
		int index = random.nextInt(listA.size());
		listA.set(index, value);
		assertEquals(Integer.valueOf(value), listA.get(index));
	}

	@Test
	public void testToArray() {
		getList(random.nextInt(100) + 10, listA);
		Object[] array = listA.toArray();
		assertEquals(listA.size(), array.length);

	}

	@Test
	public void testTrim() {
		getList(random.nextInt(100) + 10, listA);
		int sizelist = listA.size();
		int valueNull = 0;
		for (int i = 0; i < listA.size(); i++) {
			if (listA.get(i) == null)
				valueNull++;
		}
		listA.trim();
		assertEquals(listA.size(), sizelist - valueNull);

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
