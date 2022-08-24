package com.atguigu.jxc.entity;

import lombok.Data;
/**
 * 进货单
 */
@Data
public class PurchaseList {

  private Integer purchaseListId; // 进货单id
  private String purchaseNumber; // 进货单号
  private double amountPaid; // 实付金额
  private double amountPayable; // 应付金额
  private String purchaseDate; // 收货日期
  private String remarks; // 备注
  private Integer state; // 状态
  private Integer supplierId; // 供应商id
  private Integer userId; // 用户 id

  private String supplierName; // 供应商名字
  private String trueName;

}
