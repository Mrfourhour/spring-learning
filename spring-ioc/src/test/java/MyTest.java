import com.xinyet.dao.UserDaoMysqlImpl;
import com.xinyet.service.UserService;
import com.xinyet.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.setUserDao(new UserDaoMysqlImpl());
        service.getUser();
    }
}
