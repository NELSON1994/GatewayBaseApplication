package com.gatewayDemo2.GatewayOwnSettingProject;

import com.gatewayDemo2.GatewayOwnSettingProject.packager.TransportPackager;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.NACChannel;

import java.io.IOException;

public class TestClass {

    public static void main(String[] args) {
            try{
                byte[] TPDU = new byte[5];
                TPDU[0] = 60;
                TPDU[1] = 00;
                TPDU[2] = 00;
                TPDU[3] = 00;
                TPDU[4] = 00;
                String server;

                ISOPackager packager=new TransportPackager();//packager
                server = "127.0.0.1"; // localhost

                //server = "41.215.130.247"; //remote  public address
//              server = "192.168.1.124"; //remote private address
               // ISOChannel channel = new NACChannel(server,  9629,  packager, null); // goto server.xml  remove header
                ISOChannel channel = new NACChannel(server,  9629,  packager, TPDU);
                new ISOMsg();
                ISOMsg message = echoTest();

                message.setPackager(packager);
                channel.connect();
                channel.send(message);

                ISOMsg response=channel.receive();

            }
            catch (Exception e){
                e.printStackTrace();
            }

    }

    private static ISOMsg echoTest() throws ISOException{
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setMTI("0800");
        isoMsg.set(3,"001100");
        isoMsg.set(11,"000001");
        isoMsg.set(41,"45789021");
        isoMsg.set(42,"421456908756435");
        isoMsg.set(47,"020002AB021004FGHJ022006YGHBNL");
        isoMsg.set(70,"301");
        isoMsg.set(72,"3");
        return isoMsg;
    }
}
