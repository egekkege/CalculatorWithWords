package org.egemen.osgi.mathservice.impl;

import org.egemen.osgi.mathservice.MathService;

public class MathServiceImpl implements MathService {

	@Override
	public int sum(int a, int b) {
		return a + b;
	}

}