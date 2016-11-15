package ch.puzzle.messaging;

import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MyQueue must be in the local JNDI of the application server.
 */
@ResourceAdapter("remote-artemis") // name of the local pooled-connection-factory to be used
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "MyLocalQueue")
})
public class MessageReceiverWithLocalJNDI implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Got Message: " + message.toString());
    }
}
