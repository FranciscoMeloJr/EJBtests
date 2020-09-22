package org.sum;

import javax.ejb.Remote;
@Remote
public interface SumRemote 
{
	int add(int a, int b);
}