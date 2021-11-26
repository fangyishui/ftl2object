package com.ftl.ftl2object.controller.service.impl;


import com.ftl.ftl2object.controller.service.GenCodeService;
import com.ftl.ftl2object.entity.TableClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenCodeServiceImpl implements GenCodeService {


    @Override
    public String genCode(List<TableClass> tableClassList, String realPath) {

//        Template modelTemplate = cfg.getTemplate("Model.java.ftl");
//        generate(modelTemplate, tableClass, path + "/model/");

        return "";
    }


//    private void generate(Template template, TableClass tableClass, String path) throws IOException, TemplateException {
//        // freemarker 配置
//        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//        configuration.setDefaultEncoding("UTF-8");
//        // 指定模板的路径
//        configuration.setDirectoryForTemplateLoading(new File("obj.ftl"));
//        // 根据模板名称获取路径下的模板
//        Template template = configuration.getTemplate(templateName);
//        // 渲染模板
//        FileWriter fileWriter = new FileWriter("D:\index.html");
//        // freemarker 引擎将动态数据绑定的模板并导出为文件
//        Map map = new HashMap(2);
//        map.put("logoImgUrl","xxxxxxUrl");
//        map.put("appName","应用名称");
//        map.put("versions","应用版本");
//        template.process(map, fileWriter);
//    }

}
