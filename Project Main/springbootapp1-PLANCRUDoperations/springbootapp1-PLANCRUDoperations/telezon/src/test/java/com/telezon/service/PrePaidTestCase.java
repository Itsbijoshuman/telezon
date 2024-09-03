package com.telezon.service;

import com.telezon.dao.PrepaidDao;
import com.telezon.model.Prepaid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrePaidTestCase {

    @Mock
    private PrepaidDao prepaidDao;

    @InjectMocks
    private PrepaidService prepaidService;

    @Test
    void testGetPrepaidPlans() {
        Prepaid prepaid1 = new Prepaid();
        Prepaid prepaid2 = new Prepaid();
        when(prepaidDao.findAll()).thenReturn(Arrays.asList(prepaid1, prepaid2));

        List<Prepaid> prepaidPlans = prepaidService.getPrepaidPlans();
        assertEquals(2, prepaidPlans.size());
    }

    @Test
    void testGetPrepaidPlanById() {
        Prepaid prepaid = new Prepaid();
        when(prepaidDao.findById(1)).thenReturn(Optional.of(prepaid));

        Prepaid result = prepaidService.getPrepaidPlanById(1);
        assertNotNull(result);
        assertEquals(prepaid, result);
    }

    @Test
    void testGetPrepaidPlanByIdNotFound() {
        when(prepaidDao.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            prepaidService.getPrepaidPlanById(1);
        });
        assertEquals("Prepaid Plan not found", thrown.getMessage());
    }

    @Test
    void testAddPrepaidPlan() {
        Prepaid prepaid = new Prepaid();
        prepaidService.addPrepaidPlan(prepaid);

        verify(prepaidDao, times(1)).save(prepaid);
    }
}
