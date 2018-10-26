这个是FISCO-BCOS web3sdk的spring boot版本demo;如果您对wec3sdk项目还不太了解，请查看https://github.com/FISCO-BCOS/web3sdk
web3sdk-spring-boot-starter 您可以拉代码直接在此项目开发。或者使用 gradle clean shadowJar 在build/libs下生成的web3sdk-spring-boot-starter-1.2.4-all.jar引入您的项目，这样您不必引入spring boot 相关依赖；
这样你可以直接注入web3j
@Autowired
private Web3j web3j;
当然您需要配置 application.yml。配置参考FISCO-BCOS web3sdk项目。
项目fisco-sloc文件下的 fisco-sloc.exe(windows环境下)可以帮你把sol文件转换成abi文件。
参考命令（git命令行）：  ./fisco-solc.exe HelloWorld.sol --bin --abi -o output。
项目中SolidityFunctionWrapperGeneratorTest 测试类可以帮你生成把abi文件转换成相应Java类。
