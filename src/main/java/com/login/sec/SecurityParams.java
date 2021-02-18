package com.login.sec;

public interface SecurityParams {
	public static final String HEADER_NAME="Authorization";
	public static final String SECRET="...";
	public static final long EXPERATIOn=10*24*3600;
	public static final String HEADER_PREFIX="Bearer ";

}
