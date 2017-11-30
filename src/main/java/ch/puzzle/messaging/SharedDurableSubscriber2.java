package ch.puzzle.messaging;


import org.jboss.ejb3.annotation.ResourceAdapter;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@ResourceAdapter("remote-artemis") // name of the local pooled-connection-factory to be used
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Topic1"),
                @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
                @ActivationConfigProperty(propertyName = "clientId", propertyValue = "subscriber-2"),
                @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "topicSubscription2"),
                @ActivationConfigProperty(propertyName = "shareSubscriptions", propertyValue = "true")
        }
)
public class SharedDurableSubscriber2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Message received for subscriber-2!" + message);
    }
}
