package org.fisco.bcos;


import org.fisco.bcos.Application;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.methods.response.EthBlock;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;

import static java.lang.System.exit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BlockTest {

    private static BigInteger gasPrice = new BigInteger("300000000");
    private static BigInteger gasLimit = new BigInteger("300000000");
    private Credentials credentials;

    @Autowired
    Web3j web3j;

    @Before
    public void setUp() throws Exception {
        credentials = GenCredential.create("b83261efa42895c38c6c2364ca878f43e77f3cddbc922bf57d0d48070f79feb6");
        if (credentials == null) {
            throw new Exception("create Credentials failed");
        }
    }

//    @After
//    public void tearDown() {
//        exit(1);
//    }

    @Test
    public void getBlockNumber() throws IOException {
        EthBlock.Block block=  web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(new BigInteger("0")), true).send().getBlock();
       assertEquals( block.getNonce(),new BigInteger("0"));
        assertTrue( block.getNumber().intValue()>=0);
    }
    @Test
    public void testDeployAndInvokeContract() throws Exception {
        Ok okDemo = Ok.deploy(web3j, credentials, gasPrice, gasLimit).send();
        if (okDemo != null) {
            System.out.println("####contract address is: " + okDemo.getContractAddress());
            TransactionReceipt receipt = okDemo.trans(new BigInteger("4")).send();

            assertTrue( receipt.getBlockNumber().intValue()>0);
            assertTrue( receipt.getTransactionIndex().intValue()>=0);
            assertTrue( receipt.getGasUsed().intValue()>0);
        }
    }
}
