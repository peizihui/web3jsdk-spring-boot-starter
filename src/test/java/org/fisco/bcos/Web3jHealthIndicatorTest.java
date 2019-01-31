package org.fisco.bcos;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Web3jHealthIndicatorTest {

    @Autowired
    Web3j web3j;

    @Test
    public void  getEthBlockNumber() throws IOException {
        BigInteger number =web3j.ethBlockNumber().send().getBlockNumber();
        assertTrue(number.intValue()>0);
        System.out.println(number);
    }


}