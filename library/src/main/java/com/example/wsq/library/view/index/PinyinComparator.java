package com.example.wsq.library.view.index;

import com.example.wsq.library.bean.ContactBean;

import java.util.Comparator;

public class PinyinComparator implements Comparator<ContactBean> {

	public int compare(ContactBean o1, ContactBean o2) {
		if (o1.getPinyin().equals("@")
				|| o2.getPinyin().equals("#")) {
			return 1;
		} else if (o1.getPinyin().equals("#")
				|| o2.getPinyin().equals("@")) {
			return -1;
		} else {
			return o1.getPinyin().compareTo(o2.getPinyin());
		}
	}

}
