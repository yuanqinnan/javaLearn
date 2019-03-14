package com.yuanqinnan.aop;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 19:41
 */
//代理类
public class ProxyEmployee implements EmployeeService {
    //
    private EmployeeService employeeService;

    private MyTransaction myTransaction;

    public ProxyEmployee(EmployeeService employeeService,MyTransaction myTransaction)
    {
       this.employeeService=employeeService;
       this.myTransaction=myTransaction;
    }
    @Override
    public void addEmployee(Employee employee) {
         myTransaction.before();
         employeeService.addEmployee(employee);
         myTransaction.after();
    }

    @Override
    public void deleteEmployee(Integer uid) {
         myTransaction.before();
         employeeService.deleteEmployee(uid);
         myTransaction.after();
    }
}
