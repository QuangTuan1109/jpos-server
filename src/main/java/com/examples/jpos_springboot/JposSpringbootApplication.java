package com.examples.jpos_springboot;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.q2.Q2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JposSpringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JposSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Q2 q2 = new Q2();
		q2.start();
	}

	public void unpackMessage(byte[] message) throws Exception {
		ISOPackager packager = new GenericPackager("cfg/packager/iso87ascii-binary-bitmap.xml");

		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);

		isoMsg.unpack(message);

		System.out.println("Unpacked Message:");
		for (int i = 0; i <= isoMsg.getMaxField(); i++) {
			if (isoMsg.hasField(i)) {
				System.out.println(i + ": " + isoMsg.getString(i));
			}
		}
	}
}
