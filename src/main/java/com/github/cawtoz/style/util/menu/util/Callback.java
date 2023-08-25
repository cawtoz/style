package com.github.cawtoz.style.util.menu.util;

import java.io.Serializable;

public interface Callback<T> extends Serializable {
	
    void callback(T data);
}