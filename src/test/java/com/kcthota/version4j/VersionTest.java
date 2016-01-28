package com.kcthota.version4j;


import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kcthota.version4j.comparison.VersionComparator;
import com.kcthota.version4j.exceptions.VersionNotValidException;
import com.kcthota.version4j.models.Version;

public class VersionTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Test
	public void basicTest() {
		Version v1 = new Version("1.2.3");
		
		Version v2 = new Version("1.2");
		
		assertThat(v1.compareTo(v2) > 0);
	}
	
	@Test
	public void zeroMajorTest() {
		Version v1 = new Version("0.100000.100000");
		
		Version v2 = new Version("1.0.0");
		
		assertThat(v1.compareTo(v2) > 0);
	}
	
	@Test
	public void greaterThanTest() {
		Version v1 = new Version("0.1.9");
		
		Version v2 = new Version("0.0.9");
		
		assertThat(v1.lessThan(v2));
	}
	
	@Test
	public void lessThanTest() {
		Version v1 = new Version("0.0.9");
		
		Version v2 = new Version("0.1.0");
		
		assertThat(v1.lessThan(v2));
	}
	
	@Test
	public void maxValueTest() {
		Version v1 = new Version("100000.100000.100000");
		
		assertThat(v1.getMajor()).isEqualTo(100000d);
		assertThat(v1.getMinor()).isEqualTo(100000d);
		assertThat(v1.getPatch()).isEqualTo(100000d);
	}
	
	@Test
	public void missingPatchShouldDefaultToZeroTest() {
		Version v1 = new Version("100000.100000");
		
		assertThat(v1.getMajor()).isEqualTo(100000d);
		assertThat(v1.getMinor()).isEqualTo(100000d);
		assertThat(v1.getPatch()).isEqualTo(0d);
	}
	
	@Test
	public void missingPartsShouldDefaultToZero() {
		Version v1 = new Version("100000");
		
		assertThat(v1.getMajor()).isEqualTo(100000d);
		assertThat(v1.getMinor()).isEqualTo(0d);
		assertThat(v1.getPatch()).isEqualTo(0d);
	}
	
	@Test
	public void nonNumericCharactersTest() {
		Version v1 = new Version("v1.2a.3b");
		
		assertThat(v1.getMajor()).isEqualTo(0d);
		assertThat(v1.getMinor()).isEqualTo(0d);
		assertThat(v1.getPatch()).isEqualTo(0d);
	}
	
	@Test
	public void nullVersionTest() {
		try {
			new Version(null);
			failBecauseExceptionWasNotThrown(VersionNotValidException.class);
		} catch(Exception e) {
			assertThat(e)
				.isInstanceOf(VersionNotValidException.class)
				.hasMessage("A valid version is required")
				.hasNoCause();
		}
	}
	
	@Test
	public void emptyVersionTest() {
		try {
			new Version(" ");
			failBecauseExceptionWasNotThrown(VersionNotValidException.class);
		} catch(Exception e) {
			assertThat(e)
				.isInstanceOf(VersionNotValidException.class)
				.hasMessage("A valid version is required")
				.hasNoCause();
		}
	}
	
	@Test
	public void maxValueVersionTest() {
		try {
			new Version("100001.1.1");
			failBecauseExceptionWasNotThrown(VersionNotValidException.class);
		} catch(Exception e) {
			assertThat(e)
				.isInstanceOf(VersionNotValidException.class)
				.hasMessage("Major, minor and patch values cannot be larger than 100000.0")
				.hasNoCause();
		}
	}
	
	@Test
	public void comparatorTest() {
		Version v1 = new Version("1.0.0");
		Version v2 = new Version("0.095.3");
		Version v3 = new Version("10.0.1");
		Version v4 = new Version("0.0.1");
		
		List<Version> versions = new ArrayList<Version>();
		versions.add(v1);
		versions.add(v2);
		versions.add(v3);
		versions.add(v4);
		
		versions.sort(new VersionComparator());
		
		assertThat(versions.get(0).getVersion()).isEqualTo("0.0.1");
		assertThat(versions.get(1).getVersion()).isEqualTo("0.095.3");
		assertThat(versions.get(2).getVersion()).isEqualTo("1.0.0");
		assertThat(versions.get(3).getVersion()).isEqualTo("10.0.1");
	}
}
