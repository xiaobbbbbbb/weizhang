package com.ecarinfo.traffic.api.main;

import com.ecarinfo.common.utils.ServerUtils;

public class Shutdown {
	
	public static void main(String[] args) {
		ServerUtils.applyToShutdownServer("ec-traffic-engine-api");
	}
}
