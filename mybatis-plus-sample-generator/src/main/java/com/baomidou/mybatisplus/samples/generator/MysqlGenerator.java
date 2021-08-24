package com.baomidou.mybatisplus.samples.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * mysql 代码生成器演示例子
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        /*代码生成器*/
        AutoGenerator mpg = new AutoGenerator();

        /*全局配置*/
//        String projectPath = "D:\\Java\\IdeaProjects\\kali";
        String projectPath = "/Volumes/Application/test ";
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(projectPath + "/mybatis-plus-sample-generator/src/main/java")
                .setAuthor(scanner("作者"))
                .setIdType(IdType.AUTO)
                .setOpen(false)
                .setFileOverride(true)
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        /*数据源配置*/
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/kali?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        /*时间转换*/
        dsc.setTypeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("datetime")) {
                    return DbColumnType.DATE;
                }
                /*其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）*/
                return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

         /*包配置*/
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.lucky.kali");
        mpg.setPackageInfo(pc);
         /*自定义配置*/
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                 /*to do nothing*/
                Map<String, Object> map = new HashMap<>(5);
                //自定义配置，在模版中cfg.superColums 获取
                // TODO 这里解决子类会生成父类属性的问题，在模版里会用到该配置
                map.put("superColums", this.getConfig().getStrategyConfig().getSuperEntityColumns());
                /*生成实体中的 transDTO*/
                map.put("transDTO", true);
                map.put("superBuilder", true);
                /*生成实体中的 recDTO*/
                map.put("recDTO", true);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
        focList.add(new FileOutConfig("/templates/ftl/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                 /*自定义输入文件名称*/
                return projectPath + "/mybatis-plus-sample-generator/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        focList.add(new FileOutConfig("/templates/ftl/dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = pc.getParent().replace(".", "/");
                 /*自定义输入文件名称*/
                return projectPath + "/mybatis-plus-sample-generator/src/main/java/" + path
                        + "/dto/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = new TemplateConfig();
        /*自定义模版*/
        templateConfig.setEntity("/templates/ftl/entity.java");
        templateConfig.setService("/templates/ftl/service.java");
        templateConfig.setServiceImpl("/templates/ftl/serviceImpl.java");
        templateConfig.setMapper("/templates/ftl/mapper.java");
        /*关闭默认的mapper xml生成*/
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

         /*策略配置*/
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.lucky.kali.common.base.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("表名"));
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        // TODO 这里解决子类会生成父类属性的问题
        strategy.setSuperEntityColumns("id", "createBy", "createDate", "updateBy", "updateDate", "delFlag");
         /*公共父类*/
//        strategy.setSuperControllerClass("com.lucky.kali.common.base.BaseController");
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        strategy.setSuperServiceClass("com.lucky.kali.common.base.BaseService");
        strategy.setSuperServiceImplClass("com.lucky.kali.common.base.BaseServiceImpl");
        /*去掉表的前缀*/
        strategy.setTablePrefix("kali_");
        mpg.setStrategy(strategy);
         /*选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！*/
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
