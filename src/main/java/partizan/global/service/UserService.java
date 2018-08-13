package partizan.global.service;

import partizan.global.model.Data;
import partizan.global.model.Operation;
import partizan.global.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String DESTINATION_SERVER = "destination.server";
    private static final String DESTINATION_CLIENT = "destination.client";

    @Autowired
    private JmsTemplate jmsTemplate;

    public Data getAllUsers() {
        Data outputData = new Data();
        outputData.setOperation(Operation.RETRIEVE);
        jmsTemplate.convertAndSend(DESTINATION_SERVER, outputData);
        return (Data) jmsTemplate.receiveAndConvert(DESTINATION_CLIENT);
    }

    public Data saveUser(User user) {
        Data outputData = new Data();
        outputData.addUser(user);
        outputData.setOperation(Operation.SAVE);
        jmsTemplate.convertAndSend(DESTINATION_SERVER, outputData);
        return (Data) jmsTemplate.receiveAndConvert(DESTINATION_CLIENT);
    }

    public Data deleteUserById(Long id) {
        Data outputData = new Data();
        outputData.setOperation(Operation.DELETE);
        User user = new User();
        user.setId(id);
        outputData.addUser(user);
        jmsTemplate.convertAndSend(DESTINATION_SERVER, outputData);
        return (Data) jmsTemplate.receiveAndConvert(DESTINATION_CLIENT);
    }
}
