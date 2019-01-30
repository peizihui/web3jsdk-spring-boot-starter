package org.fisco.bcos.solidity;

import org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SolidityFunctionWrapperGeneratorTest  {

    private String binFile =  new ClassPathResource("solidity/greeter/build/Greeter.bin").getFile().getAbsolutePath();
    private String abiFile =  new ClassPathResource("solidity/greeter/build/Greeter.abi").getFile().getAbsolutePath();
    protected String tempDirPath =  new File("src/test/java/").getAbsolutePath();
    protected String packageName =  "org.fisco.bcos.solidity";


    @Test
    public void generateClassFromABIForHelloWorld() throws Exception {

        String binFile1 =  new ClassPathResource("solidity/HelloWorld.bin").getFile().getAbsolutePath();
        String abiFile1 =  new ClassPathResource("solidity/HelloWorld.abi").getFile().getAbsolutePath();
        SolidityFunctionWrapperGenerator.main(Arrays.asList(
                binFile1,
                abiFile1,
                "-p", packageName,
                "-o", tempDirPath
        ).toArray(new String[0]));
    }

    @Test
    public void generateClassFromABIForGreeter() throws Exception {
        SolidityFunctionWrapperGenerator.main(Arrays.asList(
                binFile,
                abiFile,
                "-p", packageName,
                "-o", tempDirPath
        ).toArray(new String[0]));
    }

    public SolidityFunctionWrapperGeneratorTest() throws IOException {
    }

}
