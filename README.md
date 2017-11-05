---
title: 报表控件
date: 2017-06-29 16:11:24
categories: Android
---

## GreenDao使用

1. 配置

   * product#gradle

   ```
   buildscript {
       repositories {
           jcenter()
       }
       dependencies {
           classpath 'com.android.tools.build:gradle:2.2.3'
           //GreenDao3依赖
           classpath 'org.greenrobot:greendao-gradle-plugin:3.2.1'
       }
   }
   ```

   * module#gradle

     ```
     //greendao plugin
     apply plugin: 'org.greenrobot.greendao'
      
      //greendao配置
     greendao {
           //版本号，升级时可配置
          schemaVersion 1 //版本号
          daoPackage 'com.warm.greendaodemo.dao.gen' //生成文件的包名
          targetGenDir 'src/main/java'  //文件路径
      }
      
     //greendao依赖
         compile 'org.greenrobot:greendao:3.2.0'
     ```

2. 编写实体

   在编写实体时，当写完一个实体，加上注解后，需要rebuild一下项目，会自动生成一些代码，注解详情如下：

   * @Entity：这个用于注解类，标明该实体需要生成Dao文件，并可以设置一些表的属性

     ```
     @Entity(
             //标明GreenDao当前实体属于哪个schema,作用是什么我也不太清楚，一般不设置
     //        schema="default_schema",

             //设置是否活跃，true：update/delete/refresh 可以有这几个操作
             active =true,
             //表的名字
             nameInDb = "STUDENT",
             //是否创建该表
             createInDb = true,
             // 是否生成所有属性的构造器
             generateConstructors = true,
             // 是否生成get、set方法
             generateGettersSetters = true
     )
     ```

   * 属性注解

     ```
     public class Student {
         //设置id  autoincrement是否自增
         @Id(autoincrement = true)
         private Long id;

         //@Unique 设置成唯一值
         //如果不设置id，可以通过这个设置索引 并设置unique = true 设置成为主键
         @Index
         private Long stu_num;

         //表中的列明
         @Property(nameInDb = "NAME")
         private String name;

         //不能为null
         @NotNull
         private Long teacherId;

         private Integer age;

         private String address;
         
     	//Student类中的代码
     	@ToMany
     	@JoinEntity(
     		//中间关系实体
     		entity = JoinStudentWithSubject.class,
     		//JoinStudentWithSubject.studentId 对应Student的Id
             sourceProperty = "studentId",
             //JoinStudentWithSubject.subjectId 对应Subject的Id
             targetProperty = "subjectId"
     	)
     private List<Subject> subjects;

         //设置不存到表中
         @Transient
         private boolean selected;
     }
     ```

   * 关系注解

     关系注解一共有三个1对1、1对多、多对多。

     * @ToOne()：实现1对1关联，一般在当前的实体中增加一个xxId对应另外一个实体的Id，根据这个xxId可以获取到该实体，成绩实体如下：

       ```
       @Entity
       public class Score {
           @Id(autoincrement = true)
           private Long id;
           private Float chinese;
           private Float math;
           private Float english;

           private Long studentId;
           @ToOne(joinProperty = "studentId")
           private Student student;
           //根据班主任的Tag获取成绩，
           private String teacherTag;
           @ToOne(joinProperty = "teacherTag")
           private Teacher teacher;
       }
       ```

       一个Score只能对应一个学生，所以在Score中添加一个studentId，然后进行注解，根据studentId获取Student。

     * @ToMany()：实现1对多关联，一般情况下，在多实体下拥有一个多实体的xxId，这时候，多实体可以通过@ToOne()获取1实体中的数据，1实体可以根据多实体中的xxId获取多实体的list。如：一个班主任对应多个学生，那么学生是多实体，班主任是1实体，学生实体中存在teacherId。

       ```
       @Entity
       public class Teacher {
           @Id(autoincrement = true)
           private Long id;
           private String name;
           private int age;
           private Integer sex;
           private Integer teachAge;
           //班主任可以通过通过如下属性获取学生：
           @ToMany(referencedJoinProperty = "teacherId")
           private List<Student> students;
           //根据tag获取成绩
            private String tag;
           @ToMany(joinProperties = {@JoinProperty(name = "tag",referencedName = "teacherTag")})
           private Score score;
           
       }
       ```

       ```
       public class Student {
           //设置id  autoincrement是否自增
           @Id(autoincrement = true)
           private Long id;

           //@Unique 设置成唯一值
           //如果不设置id，可以通过这个设置索引 并设置unique = true 设置成为主键
           @Index
           private Long stu_num;

           //表中的列明
           @Property(nameInDb = "NAME")
           private String name;

           //不能为null，通过teacherId获取学生
           @NotNull
           private Long teacherId;
           @ToOne(joinProperty = "teacherId")
           private Teacher teacher;

           private Integer age;

           private String address;

           //设置不存到表中
           @Transient
           private boolean selected;
       }
       ```

       @ToMany另外还有一个JoinProperty[]，这个和referencedJoinProperty类似，只是referencedJoinProperty，默认使用id和多实体中的外键对应，但是可能我们不能使用id和多实体的外键对应。我们就可以使用JoinProperty[]实现自定义的关联。

       比如Score中：

       ```
       private String teacherTag;
       ```

       Teacher中：

       ```
       private String tag;
       @ToMany(joinProperties = {@JoinProperty(name = "tag",referencedName = "teacherTag")})
       private List<Score> scores;
       ```

       所以，@ToMany(joinProperties = {@JoinProperty(name = "id" ,referencedName = "teacherId")})和@ToMany(referencedJoinProperty = "teacherId")是同一个作用。

     * @ToMany、@JoinEntity()两个同时使用。场景是，Student可以选择多个Subject，Subject也有多个Student，所以在这种情况下，我们需要一个中间关系表，用来保存两者的关系。这个中间关系表拥有两者的Id。

       ```
       //Student类中的代码
       @ToMany
       @JoinEntity(
       		//中间关系实体
       		entity = JoinStudentWithSubject.class,
       		//JoinStudentWithSubject.studentId 对应Student的Id
               sourceProperty = "studentId",
               //JoinStudentWithSubject.subjectId 对应Subject的Id
               targetProperty = "subjectId"
       )
       private List<Subject> subjects;
       ```

       Subject实体：

       ```
       @Entity
       public class Subject {
           @Id(autoincrement = true)
           private Long id;
           private String name;
       }
       ```

       中间关系实体：

       ```
       @Entity
       public class JoinStudentWithSubject {
           @Id(autoincrement = true)
           private Long id;
           //拥有Student的Id和Subject的Id
           private Long studentId;
           private Long subjectId;
       }
       ```

       因为多对多可以互相获取，所以这两个都需要使用这两个注解才可以互相联系。

3. 增删改查

   当实体中写好所有需要的注解后，rebuild一下项目，项目会自动生成DaoMaster、DaoSession和Daos，DaoMaster.OpenHelper负责创建数据库，DaoMaster负责创建所有表，然后DaoMaster提供DaoSession管理所有的表，我们可以根据DaoSession获取所有Daos，然后由Dao进行操作。

   * 在使用时，一般在我们会在Application类中创建数据库和表。

     ```
     public class MyApp extends Application {

         //获取所有Dao的顶级上层，用来负责所有dao的增删改查
         private static DaoSession daoSession;

         private final boolean idEncrypt = false;

         private static final String PWD = "pwd";
     ```


         @Override
         public void onCreate() {
             super.onCreate();
             Stetho.initializeWithDefaults(this);
             setupDb();
         }
    
         private void setupDb() {
    
             //创建数据库shop.db"
             MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, idEncrypt ? "shop_encrypt.db" : "shop.db", null);
             //获取可写数据库
             Database db = idEncrypt ? helper.getEncryptedWritableDb(PWD) : helper.getWritableDb();
             //获取数据库对象
             DaoMaster daoMaster = new DaoMaster(db);
    
             //获取Dao对象管理者
             daoSession = daoMaster.newSession();
         }
    
         public static DaoSession getDaoSession() {
             return daoSession;
         }
     }
     ```

   * 增、删、改、查

     这些操作可以自行查询具体方法，非常简单，熟悉一下就好。

4. 在建表的时候，要注意一些关键字是不可以作为表名或列名的，[关键字](http://blog.csdn.net/imxilife/article/details/45620009)。