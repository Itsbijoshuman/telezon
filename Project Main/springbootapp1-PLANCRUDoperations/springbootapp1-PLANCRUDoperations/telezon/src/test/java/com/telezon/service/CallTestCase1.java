
package com.telezon.service;

import com.telezon.dao.CallDao;
import com.telezon.dao.CustomerDao;
import com.telezon.model.Call;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CallTestCase1 {

    @Mock
    private CallDao callDao;

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CallService callService;

    @Test
    void testGetAllCalls() {
        Call call1 = new Call();
        Call call2 = new Call();
        when(callDao.findAll()).thenReturn(Arrays.asList(call1, call2));

        assertEquals(2, callService.getAllCalls().size());
    }

    @Test
    void testGetCallById() {
        Call call = new Call();
        when(callDao.findById(1)).thenReturn(Optional.of(call));

        assertTrue(callService.getCallById(1).isPresent());
    }

    @Test
    void testSaveCall() {
        Call call = new Call();
        callService.saveCall(call);

        verify(callDao, times(1)).save(call);
    }

    @Test
    void testUpdateCall() {
        Call call = new Call();
        callService.updateCall(call);

        verify(callDao, times(1)).save(call);
    }

    @Test
    void testDeleteCall() {
        callService.deleteCall(1);

        verify(callDao, times(1)).deleteById(1);
    }
}



