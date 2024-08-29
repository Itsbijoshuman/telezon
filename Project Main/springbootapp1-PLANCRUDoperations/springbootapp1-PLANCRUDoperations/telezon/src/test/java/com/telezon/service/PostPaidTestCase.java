package com.telezon.service;

import com.telezon.dao.PostpaidDao;
import com.telezon.model.Postpaid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostPaidTestCase {

    @Mock
    private PostpaidDao postpaidDao;

    @InjectMocks
    private PostpaidService postpaidService;

    @Test
    void testGetPostpaidPlans() {
        Postpaid postpaid1 = new Postpaid();
        Postpaid postpaid2 = new Postpaid();
        when(postpaidDao.findAll()).thenReturn(Arrays.asList(postpaid1, postpaid2));

        List<Postpaid> postpaidPlans = postpaidService.getPostpaidPlans();
        assertEquals(2, postpaidPlans.size());
    }

    @Test
    void testAddPostpaidPlan() {
        Postpaid postpaid = new Postpaid();
        postpaidService.addPostpaidPlan(postpaid);

        verify(postpaidDao, times(1)).save(postpaid);
    }
}
