package com.test.learn.jdk;

import java.util.Collections;
import java.util.List;

public class CollectionsTest {

	public static void main(String[] args) {
		List<String> list4 = Collections.nCopies(5, "哈哈");
		System.out.println(list4);
	}

}
