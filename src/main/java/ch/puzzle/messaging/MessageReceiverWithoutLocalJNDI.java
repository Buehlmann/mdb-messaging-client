package ch.puzzle.messaging;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

/**
 * It is not necessary that MyQueue is contained in the local JNDI. The Queue will by looked up remotely on the message
 * broker.
 */
@ResourceAdapter("remote-artemis") // name of the local pooled-connection-factory to be used
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "MyRemoteQueue")
})
public class MessageReceiverWithoutLocalJNDI implements MessageListener {

    @Inject
    @JMSConnectionFactory("java:/jms/RemoteArtemisConnectionFactory")
    private JMSContext context; // used to send a message

    public void onMessage(Message message) {
        System.out.println("Got Message: " + message.toString());
    }
}
