package com.yuanqinnan.aop;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 18:18
 */
//员工接口
public interface EmployeeService {

    //新增方法
    void addEmployee(Employee employee);

    //删除方法
    void deleteEmployee(Integer uid);
}
