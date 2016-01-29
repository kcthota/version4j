package com.kcthota.version4j.models;

import com.kcthota.version4j.exceptions.VersionNotValidException;

/**
 * Version Model
 * @author kc
 *
 */
public class Version implements Comparable<Version> {

	private final Double MAX_VERSION_PART = 100000d;

	private String version = "0.0.0";

	private Double major = 0d;

	private Double minor = 0d;

	private Double patch = 0d;

	private Double scalarValue = 0d;
	

	/**
	 * Builds version object from passed String. Throws VersionNotValidException if not parseable.
	 * @param version
	 */
	public Version(String version) {

		if (version != null && version.trim().length() > 0) {
			this.version = version;
			String[] versionParts = version.split("\\.");
			this.major = parsePart(versionParts[0]);

			if (versionParts.length > 1) {
				this.minor = parsePart(versionParts[1]);

				if (versionParts.length > 2) {
					this.patch = parsePart(versionParts[2]);
				}
			}

			this.scalarValue = calculateScalarValue();

		} else {
			throw new VersionNotValidException("A valid version is required");
		}
	}

	private Double calculateScalarValue() {
		return major * Math.pow(MAX_VERSION_PART, 2d) + minor
				* MAX_VERSION_PART + patch;
	}

	private Double parsePart(String part) {
		//crop any non-numeric characters
		part  = part.replaceAll("[^\\d]", "");
		Double val = 0d;
		try {
			val = Double.valueOf(part);
		} catch (NumberFormatException e) {
			// do nothing and default to 0
		}

		if (val.compareTo(MAX_VERSION_PART) > 0) {
			throw new VersionNotValidException(
					"Major, minor and patch values cannot be larger than "
							+ MAX_VERSION_PART);
		}
		return val;
	}

	/**
	 * Get the version major value
	 * @return
	 */
	public Double getMajor() {
		return major;
	}

	/**
	 * Gets the version minor value
	 * @return
	 */
	public Double getMinor() {
		return minor;
	}

	/**
	 * Gets the version patch value
	 * @return
	 */
	public Double getPatch() {
		return patch;
	}

	/**
	 * Gets the unique scalar value for a version
	 * @return
	 */
	public Double getScalarValue() {
		return scalarValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((scalarValue == null) ? 0 : scalarValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (scalarValue == null) {
			if (other.scalarValue != null)
				return false;
		} else if (!scalarValue.equals(other.scalarValue))
			return false;
		return true;
	}
	
	/**
	 * Returns the String representation of the Version
	 */
	@Override
	public String toString() {
		return version;
	}

	/**
	 * Compares current Version with passed Version.
	 * Returns 1 if current version is greater than passed Version
	 * Returns 0 is equal
	 * Returns -1 otherwise
	 */
	public int compareTo(Version that) {
		
		return this.getScalarValue().compareTo(that.getScalarValue());
	}
	
	/**
	 * Checks if current Version object is greater than the passed Version object
	 * @param that
	 * @return
	 */
	public boolean greaterThan(Version that) {
		return this.compareTo(that) > 0 ? true : false;
	}
	
	/**
	 * Checks if current Version object is less than the passed Version object
	 * @param that
	 * @return
	 */
	public boolean lessThan(Version that) {
		return this.compareTo(that) < 0 ? true : false;
	}
	
	/**
	 * Parses the String version and returns a Version object. 
	 * Throws VersionNotValidException if String is not parseable.
	 * @param version
	 * @return
	 */
	public static Version parseVersion(String version) {
		return new Version(version);
	}
	
	/**
	 * Attempts to parse String to Version object. Returns true if parseable, else returns false.
	 * @param version
	 * @return
	 */
	public static boolean tryParse(String version) {
		try {
			new Version(version);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
