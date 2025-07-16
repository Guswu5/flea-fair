
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {  // 修改类名拼写

    @Autowired
    private LoginMapper loginMapper;

    /**
     * 登录
     * @param username
     * @param password
     */
    @Override
    public boolean login(String username, String password) {
        //访问数据库，查询用户信息
        log.info("用户名：{}，密码：{}", username, password);

        return loginMapper.findByUser(username, password);

    }

}