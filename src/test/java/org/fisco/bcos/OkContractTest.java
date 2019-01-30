package org.fisco.bcos;

import org.fisco.bcos.channel.test.Ok;
import org.fisco.bcos.contract.tools.ToolConf;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OkContractTest {
    @Autowired
    ToolConf toolConf;
    @Autowired
    Web3j web3j;

    private static BigInteger gasPrice = new BigInteger("300000000");
    private static BigInteger gasLimit = new BigInteger("300000000");
    private static BigInteger initialWeiValue = new BigInteger("0");

    @Test
    public void testOk() throws Exception {


        Credentials credentials = GenCredential.create(toolConf.getPrivKey());
        if (credentials != null) {
            System.out.println("####create credential succ, begin deploy contract");

            	Ok okDemo = Ok.deploy(web3j, credentials, gasPrice, gasLimit, initialWeiValue).get(60000, TimeUnit.MILLISECONDS);
            if (okDemo != null) {
                System.out.println("####contract address is: " + okDemo.getContractAddress());
                System.out.println("####contract  is: " + okDemo.isValid());
                TransactionReceipt receipt = okDemo.trans(new Uint256(4)).get(60000, TimeUnit.MILLISECONDS);
                System.out.println("###callback trans success");
                Uint256 toBalance = okDemo.get().get(60000, TimeUnit.MILLISECONDS);
                assertNotNull(toBalance.getValue());
            }
        }
    }
}
