package org.zonic.rspool.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.zonic.rspool.client.RsClient;

public class RsClientPoolFactory extends BasePooledObjectFactory<RsClient> {

	@Override
	public RsClient create() throws Exception {
		return new RsClient();
	}

	@Override
	public PooledObject<RsClient> wrap(RsClient client) {
		return new DefaultPooledObject<RsClient>(client);
	}
	
    @Override
    public void destroyObject(PooledObject<RsClient> p) throws Exception  {
    	p.getObject().shutDown();
    }

}
