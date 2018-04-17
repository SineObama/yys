## yys

网易手机游戏《阴阳师》战斗（对弈竞猜）模拟器。

\[开发中]idea搭建的java 1.8命令行程序，编码UTF-8。  
目前只有少数式神和御魂。

##### 文件结构
* src 源文件。主函数`com.sine.Main`。
* resource
    * demo.txt 样例数据文件，含格式说明。
    * logging.properties 用于配置`java.util.logging.Logger`，输出详细的战斗信息。


打包：jar包和resource文件一起。  

运行参数：`[输入文件 [测试次数]] [-i] [-fe=<encoding>]`  
`-i` 输出详细信息（设置全局log等级为INFO）  
`-fe=<encoding>` 设置读取文件的编码格式，如gbk。默认UTF-8  
默认文件demo.txt，默认次数100
