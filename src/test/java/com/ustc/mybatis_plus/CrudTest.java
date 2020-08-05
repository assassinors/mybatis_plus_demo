package com.ustc.mybatis_plus;

import com.ustc.mybatis_plus.entity.Product;
import com.ustc.mybatis_plus.entity.User;
import com.ustc.mybatis_plus.mapper.ProductMapper;
import com.ustc.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrudTest {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setName("cv");
        user.setEmail("test@mail.com");
        user.setAge(18);
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println("user id:" + user.getId());
    }


    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(1291022628579905538L);
        user.setAge(28);
        user.setName("xiao");
        int i = userMapper.updateById(user);
        System.out.println(i);

    }


    @Test
    public void testConcurrentUpdate() {
        // 小李获取数据
        Product product1 = productMapper.selectById(1);
        System.out.println("小李价格：" + product1.getPrice());

        // 小王获取数据
        Product product2 = productMapper.selectById(1);
        System.out.println("小王价格：" + product2.getPrice());

        // 小李 +50
        product1.setPrice(product1.getPrice() + 50);
        productMapper.updateById(product1);

        // 小王 -30
        product2.setPrice(product2.getPrice() - 30);
        int result = productMapper.updateById(product2);
        if (result == 0) {
            System.out.println("小王更新失败");
            product2 = productMapper.selectById(1L);
            product2.setPrice(product2.getPrice() - 30);
            productMapper.updateById(product2);
        }


        // 最后的结果，商品价格
        System.out.println("最终价格：" + productMapper.selectById(1).getPrice());
    }

}
