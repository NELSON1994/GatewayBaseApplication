package com.gatewayDemo2.GatewayOwnSettingProject.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextBridge implements SpringContextBridgeTemplate, ApplicationContextAware {
    private static ApplicationContext applicationContext;


    /*

        @Autowired
    private AllTransactionServiceTemplate allTransactionServiceTemplate;

    @Override
    public AllTransactionServiceTemplate getAllTransactionServiceTemplate(){
    return allTransactionServiceTemplate;
}

     */


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextBridge.applicationContext = applicationContext;
    }

    public static SpringContextBridgeTemplate services(){
        return applicationContext.getBean(SpringContextBridgeTemplate.class);
    }
}
