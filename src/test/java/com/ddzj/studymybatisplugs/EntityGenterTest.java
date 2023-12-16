package com.ddzj.studymybatisplugs;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class EntityGenterTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/studymybatisplugs?useUnicode=true&characterEncodidng=utf-8&allowMultiQueries=true";
        String outPath = "/Users/zz/Documents/temp";
        FastAutoGenerator.create(url, "root", "root123!@#")
                .globalConfig(builder -> {
                    builder.author("yzb") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.ddzj.studymybatisplugs") // 设置父包名
                            //.moduleName("studymybatisplugs") // 设置父包模块名
                            .xml("mapper.xml")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {})
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
