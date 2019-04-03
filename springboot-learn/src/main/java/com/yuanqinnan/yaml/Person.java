package com.yuanqinnan.yaml;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: springboot-learn
 * <p>
 * Created by yuanqn on 2019/3/27 22:56
 */

@Component
//@PropertySource(value = {"XXX"})
//@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    //支持${}
    @Value("${person.lastName}")
    private String lastName;
    //支持#{SpEL}
    @Value("#{10*2}")
    private Integer age;
    //支持字面量
    @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
}
