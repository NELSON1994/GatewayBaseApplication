package com.gatewayDemo2.GatewayOwnSettingProject;

import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayOwnSettingProjectApplication {

	private static void startJPosServer(){
		JPosServer.getInstance();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayOwnSettingProjectApplication.class, args);
		startJPosServer();


		System.out.println("~~~~~~~~~~~~~~^^^^^^^^^^~~~~~~~~~~~~GATEWAY HAS STARTED SUCCESSFULLY~~~~~~~~~~~~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^");
	}





}

class JPosServer {
	private static volatile JPosServer instance = null;
	static void getInstance(){
		if (instance == null) {
			synchronized (JPosServer.class){
				if (instance == null) {
					instance = new JPosServer();
				}
			}
		}
	}
	private JPosServer(){
		try {
			new Q2("config/jpos").start();
			ISOUtil.sleep(2000);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
