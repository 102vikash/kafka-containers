package com.consumer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConsumerApplicationTests.class,
})
public class TestSuites {
        @BeforeClass
        public static void start() {
            System.out.println("Hii");
            System.out.println("Bye");
        }

        @AfterClass
        public static void end() {
            System.out.println("end hi");
            System.out.println("end bye");
        }
}
