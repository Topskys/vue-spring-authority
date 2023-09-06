// package com.topsky;
//
// import com.baomidou.mybatisplus.annotation.DbType;
// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.core.config.GlobalConfig;
//
// public class GeneratorCode {
//     private static String author ="Topskys"; // 作者名称
//     private static String outputDir="D:\\"; // 生成导出的位置
//     private static String driver ="com.mysql.cj.jdbc.Driver"; // 驱动，注意版本
//     private static String url =""; // 数据库连接路径
//     private static String username="root"; // 数据库用户名
//     private static String password="123456"; // 数据库密码
//     private static String tablePrefix="sys_"; // 数据库表的前缀，如sys_user
//     private static String [] tables={"sys_user","sys_role","sys_permission","sys_department"}; // 生成的表
//     private static String parentPackage="com.topsky"; // 顶级包结构
//     private static String dao="dao"; // 数据库访问层包名称
//     private static String service="service"; // 业务逻辑层包名称
//     private static String entity="entity"; // 实体层包名称
//     private static String controller="controller"; // 控制层包名称
//     private static String mapperxml="mapper"; // mapper映射文件包名称
//
//     public static void main(String[] args) {
//         // 1.全局配置
//         GlobalConfig config=new GlobalConfig();
//         config.setAuthoor(author)
//                 .setOutputDir(outputDir)
//                 .setFileOverride(true) // 文件覆盖
//                 .setIdType(IdType.AUTO) // 主键策略
//                 .setServiceName("%sService")
//                 .setBaseResultMap(true) // 设置生成的service接口得名字的首字母是否为I，加%s表示不生成I
//                 .setBaseColumnList(true); // 生成通用sql字段
//         // 2.数据源配置
//         DataSourceConfig dsConfig=new DataSourceConfig();
//         dsConfig.setDbType(DbType.MYSQL) // 设置数据库类型
//                 .setDriverName(driver) // 设置驱动
//                 .setUrl(url) // 设置连接路径
//                 .setUsername(username)
//                 .setPassword(password);
//         // 3.策略配置
//     }
// }
