package com.kcthota.version4j.comparison;

import java.util.Comparator;

import com.kcthota.version4j.models.Version;
/**
 * Comparator for sorting Version objects
 * @author kc
 *
 */
public class VersionComparator implements Comparator<Version> {
	
	public int compare(Version v1, Version v2) {
		return v1.getScalarValue().compareTo(v2.getScalarValue());
	}
}
