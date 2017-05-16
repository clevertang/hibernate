

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class Studentstest {

    @Test
    public void test() {
        //1. 创建一个SessionFactory 对象
        SessionFactory sessionFactory = null;
        
        //1).创建 Configuration 对象：对应 Hibernate 的基本配置信息和对象关系映射信息(关联hibernate.cfg.xml文件)
//        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        Configuration configuration = new Configuration().configure(); //因为使用默认的命名，所以不用指定也可以
        
        //4.0 之前这样创建
//        sessionFactory = configuration.buildSessionFactory();
        
        //2).创建一个 ServiceRegistry 对象：这是hibernate 4.x 新添加的对象
        //   hibernate 的任何配置和服务都需要在该对象中注册后才能有效。
        org.hibernate.service.ServiceRegistry serviceRegistry = 
                new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        
        //3).
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
        //2. 创建一个 Session 对象
        Session session = sessionFactory.openSession();
        
        //3. 开启事务
        Transaction transaction = session.beginTransaction();
        
        //4. 执行保存操作
        Students user = new Students("clever","333","409169399@qq.com","厦门");
        session.save(user);
        
        //5. 提交事务
        transaction.commit();
        
        //6. 关闭 Session
        session.close();
        //7. 关闭 SessionFactory 对象
        sessionFactory.close();
        
    }
}
