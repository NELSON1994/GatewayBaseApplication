package com.gatewayDemo2.GatewayOwnSettingProject;

import org.jpos.iso.*;

import java.util.Date;

public class RequestHandler implements ISORequestListener {

    @Override
    public boolean process(ISOSource isoSource, ISOMsg isoMsg) {

        // AllTransactionServiceTemplate all = SpringContextBridgeService.services().getAllTransactionServiceTemplate();
        String now = ISODate.formatDate(new Date(), "YYYYMMddhhmm");
        isoMsg.set(12, now);//field 12
        try {
            switch (isoMsg.getMTI()) {
                case "0800":
                    isoMsg.set(3,"000000");
                    isoMsg.set(39,"00");
                    isoMsg.set(72,"success");
                    break;
                default:
                    isoMsg.set(39,"06");
                    isoMsg.set(72,"TRANSACTION NOT ALLOWED IN THE TERMINAL");

            }
            isoMsg.setResponseMTI();
            isoSource.send(isoMsg);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
