package com.yuanqinnan.aop;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 18:22
 */
//员工方法实现
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void addEmployee(Employee employee) {
        System.out.println("新增员工");
    }

    @Override
    public void deleteEmployee(Integer uid) {
        System.out.println("删除员工");

    }
}
