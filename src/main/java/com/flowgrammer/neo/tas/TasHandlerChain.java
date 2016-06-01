package com.flowgrammer.neo.tas;

import org.apache.mina.handler.chain.IoHandlerChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by neox on 10/23/15.
 */
public class TasHandlerChain extends IoHandlerChain{

    private static Logger LOGGER = LoggerFactory.getLogger(TasHandlerChain.class);

    public void printTest() {
        LOGGER.info("printTest");

    }
}
