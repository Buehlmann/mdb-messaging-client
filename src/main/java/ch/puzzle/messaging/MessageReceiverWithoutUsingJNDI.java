package ch.puzzle.messaging;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

/**
 * It is not necessary that test.queue is contained in the local JNDI. The Queue will by looked up remotely on the message
 * broker.
 */
@ResourceAdapter("remote-artemis") // name of the local pooled-connection-factory to be used
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "test.queue"), // physical name of the destination on the message broker
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MessageReceiverWithoutUsingJNDI implements MessageListener {

    @Inject
    @JMSConnectionFactory("java:/jms/RemoteArtemisConnectionFactory")
    private JMSContext context; // used to send a message

    public void onMessage(Message message) {
        System.out.println("Received Message in MessageReceiverWithoutUsingJNDI:\n   " + message.toString());
    }
}
