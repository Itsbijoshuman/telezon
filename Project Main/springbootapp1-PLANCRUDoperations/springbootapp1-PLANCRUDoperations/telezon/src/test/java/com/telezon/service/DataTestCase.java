package com.telezon.service;

import com.telezon.dao.DataDao;
import com.telezon.model.Data;
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
public class DataTestCase {

    @Mock
    private DataDao dataDao;

    @InjectMocks
    private DataService dataService;

    @Test
    void testGetAllData() {
        Data data1 = new Data();
        Data data2 = new Data();
        when(dataDao.findAll()).thenReturn(Arrays.asList(data1, data2));

        assertEquals(2, dataService.getAllData().size());
    }

    @Test
    void testGetDataById() {
        Data data = new Data();
        when(dataDao.findById(1)).thenReturn(Optional.of(data));

        Optional<Data> result = dataService.getDataById(1);
        assertTrue(result.isPresent());
        assertEquals(data, result.get());
    }

    @Test
    void testSaveData() {
        Data data = new Data();
        dataService.saveData(data);

        verify(dataDao, times(1)).save(data);
    }

    @Test
    void testUpdateData() {
        Data data = new Data();
        dataService.updateData(data);

        verify(dataDao, times(1)).save(data);
    }

    @Test
    void testDeleteData() {
        int id = 1;
        dataService.deleteData(id);

        verify(dataDao, times(1)).deleteById(id);
    }
}
