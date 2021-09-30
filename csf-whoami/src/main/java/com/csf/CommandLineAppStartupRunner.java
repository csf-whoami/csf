package com.csf;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.csf.aop.DestinationClass;
import com.csf.aop.SourceClass;
import com.csf.database.adapter.BaseAdapter;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        SourceClass source = new SourceClass();
        source.setId(10L);
        source.setAge(35);
        source.setName("Test user");
        source.setCreatedAt(new Date());
        source.setDeletedAt(null);
        source.setTestProp(11111L);

        DestinationClass copySource = BaseAdapter.copyFromObject(source, DestinationClass.class);
        if (copySource != null) {
            System.out.println("Copy source - ID: " + copySource.getId());
            System.out.println("Copy source - Property: " + copySource.getMyProperties());
        }
    }
}
