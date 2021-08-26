package org.Encheres.Servlets;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class Shared {

	public static final String IMAGES_FOLDER = "/aPath/Images";

	public static final Key SECRET_KEY = new SecretKeySpec("Une clé secrête : chut !!!!!!!".getBytes(), "AES");
}
