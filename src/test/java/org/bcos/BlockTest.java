package org.bcos;

import org.bcos.channel.test.Ok;
import org.bcos.contract.tools.ToolConf;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.GenCredential;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.response.EthBlock;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BlockTest {

    private static BigInteger gasPrice = new BigInteger("300000000");
    private static BigInteger gasLimit = new BigInteger("300000000");
    private static BigInteger initialWeiValue = new BigInteger("0");
    private Credentials credentials;

    @Autowired
    ToolConf toolConf;
    @Autowired
    Web3j web3j;

    @Before
    public void setUp() throws Exception {
        credentials = GenCredential.create(toolConf.getPrivKey());
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
        EthBlock.Block block=  web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(new BigInteger("1")), true).send().getBlock();
       assertEquals( block.getNonce(),new BigInteger("0"));
        assertTrue( block.getNumber().intValue()>0);
    }
    @Test
    public void testDeployAndInvokeContract() throws Exception {
        Ok okDemo = Ok.deploy(web3j, credentials, gasPrice, gasLimit, initialWeiValue).get(60000, TimeUnit.MILLISECONDS);
        if (okDemo != null) {
            System.out.println("####contract address is: " + okDemo.getContractAddress());
            TransactionReceipt receipt = okDemo.trans(new Uint256(4)).get(60000, TimeUnit.MILLISECONDS);

            assertTrue( receipt.getBlockNumber().intValue()>0);
            assertTrue( receipt.getTransactionIndex().intValue()>=0);
            assertTrue( receipt.getGasUsed().intValue()>0);
            assertTrue( receipt.getCumulativeGasUsed().intValue()>0);

            Uint256 oldBalance = okDemo.get().get(60000, TimeUnit.MILLISECONDS);
             okDemo.trans(new Uint256(4)).get(60000, TimeUnit.MILLISECONDS);
            Uint256 newBalance = okDemo.get().get(60000, TimeUnit.MILLISECONDS);
            assertTrue(newBalance.getValue().intValue() == oldBalance.getValue().intValue() + 4);
            Thread.sleep(1000);
        }
    }
}
