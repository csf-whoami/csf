package com.csf.whoami.database.adapter;

public interface BaseAdapter<K, V> {
	V modelToVo(K model);
	K voToModel(V vo);
}
