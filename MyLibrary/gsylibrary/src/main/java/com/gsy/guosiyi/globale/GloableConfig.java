package com.gsy.guosiyi.globale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * @author gsy
 * @date 2016/11/17 21:40
 * 修改代码时别忘了修改相应的注释
 */
public class GloableConfig extends Activity{
	public static Context sContext;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sContext = GloableConfig.this;
	}
}
