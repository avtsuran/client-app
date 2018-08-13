package partizan.global.service;

import partizan.global.model.Data;
import partizan.global.model.Operation;
import partizan.global.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final int SIZE = 1;
    private static final String NAME = "John Doe";
    private static final String PHONE = "38099001100";
    private static final String EMAIL = "johndoe@gmail.com";
    private static final String DESTINATION_SERVER = "destination.server";
    private static final String DESTINATION_CLIENT = "destination.client";

    private Data inputData;
    private Data outputData;
    private User user;
    @Captor
    private ArgumentCaptor<Data> captor;
    @Mock
    private JmsTemplate jmsTemplate;
    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        user = createUser();
        inputData = new Data();
        inputData.addUser(user);
        outputData = new Data();
        outputData.setOperation(Operation.RETRIEVE);
    }

    @Test
    public void shouldReturnAllUsers() {
        when(jmsTemplate.receiveAndConvert(DESTINATION_CLIENT)).thenReturn(outputData);

        Data data = userService.getAllUsers();

        assertEquals(outputData, data);
        verify(jmsTemplate).convertAndSend(eq(DESTINATION_SERVER), captor.capture());
        Data value = captor.getValue();
        assertEquals(Operation.RETRIEVE, value.getOperation());
    }

    @Test
    public void shouldSaveUser() {
        when(jmsTemplate.receiveAndConvert(DESTINATION_CLIENT)).thenReturn(outputData);
        Data data = userService.saveUser(user);

        assertEquals(outputData, data);
        verify(jmsTemplate).convertAndSend(eq(DESTINATION_SERVER), captor.capture());
        Data value = captor.getValue();
        assertEquals(Operation.SAVE, value.getOperation());
        assertEquals(SIZE, value.getUsers().size());
    }

    @Test
    public void shouldDeleteUserById() {
        when(jmsTemplate.receiveAndConvert(DESTINATION_CLIENT)).thenReturn(outputData);
        Data data = userService.deleteUserById(user.getId());

        assertEquals(outputData, data);
        verify(jmsTemplate).convertAndSend(eq(DESTINATION_SERVER), captor.capture());
        Data value = captor.getValue();
        assertEquals(SIZE, value.getUsers().size());
    }

    private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName(NAME);
        user.setPhone(PHONE);
        user.setEmail(EMAIL);
        return user;
    }
}
