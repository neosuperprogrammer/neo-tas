package com.flowgrammer.neo.tas;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.InetSocketAddress;

/**
 * Created by neox on 10/22/15.
 */
public class Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private ClassPathXmlApplicationContext applicationContext;

    private static final int PORT = 8080;

    public Main() {
        String config = "conf/tas-config.xml";
        applicationContext = new ClassPathXmlApplicationContext(new String[]{config}, true);

        TasHandlerChain handlerChain = applicationContext.getBean("tasHandlerChain", TasHandlerChain.class);
        handlerChain.printTest();
    }

    public static void main(String[] args) throws Exception {

        LOGGER.info("start");

        new Main();

        SocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setReuseAddress( true );
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();




        acceptor.setHandler(new EchoProtocolHandler());
        acceptor.bind(new InetSocketAddress(PORT));

        System.out.println("Listening on port " + PORT);

        for (;;) {
            System.out.println("R: " + acceptor.getStatistics().getReadBytesThroughput() +
                    ", W: " + acceptor.getStatistics().getWrittenBytesThroughput());
            Thread.sleep(3000);
        }
    }
}
