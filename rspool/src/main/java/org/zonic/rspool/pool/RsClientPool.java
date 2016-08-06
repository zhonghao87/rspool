package org.zonic.rspool.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.zonic.rspool.client.RsClient;

public class RsClientPool extends GenericObjectPool<RsClient>{

	public RsClientPool(GenericObjectPoolConfig config) {
		super(new RsClientPoolFactory(), config);
		
		//use FIFO
		setLifo(false);
	}
	
    @Override
    public RsClient borrowObject() throws Exception {
    	
    	RsClient client = super.borrowObject();
    	
    	/*
    	 * since it is FIFO, may try multiple times until
    	 * a ready RsClient is borrowed.
    	 */
    	while(!client.isReady()) {
    		returnObject(client);
    		client = super.borrowObject();
    	}
    	
    	return client;
    }

	
}
