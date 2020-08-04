# IDEASpace
Ipersisten的源码，编程作业还未开始做，后续将加入，需要先开启下一模块。


1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？
if,where等，类似脚本语言，可以动态拼接sql。
第一部分：在启动加载解析xml配置文件的时候进行解析，根据关键标签封装成对应的handler处理对象，封装成sqlSource对象存在mappedStatement。
调用流程：
I、SqlSessionFactoryBuilder对builder对象的时候，调用XMLConfigBuilder解析sqlMapConfig.xml配置文件，在解析过程中使用到了私有的mapperElement(XNode parent)方法
II、上面方法中通过构建XMLMapperBuilder,获取到所有的配置mapper配置，在调用private void configurationElement(XNode context)方法进行解析mapper.xml,通过void buildStatementFromContext(List<XNode> list, String requiredDatabaseId)方法解析mapper.xml内的每一个标签
III、循环中构建XMLStatementBuilder对象，调用parseStatementNode()方法来封装mappedStatment对象，
IIII、在过程中需要构建sqlSource对象，通过XMLLanguageDriver对象进行处理，在XMLLanguageDriver中构建解析动态标签对象XMLScriptBuilder
第二部分：在执行过程中获取sqlSource中获取bondSql对象时，执行相应的标签handler
调用查询执行到BaseExecutor的query方法时候会去getBoundSql并且将参数传进去，
在sqlSource接口DynamicSqlSource实现类中，调用getBoundSql方法执行过程共创建DynamicContext对象进行判定解析封装成SqlSource对象返回。
  
2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？
Mybatis 仅支持 association 关联对象和 collection 关联集合对象的延迟加载，association 指的就是一对一，collection 指的就是一对多查询。在 Mybatis配置文件中，可以配置是否启用延迟加载 lazyLoadingEnabled=true|false。它的原理是，使用 CGLIB 创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用 a.getB().getName()，拦截器 invoke()方法发现 a.getB()是null 值，那么就会单独发送事先保存好的查询关联 B 对象的 sql，把 B 查询上来，然后调用 a.setB(b)，于是 a 的对象 b 属性就有值了，接着完成 a.getB().getName()方法的调用。这就是延迟加载的基本原理。

3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？
SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象。
BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。

4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？
一级缓存：它指的是Mybatis中sqlSession对象的缓存，当我们执行查询以后，查询的结果会同时存入到SqlSession为我们提供的一块区域中，该区域的结构是一个Map，当我们再次查询同样的数据，mybatis会
先去sqlsession中查询是否有，的话直接拿出来用，当SqlSession对象消失时，mybatis的一级缓存也就消失了，同时一级缓存是SqlSession范围的缓存，当调用SqlSession的修改、添加、删除、commit(),close等
方法时，就会清空一级缓存。
二级缓存：他值得是Mybatis中SqlSessionFactory对象的缓存，由同一个SqlSessionFactory对象创建的SqlSession共享其缓存，但是其中缓存的是数据而不是对象，所以从二级缓存再次查询出得结果的对象与
第一次存入的对象是不一样的。

5、简述Mybatis的插件运行原理，以及如何编写一个插件？
 1、每个创建出来的对象不是直接返回的，而是interceptorChain.pluginAll(parameterHandler);
 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；调用interceptor.plugin(target);返回target包装后的对象
 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）我们的插件可以为四大对象创建出代理对象；代理对象就可以拦截到四大对象的每一个执行；
