今天弄完了struts2的针对sqlite的CURD操作，虽然界面很丑陋，但是功能还是符合我的预期的，越来越感觉到ajax异步调用的强大，以后在项目中逐渐的学会使用吧, 哦对了，最近该继续学习oc了哈哈，code has been uploaded on the github website,博客上有全文。

1.第一步是引入jar包，让struts2支持对于sqlite3.0的操作：
查资料看了这个之后，果断选择了支持macOS的xerial第三个版本。

2.下面就是首页了，在这里demo里面是success.jsp, 我的想法之前是打开这个界面就显示数据库里的内容，然后我之前的想法比较二，寻思，打开这个页面利用js直接提交表单数据到相应的action，然后就可以显示了，虽然这种方法试验成功了，但是js代码部分会一直提交，导致界面一直刷新，过一段时间就会崩溃，然后想到了通过设置welcome-list来配置整个项目的首页，这个过程中有个小trick，我不知道我的想法是否是主流的解法，但是功能却实现了：

(1)在web.xml里面设置首页，为了实现首页跳转的功能，需要添加dispatcher功能，request和forword

(2)在index.jsp中设置跳转到action的代码，如果action执行成功的话，result为success的时候就会跳转到success.jsp，那么就完成了我们首页直接显示数据库内容的功能。

3.我们建立一个javabean，把每条数据都封装到一个类的对象，这里面是News class, 这里面的数据比较简单，只有id, username和job三个字段。

4.由于我们是form表单提交数据到action，然后进行相应的curd操作，在CURD这个类中，我们设置类型为news的arraylist属性，我们把数据库中查出的数据都存到request对象的list之中，然后就可以在jsp页面利用循环显示出来了。

显示在jsp页面：

5. 关于curd的操作有几点需要注意，在插入数据的时候由于id字段是自增的，所以不需要对其进行赋值，然后修改操作的时候，需要跳转界面传递id值到另一个界面然后提交修改数据到相应的action（所以input的name要和属性名字保持一致，要不取不到值），然后把修改的数据都存入list之中在跳转到success页面显示出来，对于删除操作我们直接传递id值到相应的action中，然后利用request.getParament方法取出来，由于http数据传输的数据类型为string类型，我们需要利用Integer.parseInt方法把字符转化为整数的id值，然后就可以删除了，删除成功后跳转到success.jsp界面。
这样就完成了简单的message board功能，功能有待改进，利用ajax无刷新就更完美了, 下面是运行的效果。