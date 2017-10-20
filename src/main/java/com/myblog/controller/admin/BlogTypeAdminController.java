package com.myblog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.BlogType;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogTypeService;
import com.myblog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin/blogType")
public class BlogTypeAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "/list")
    public String BlogType(@RequestParam(value = "page",required = false)String page,
                           @RequestParam(value = "rows",required = false)String rows,
                           HttpServletResponse response){
        //定义分页bean
        PageBean<BlogType> pageBean = new PageBean<BlogType>(Integer.parseInt(page)
                ,Integer.parseInt(rows));

        //拿到分页结果已经记录总数的pageBean
        pageBean = blogTypeService.listByPage(pageBean);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();

        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);

        //将序列化结果放入json对象中
        result.put("rows", array);
        result.put("total", pageBean.getTotal());

        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletResponse response,BlogType blogType){
        int resultTotal=0;
        if(blogType.getId()==null){
            resultTotal=blogTypeService.addBlogType(blogType);
        }else{
            resultTotal=blogTypeService.updateBlogType(blogType);
        }
        JSONObject result=new JSONObject();
        if(resultTotal>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        ResponseUtil.write(response,result);
        return null;
    }

    @RequestMapping(value = "/delete")
    public String delete(HttpServletResponse response,@RequestParam(value = "ids",required = false)String ids){
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            //其实在这里我们要判断该博客类别下面是否有博客 如果有就禁止删除博客类别 ，等我们完成博客对应的操作再来完善
            blogTypeService.deleteBlogType(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
