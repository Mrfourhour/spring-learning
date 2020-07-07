#spring-IOC
# 1. UserDao接口
```java

public interface UserDao {
    void getUser();
}
```
# 2. UserDaoImpl
```java
public class UserDaoImpl implements UserDao {
    public void getUser() {
        System.out.println("默认获取user成功！");
    }
}

```
# 3.UserService
```java
public interface UserService {
    void getUser();
}
```
# 4.UserServiceImpl
```java
public class UserServiceImpl implements UserService {

    //手动创建需要的实现类
    private UserDao userDao = new UserDaoImpl();
    public void getUser() {
        userDao.getUser();//调用实现类的实现方法
    }
}

```
# 5. 测试
```java
    public class MyTest {
        public static void main(String[] args) {
            UserService service = new UserServiceImpl();
            service.getUser();
        }
    }
```

# 6. 思考: 目前UserDao只有一个默认的实现,service层引用的也是在代码里写死的默认实现，若要增加一个实现怎么办？？？？

  假设我们现在需要从mysql去查询数据，从而增加一个Mysql的实现
```java
public class UserDaoMysqlImpl implements UserDao {
    public void getUser() {
        System.out.println("从Mysql获取user成功！");
    }
}
```
  service 层需要做的修改如下:
```java
public class UserServiceImpl implements UserService {

    //private UserDao userDao = new UserDaoImpl();     将默认的UserDaoImpl换成  UserDaoMysqlImpl
    private UserDao userDao = new UserDaoMysqlImpl();  //这样获取的数据就是从Mysql获取的了，，但是实现类变动一位着我们就必须手动切换实现类

    public void getUser() {
        userDao.getUser();
    }
}

```
# 7. 为了避免实现类切换，我们去手动修改实现类代码的问题，我们将service的依赖dao,直接当作接口引入，并提供setter，getter 如下   
```java
public class UserServiceImpl implements UserService {

//    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao = new UserDaoMysqlImpl();
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}

```
 测试如下:   
 ```java
public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.setUserDao(new UserDaoMysqlImpl()); //用户可以通过设置具体的实现来控制调用想要的实现了，，是不是觉得很完美?
        service.getUser();
    }
}
```

# 8. 总结
    - 之前: 程序员需要什么实现就去船舰什么样的对象！控制权在自己手上
    - 使用了setter过后，程序员不再有主动性，而变成了被动接受对象
    这种思想从本质上解决了问题，我们程序员不再去关心对象的创建，系统的耦合性大大降低了，可以更加专注于业务代码的实现，这就是IOC的原型！！