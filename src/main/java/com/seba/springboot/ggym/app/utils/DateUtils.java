package com.seba.springboot.ggym.app.utils;

import java.util.Date;

public abstract class DateUtils {
	public static Date expirationDate() {
		return new Date(System.currentTimeMillis() + 1000 * 60 * 24);
	}
}
