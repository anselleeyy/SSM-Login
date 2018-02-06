## SSM-Login
基于SSM框架实现的登录注册功能

### 项目开发环境
- windows 10_64
- jdk 1.8_144
- maven 3.5.0
- spring 4.3.11.RELEASE
- mybatis 3.4.5
- mysql 5.7.18

### Model 层
<strong>数据库设计</strong><br>
User 表

|字段名   |注释    |数据类型   |自动递增   |备注
|:--------:|:--------:|:--------:|:--------:|:--------:
|id       |id       |int      |Yes      |主键(primary key)
|username |用户名   |varchar(25)     |No      |唯一(Unique)
|password |密码     |varchar(25)     |NO      |
|email    |邮箱     |varchar(30)     |NO      |

<strong>java bean</strong>
``` java
/**
 * 用户模型层( User )
 * 用户属性含: username, email, password
 * 采用 bean validation 验证
 * 验证内容包含：
 *     1. 用户名( username )长度在4-20之间
 *     2. 邮箱( email )规范验证
 *     3. 密码( password )仅由长度在3-14之间的数字组成
 */
@Size(min = 4, max = 20, message = "用户名长度应该在4-20字符之间")
private String username;
    
@Email
private String email;

@Pattern(regexp = "^[0-9]{3,14}$", message = "密码长度为3-14，仅包含数字")
private String password;
```

### Controller 层
<strong>Login.java</strong>
``` java
/**
 * 用户登录控制层
 * 连接数据库，调用 checkUser(username) 方法
 * 如果正确则提示并在5s后进入主页
 * 失败则对应提示用户不存在或用户名或密码错误，重新返回登录页面
 */
@RequestMapping(value = "/login")
    public ModelAndView UserLogin(Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ModelAndView mv = new ModelAndView();
        String username = String.valueOf(request.getParameter("username"));
        String password = String.valueOf(request.getParameter("password"));
        User user = this.userService.checkUser(username);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (user == null) {
            // 该用户不存在
            request.setAttribute("username", username);
            mv.setViewName("login");
        } else if (user.getPassword().equals(password)) {
            // 该用户存在且用户名和密码匹配
            mv.setViewName("login_success");
        } else {
            // 用户名或密码错误
            request.setAttribute("username", username);
            mv.setViewName("login");
        }
        return mv;
    }
```

<strong>Register.java</strong>
``` java
/**
 * 用户注册验证层
 * 采用bean validation + Spring-mvc 验证用户输入信息是否有误
 * 有误则返回注册页面并对应提示错误信息
 * 无误则调用 insertNewUser(User) 方法进行数据库操作
 * 若用户名已经存在则提示并返回注册页面
 * 否则提示注册成功并跳转至登录页面
 */
@RequestMapping("/verify")
    public ModelAndView processUser(HttpServletRequest request, HttpServletResponse response, @Valid User user,
            BindingResult result) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        if (result.hasErrors()) {
//          表单验证有误
            mv.setViewName("register");
        } else {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (this.userService.checkUser(username) != null) {
//              用户名存在
                mv = new ModelAndView("forward:/register");
            } else {
//              插入成功
                User newUser = new User(username, email, password);
                this.userService.insertNewUser(newUser);
                mv.setViewName("register_success");
            }
        }
        return mv;
    }
```

### View 层
<strong>login.jsp</strong>
``` html
<form class="login" action="login" name="login" onkeydown="keyAction(event)">
    <input type="text" placeholder="用户名" name="username" value="${username}"><br>
    <input type="password" placeholder="密码" name="password"><br>
    <input type="button" value="登录" onclick="Login()">
    <input type="button" value="注册" onclick="Register()">   
</form>
```


<strong>register.jsp</strong>
``` html
<mvc:form class="login" action="verify" name="register" modelAttribute="user">
    <mvc:input type="text" path="username" placeholder="用户名" name="username" /><br>
    <mvc:errors path="username" style="color: RED" />
    <mvc:input type="email" path="email" placeholder="邮箱地址" name="email" />
    <br>
    <mvc:errors path="email" style="color: RED" />
    <mvc:input type="password" path="password" placeholder="密码" name="password" />
    <br>
    <mvc:errors path="password" style="color: RED" />
    <input type="password" placeholder="再次输入密码" name="password2" /> <br>
    <input type="button" value="注册" onclick="Regis()">
</mvc:form>
```
