package partizan.global.controller;

import partizan.global.model.Data;
import partizan.global.model.Operation;
import partizan.global.model.User;
import partizan.global.service.UserService;
import partizan.global.service.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final long ID = 1L;
    private static final String REDIRECT = "redirect:/";
    private static final String PHONE_BOOK = "phone-book";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ADD_USER = "add-user";
    private static final String VALID_NAME = "John Doe";
    private static final String VALID_PHONE = "38099001100";
    private static final String VALID_EMAIL = "johndoe@gmail.com";


    private Data outputData;
    private Data inputData;
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private UserValidator validator;
    @Mock
    private BindingResult result;
    @Mock
    private RedirectAttributes redirect;
    @Mock
    private View mockView;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(userController)
                .setSingleView(mockView)
                .build();
        outputData = new Data();
        outputData.setOperation(Operation.RETRIEVE);
        inputData = new Data();
        inputData.setStatus("status");
    }

    @Test
    public void shouldReturnPhoneBook() throws Exception {
        when(userService.getAllUsers()).thenReturn(outputData);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(PHONE_BOOK));
    }

    @Test
    public void shouldReturnAddUser() throws Exception {
        mockMvc.perform(get("/add-user"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADD_USER));
    }

    @Test
    public void shouldRedirectIfInputDataIsValid() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(inputData);
        mockMvc.perform(post("/add-user")
                .param(NAME, VALID_NAME)
                .param(PHONE, VALID_PHONE)
                .param(EMAIL, VALID_EMAIL))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name(REDIRECT));
    }

    @Test
    public void shouldRedirectAndDeleteUser() throws Exception {
        when(userService.deleteUserById(ID)).thenReturn(inputData);
        mockMvc.perform(get("/delete/" + ID))
                .andExpect(status().isOk())
                .andExpect(view().name(REDIRECT));
    }
}
