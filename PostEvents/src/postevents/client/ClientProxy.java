package postevents.client;

import postevents.common.CommonProxy;

public class ClientProxy extends CommonProxy {
	@Override
	public void init() {
		// We do nothing client-side.
	}

	@Override
	public boolean isServer() {
		return false;
	}

	@Override
	public boolean isClient() {
		return true;
	}
}
