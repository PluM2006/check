package check;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.check.app.service.impl.CustomArrayList;

public class CustomArrayListTest {

	private Random random = new Random();
	private CustomArrayList<Integer> listA;
	private CustomArrayList<Integer> listB;

	@Before
	public void setUp() throws Exception {
		listA = new CustomArrayList<>();
		listB = new CustomArrayList<>();
	}

	@Test
	public void testsetMaxSize() {
		int sizeArray = random.nextInt(20) + 10;
		int maxSizeL = sizeArray / 2;
		int maxSizeM = sizeArray * 2;
		getArray(sizeArray, listA);
		listA.setMaxSize(maxSizeL);
		assertEquals(maxSizeL, listA.size());
		//Дополним list до + 5
		int k = maxSizeL-listA.size() + 5;
		for (int i = 1; i < k ; i++) {
			addItem(listA);
		}
		assertEquals(maxSizeL, listA.size());
		listA.setMaxSize(maxSizeM);
		//Дополним list до конца
		k = maxSizeM - listA.size();
		for (int i = 1; i <= k; i++) {
			addItem(listA);
		}
		assertEquals(maxSizeM, listA.size());
	}

	@Test
	public void testAdd() {
		int sizeList = getArray(random.nextInt(20), listA);
		assertEquals(listA.size(), sizeList);
	}

	@Test
	public void testAddAll() {
		int sizelistA = random.nextInt(20);
		int sizelistB = random.nextInt(20);
		getArray(sizelistA, listA);
		getArray(sizelistB, listB);
		listA.addAll(listB);
		assertEquals(listA.size(), sizelistB + sizelistA);
	}

	@Test
	public void testSet() {

	}

	@Test
	public void testRemove() {

	}

	@Test
	public void testClear() {

	}

	@Test
	public void findTest() {

	}

	@Test
	public void testGet() {

	}

	@Test
	public void testToArray() {

	}

	@Test
	public void testTrim() {

	}

	private int getArray(int size, CustomArrayList<Integer> list) {
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
