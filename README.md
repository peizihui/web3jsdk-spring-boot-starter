## 这个是FISCO-BCOS web3sdk的spring boot版本demo;

- 如果您对wec3sdk项目还不太了解，请查看[https://github.com/FISCO-BCOS/web3sdk](https://github.com/FISCO-BCOS/web3sdk)。

- 您可以拉代码直接在此项目开发(删除test文件下的测试案例即可)。我们在test下面提供基本测试案例，保证你能成功调用智能合约。

    开始开发前，您需要配置 application.yml。配置类似于web3sdk项目配置。只需把application.xml修改成application.yml。
    配置如下图：
    ![配置](./user/images/springboot.png)
    配置参考FISCO-BCOS web3sdk项目[https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/web3sdk/config_web3sdk.html](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/web3sdk/config_web3sdk.html)。

    这样您可以直接在你的项目里注入web3j
 
    ```@Autowired```

    ``` private Web3j web3j```;

- 项目中SolidityFunctionWrapperGeneratorTest 测试类可以帮你生成把abi文件转换成相应Java类。
