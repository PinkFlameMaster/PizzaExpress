package com.service;

import baseUnitTest.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityServiceTest extends BaseUnitTest {
    @Autowired
    private ActivityService service;

    @Test
    public void testGetAllActivities()
    {
        Assert.assertNotNull(service.getAllActivities());
    }
}
