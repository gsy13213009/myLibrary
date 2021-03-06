package com.gsy.guosiyi.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.gsy.guosiyi.globale.GloableConfig.sContext;

/**
 * SharedPrefrencens工具类，可以保存Object对象
 *
 * @author gsy
 * @date 2016/11/7 16:04
 * 修改代码时别忘了修改相应的注释
 */
public class GsySPUtils {
	/**
	 * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
	 * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
	 * @param object
	 *
	 * @return String
	 */
	private static String Object2String(Object object) {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
			objectOutputStream.close();
			return string;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用Base64解密String，返回Object对象
	 * @param objectString
	 *
	 * @return
	 */
	private static Object String2Object(String objectString) {
		byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object object = objectInputStream.readObject();
			objectInputStream.close();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 使用SharedPrefrence保存对象
	 * @param fileKey		储存文件的key
	 * @param key			储存对象的key
	 * @param saveObject	储存的对象
	 */
	public static void save(String fileKey, String key, Object saveObject) {
		SharedPreferences sharedPreferences = sContext.getApplicationContext().getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		String string = Object2String(saveObject);
		editor.putString(key, string);
		editor.commit();
	}

	/**
	 * 获取SharedPrefrence保存的对象
	 * @param fileKey	储存文件的key
	 * @param key		储存对象的key
	 *
	 * @return			对象Object
	 */
	public static Object get(String fileKey, String key) {
		SharedPreferences sharedPreferences = sContext.getApplicationContext().getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
		String string = sharedPreferences.getString(key, null);
		if (string != null) {
			Object object = String2Object(string);
			return object;
		} else {
			return null;
		}
	}
}
