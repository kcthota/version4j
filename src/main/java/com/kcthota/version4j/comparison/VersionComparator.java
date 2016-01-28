package com.kcthota.version4j.comparison;

import java.util.Comparator;

import com.kcthota.version4j.models.Version;

public class VersionComparator implements Comparator<Version> {
	
	public int compare(Version v1, Version v2) {
		if (v1.getScalarValue() > v2.getScalarValue()) {
			return 1;
		} else if (v1.getScalarValue() == v2.getScalarValue()) {
			return 0;
		} else {
			return -1;
		}
	}
}
