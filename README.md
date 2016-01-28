# version4j
Library to help compare major.minor.patch versions.

Implements the standard Comparable and Comparator Interfaces.

# Usage

Will soon be available on Maven Central.

# Examples

## Parse Version
Attempts to parse the String to Version Object. Throws VersionNotValidException if fails to parse the String.

Currently only support major.minor.patch format. And the value of any of these parts cannot be greater than 100000.

```
Version.parseVersion("1.1.2"); //parsed as 1.1.2

Version.parseVersion("1"); //parsed as 1.0.0

Version.parseVersion("1.2"); //parsed as 1.2.0

Version.parseVersion("1.2.3c"); //parsed as 1.2.3

Version.parseVersion("1.2.3c"); //parsed as 1.2.3

Version.parseVersion("1.2.3.4.5"); //parsed as 1.2.3

Version.parseVersion(" "); //throws VersionNotValidException

Version.parseVersion(null); //throws VersionNotValidException

Version.parseVersion("100001.0.0"); //throws VersionNotValidException

```

## Try Parse

Tries to parse the passed String. Returns true if successfully parses the String or returns false.

This method does not throw an exception.

```
Version.parseVersion("1.1.2"); //true

Version.parseVersion("1"); //true

Version.parseVersion("1.2"); //true

Version.parseVersion("1.2.3c"); //true

Version.parseVersion("1.2.3c"); //true

Version.parseVersion("1.2.3.4.5"); //true

Version.parseVersion(" "); //false

Version.parseVersion(null); //false

Version.parseVersion("100001.0.0"); //false

```

## CompareTo

Standard Comparable interface

```
Version v1 = new Version("1.2.3");
		
Version v2 = Version.parseVersion("1.2");
		
v1.compareTo(v2); //1

```

## Greater than

```
Version v1 = new Version("0.1.9");
		
Version v2 = new Version("0.0.9");
		
v1.greaterThan(v2); //true

```

## Less than

```
Version v1 = new Version("2.0.9");
		
Version v2 = new Version("10.0.1");
		
v1.lessThan(v2); //true

```

## Sort Versions

Sorts the versions in ascending order

```
Version v1 = new Version("2.0.0");
Version v2 = new Version("0.095.3");
Version v3 = new Version("10.0.1");
Version v4 = new Version("0.0.1");

List<Version> versions = new ArrayList<Version>();
versions.add(v1);
versions.add(v2);
versions.add(v3);
versions.add(v4);

versions.sort(new VersionComparator());

```

#License

MIT
