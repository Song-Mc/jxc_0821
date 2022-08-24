package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description 供应商Controller控制器
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     *
     * 供应商下拉列表查询
     * @author SongMc
     * @date 14:43 2022/8/24
     * @param q 供应商名称模糊查询
     * @return
     **/
    @RequestMapping(value = "/getComboboxList",method = RequestMethod.POST)
    @RequiresPermissions(value = "供应商管理")
    public List<Supplier> getComboboxList(String q){

        List<Supplier> listSupplier = supplierService.getComboboxList(q);

        return listSupplier;

    }


    /**
     * 分页查询供应商
     * @param page 当前页数
     * @param rows 每页显示的记录数
     * @param supplierName 供应商名
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions(value = "供应商管理")
    public Map<String,Object> list(Integer page, Integer rows, String supplierName) {
        return supplierService.list(page, rows, supplierName);
    }

    /**
     * 添加或修改供应商
     * @param supplier 供应商实体
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions(value = "供应商管理")
    public ServiceVO save(Supplier supplier) {
        return supplierService.save(supplier);
    }

    /**
     * 删除供应商
     * @param ids 供应商ids字符串，用逗号分隔
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions(value = "供应商管理")
    public ServiceVO delete(String ids) {
        return supplierService.delete(ids);
    }
}
