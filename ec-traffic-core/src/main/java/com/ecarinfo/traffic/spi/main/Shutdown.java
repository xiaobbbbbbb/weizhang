package com.ecarinfo.traffic.spi.main;

import com.ecarinfo.common.utils.ServerUtils;

public class Shutdown {
	
	public static void main(String[] args) {
		ServerUtils.applyToShutdownServer("ec-traffic-engine-core");
	}
}
