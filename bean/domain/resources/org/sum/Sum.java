package org.sum;

import javax.ejb.Stateless;

@Stateless
public class Sum implements SumRemote {
	
    public int add(int var1, int var2) {
        return var1 + var2;
    }
}
