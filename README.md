### Instant App Support
instant app 是谷歌推出的类似于微信小程序的一项技术，用户无需安装，用完即走，同时兼备h5的便捷和原生应用的优质体验。

Instant app 于2016年Google IO大会上推出，同年的9月份，微信小程序开始内测。

先用一张动图来解释什么是Instant App

![instantApp](https://note.youdao.com/yws/api/personal/file/WEB5eb6b7c48f4b94b6151c531e1699fe25?method=download&shareKey=584a555281fa301c8b72c995abefda99)

从图中我们初步可以看出，当操作者在点击一段Buzzfeed的视频链接时，会自动的加载这个链接所属程序的代码，并且很轻松的在这个“程序的APP内”体验到这个链接视频效果。

这就是Instant App，也可以称之为即时应用。它出现的目的，就是为了让人们能够像点击链接那么简单，节省掉安装App的痛苦，最快速度、最少流量的消耗，让用户体验到App级的用户体验。

#### 典型应用场景

+ 通过直接点击链接进入（从IM或SMS中点击链接）
+ 浏览器搜索进入
+ Google Play上App试用部分功能
+ 游戏试玩

#### 前置条件
+ Google Service能正常推广
+ 手机上安装了Google Service框架
+ 完整的应用需提前发布在Google Play上
+ 部署信息验证文件的网站，必须能通过https进行访问

#### 系统要求
+ JDK 1.8+ 
+ Android Studio 3.0+
+ Android SDK 6.0+
+ Android SDK Tool 25.0+
+ Android SDK Build Tools 26.0+
+ Android SDK Platform Tools 25.0+

#### 程序结构
项目开发时，一般会有四个模块`base`，`app`,`instant app`,`feature`。`app`和`instant app`作为入口模块，不负责具体业务实现。`base`模块存放公共的资源或者实现通用的代码，有且仅有一个，`feature`可以有多个或者没有。

### Android X
Google 2018 IO大会上推出Android新的扩展库 AndroidX，用于替换原来的 Android扩展库，将原来的android.\*替换成androidx.\*；只有包名和Maven工件名受到影响，原来的类名，方法名和字段名不会更改。

#### 配置
+ Android Studio 3.2+
+ Gradle Plugin 4.6+
+ compileSdkVersion 28+
+ buildToolsVersion 28.0.2+

gradle.properties文件添加配置：
```
android.useAndroidX=true
android.enableJetifier=true
```
AS 3.2提供了一键迁移功能：Refactor -> Migrate to AndroidX
#### 迁移对照表

https://developer.android.com/jetpack/androidx/migrate

#### Guava冲突
> Program type already present: 
com.google.common.util.concurrent.ListenableFuture
>
> Message{kind=ERROR, text=Program type already present: 
> com.google.common.util.concurrent.ListenableFuture, sources=[Unknown source 
file], tool name=Optional.of(D8)}

解决方案：

app的build.gradle文件中加入以下配置
```
configurations {
    all*.exclude group: 'com.google.guava', module: 'listenablefuture'
}
```
### Android Jetpack

2018年Google IO大会上，官方发布了一系列辅助android开发者的实用工具，合称Jetpack，以帮助开发者构建出色的 Android 应用。

，2017年的Google IO大会上，官方为Android开发者推出了 一系列的 架构组件，比如 Lifecycle，LiveData，Room，ViewModel等，旨在推出 官方认证 的 Android架构 方案。

而本次IO大会上，Android Jetpack的推出，从 架构（Architecture），UI，基础（Foundation）及行为（Behavior）四个方面，推出了一套官方认证的 开发体系。

一张图了解Android Jetpack

![jetpack](https://note.youdao.com/yws/api/personal/file/WEB73123b49d11cdb9fe7d8630bf9941550?method=download&shareKey=f831b07ad3e475177dcc9e5658dc5ff3)

#### 新组件

本次的 Android Jetpack 推出了五个新组件，它们分别是：
`Navigation`，`Paging`，`WorkManager`，`Slices`，`Android KTX`。

`Navigation`是单Activity多Fragment 开发模式下的页面跳转的导航组件。可以处理转场，返回等。

`Paging`是分页组件，对于Android开发中的 数据列表展示，很多时候我们需要对数据进行 分页加载，这时候我们可以考虑使用Paging。

分页组件可以轻松加载和呈现大型数据集，同时在 RecyclerView 中进行快速、无限滚动。它可以从本地存储和/或网络加载分页数据，并能够定义内容的加载方式。此组件原生支持 Room、LiveData 和 RxJava。

`WorkManager`可以管理一些要在后台工作的任务, 即使应用没有启动也能保证任务能被执行。和RxJava以及 AsyncTask不同的是，前者帮助你在应用中开后台线程干活, 但是应用一被杀或被关闭，这些工具就干不了活了。而WorkManager不是, 它在应用被杀, 甚至设备重启后仍能保证你安排给他的任务能得到执行。

`Slice`是一个UI展示模块，它可以在搜索APP、语音助手、关键字识别等动作中动态地显示你的APP部分模块的内容，通过它，可以丰富地显示你的APP当中的内容。

`Android KTX`是Google发布的一款Kotlin扩展库，加入了一些常用的语法糖，使 Android 上的 Kotlin 代码更简洁，从而提高开发者的效率和使用体验。

可以将下面的代码
```
view.viewTreeObserver.addOnPreDrawListener(
  object : ViewTreeObserver.OnPreDrawListener {
    override fun onPreDraw(): Boolean {
      viewTreeObserver.removeOnPreDrawListener(this)
      actionToBeTriggered()
      return true
    }
});
```
转换成如下更精简的代码
```
view.doOnPreDraw { actionToBeTriggered() }
```
#### 架构组件
在大多数情况下，桌面应用将桌面或程序启动器当做单个入口点，然后作为单个整体流程运行。Android 应用则不然，它们的结构要复杂得多。典型的 Android 应用包含多个应用组件，包括 Activity、Fragment、Service、内容提供程序和广播接收器。

在用户使用应用的时候，很有可能需要跳转到其他应用（比如在微信上聊天，然后拍照，然后回到微信分享给朋友），或者被其他应用打断（比如电话）。鉴于这种条件，应用组件可以不按顺序单独启动，并且操作系统和用户随时有可能销毁它们。由于这些事件是我们没法控制的，所以我们**不应该在应用组件内存储任何应用数据或状态**，并且应用组件之间不应该相互依赖。

如何设计？

设计原则：[分离关注点](https://en.wikipedia.org/wiki/Separation_of_concerns)、通过模型驱动界面

具体在Android里面，就是尽量的保持Activity和Fragment的精简。用模型去处理应用数据，独立于UI组件。

![image](https://note.youdao.com/yws/api/personal/file/WEBf87fa5ca3302960bdf1b1372deba4c85?method=download&shareKey=f00b113066e17e7bb3a26dcb1419052c)

上图为Google推荐的应用架构图，在图中，除了数据源`Repository`外，其余的组件都是单向唯一依赖，模块划分的非常清晰。

下面使用一个实际的项目来介绍架构组件的使用，API使用知乎日报的接口。

最新信息API：
```
https://news-at.zhihu.com/api/4/news/latest
```
返回的数据：

```js
{
date: "20190129",
stories: [
    images: [
    "https://pic1.zhimg.com/v2-8f8be63108a5511c103304e61b487d6c.jpg"
    ],
    type: 0,
    id: 9707083,
    ga_prefix: "012914",
    title: "我们分析了西班牙人的阵容，想看看武磊究竟有没有机会"
    },
    {
    images: [
    "https://pic2.zhimg.com/v2-4f10760a98b7aa85b70dcf168a1a9921.jpg"
    ],
    type: 0,
    id: 9706683,
    ga_prefix: "012910",
    title: "日本是唐代文化的电冰箱？读完这篇，以后可别这么说了"
    }
],
top_stories: [
    {
    image: "https://pic4.zhimg.com/v2-a1f7e1bfe4efd17aafc6a33ef548ebb3.jpg",
    type: 0,
    id: 9707080,
    ga_prefix: "012907",
    title: "我的 KTV 歌单，已经十年没有更新了"
    },
    {
    image: "https://pic1.zhimg.com/v2-2406c62d8d81279e329a40ef377922d0.jpg",
    type: 0,
    id: 9707052,
    ga_prefix: "012907",
    title: "几年后回头看，这次在年会宣布 996 绝对是好事……吗？"
    },
  ]
```

详细信息API:
```
https://news-at.zhihu.com/api/4/news/{id}
```

返回的数据：
```js
{
    body:"",
    image_source: "geralt / CC0",
    title: "几年后回头看，这次在年会宣布 996 绝对是好事……吗？",
    image: "https://pic1.zhimg.com/v2-2406c62d8d81279e329a40ef377922d0.jpg",
    share_url: "http://daily.zhihu.com/story/9707052",
    js: [ ],
    ga_prefix: "012907",
    images: [
    "https://pic3.zhimg.com/v2-da859c481335a7438279791fa6d0e13a.jpg"
    ],
    type: 0,
    id: 9707052,
    css: [
    "http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"
    ]
}
```

实现的目标：

首屏拉取最新列表信息，点击列表页面，进入详细信息，采用单Activit + 多Fragment来组织UI。

##### DataBinding
DataBinding支持MVVM，去掉Activity、Fragment中刷新UI的逻辑，让业务逻辑和UI代码分离。去掉了Activity、Fragment中使用代码对组件进行绑定。

将UI组件中的代码
```
TextView textView = findViewById(R.id.sample_text);
textView.setText(viewModel.getUserName());
```
转移到xml中
```
<TextView
    android:text="@{viewmodel.userName}" />
```

并且会保证在主线程中执行，也会保证不会出现空指针异常。

+ 准备

    + Android 4.0+ (API 14)
    + Gradle Plugin 1.5.0+
    + 修改build.gradle
    ```
    android {
        dataBinding {
            enabled = true
        }
    }
    ```
+ 布局文件

news_item.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="news"
            type="com.swx.jetpackxbasic.model.News"/>
        <variable
            name="callback"
            type="com.swx.jetpackxbasic.ui.NewsItemCallback"/>
    </data>
    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:minHeight="100dp"
        android:onClick="@{() -> callback.onClick(news)}"
        android:orientation="horizontal"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        app:cardUseCompatPadding="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:orientation="horizontal"
            android:layout_marginStart="8dp"
            android:layout_marginEnd="8dp"
            android:layout_height="120dp">
            <ImageView
                android:layout_marginStart="5dp"
                android:layout_gravity="center_vertical"
                app:fromUrl="@{news.image ?? news.images.get(0)}"
                android:layout_width="100dp"
                android:layout_height="100dp" />
            <TextView
                android:layout_width="wrap_content"
                android:layout_gravity="center_vertical"
                android:text="@{news.title}"
                android:layout_marginStart="10dp"
                android:textSize="16sp"
                android:padding="1dp"
                android:layout_height="wrap_content" />
        </LinearLayout>

    </androidx.cardview.widget.CardView>
</layout>
```
需要将以前的布局文件包在`layout`标签里面，增加<data>标签，存放数据相关内容。支持绑定数据，函数调用等。这里数据可以直接使用接口，并且能智能识别字段。

xml文件编译后会生成同名 + bing的文件。这里news_item.xml最终会生成NewsItemBinding的抽象类和NewsItemBindingImpl的实现类。

+ 在Activity中使用

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
}
```
+ 在Fragment中使用
```
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container, false);
        // ...
        return mBinding.getRoot();
    }
```

+ 绑定数据

```
mBinding.setIsLoading(true);
```
##### LiveData

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)是一种可观察的数据存储器，采用了观察者模式设计，其中LiveData是被观察者，当数据发生变化时会通知观察者进行数据更新。通过这点，可以确保数据和界面的实时性。

能有效的避免内存泄露，Fragment、Actvity等组件生命周期结束时会自动移除对liveData的观察。

能避免Activity、Fragment销毁时引起崩溃，因为除非Fragment、Activity组件处于活动状态，否则组件不会收到LiveData的的数据变动通知。

从上面的架构中也能看出，LiveData一般位于ViewModel中的，不在Activity等应用组件中。

+ LiveData使用

1.  创建LiveData实例来保存数据，常常是配合ViewModel一起工作。

```
// NewsViewModel.java
public class NewsListViewModel extends AndroidViewModel {
    private LiveData<List<News>> news;
    private final DataRepository dataRepo;

    public  NewsListViewModel(Application application){
        super(application);
        App app = (App)application;
        this.dataRepo = app.getDataRepository();
        news = dataRepo.getNewsList();
    }

    /**
     * @return LiveData<List<News>
     */
    public LiveData<List<News>> getNews(){
        return news;
    }
}

```

这里LiveData的创建位于DataRepository中。

2.  定义一个Observer的观察者对象，如果有数据更新会通过观察者的onChanged()方法来同步到UI上面，通常Fragment充当观察者。

```
// NewsListFragment.java

@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
    subscribeData(mViewModel.getNews());
}
```

3.  将观察者Observer通过observe()方法进行绑定。

```
private void subscribeData(LiveData<List<News>> liveData) {
    mBinding.setIsLoading(true);
    liveData.observe(this, newsEntities -> {
        if(newsEntities != null){
            mBinding.setIsLoading(false);
            mNewsAdapter.setNewsList(newsEntities);
        }
    } );
}
```

+ LiveData的数据更新

LiveData通过setValue和postValue来更新数据，setValue必须在UI线程。LiveData本身不能修改数据，子类MutableLiveData包含修改数据的方法。

```
public class MutableLiveData<T> extends LiveData<T> {
    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
}
```

##### ViewModel

[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)对象为特定的界面组件（如 Fragment 或 Activity）提供数据，并包含数据处理业务逻辑。ViewModel为View和Model之前的桥梁。

+ 生命周期

![image](https://note.youdao.com/yws/api/personal/file/WEB7af587c94620b6916e660037cbda6546?method=download&shareKey=3360429ca2c965eeaab11969b78cc0c2)

从图中可以看出，在第一次调用Activity对象的onCreate()方法时创建了一个ViewModel。在Activity运行过程中可能会多次调用onCreate()方法（比如当设备屏幕旋转时），但是ViewModel一直存在，直到Activity结束并销毁。这意味着ViewModel不会因为它的创建者的一个配置变化而被销毁，Activity 的新实例将与现有的ViewModel重新连接。

+ 依赖

```
implementation "androidx.fragment:fragment:1.0.0"
implementation "androidx.lifecycle:lifecycle-viewmodel:2.0.0"
implementation "androidx.lifecycle:lifecycle-extensions:2.0.0"
```

+ 实现

```java
public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}
```

+ 使用

```java
public class MyActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
        });
    }
}

```

+ 通过ViewModel在不同的Fragment之间传值

```java
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Item> selected = new MutableLiveData<Item>();

    public void select(Item item) {
        selected.setValue(item);
    }

    public LiveData<Item> getSelected() {
        return selected;
    }
}


public class MasterFragment extends Fragment {
    private SharedViewModel model;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        itemSelector.setOnClickListener(item -> {
            model.select(item);
        });
    }
}

public class DetailFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getSelected().observe(this, { item ->
           // Update the UI.
        });
    }
}
```

不同的Fragment属于相同的Activity，这样通过ViewModelProviders获取到的ViewModel是同一个实例，通过这个相同的实例，可以互相传递数据。

##### Room

[Room](https://developer.android.com/training/data-storage/room/index.html)是Google提供的一个ORM库。Room提供了三个主要的组件：

@Database：@Database用来注解类，并且注解的类必须是继承自RoomDatabase的抽象类。该类主要作用是创建数据库和创建Daos（data access objects，数据访问对象）。

@Entity：@Entity用来注解实体类，@Database通过entities属性引用被@Entity注解的类，并利用该类的所有字段作为表的列名来创建表。

@Dao：@Dao用来注解一个接口或者抽象方法，该类的作用是提供访问数据库的方法。在使用@Database注解的类中必须定一个不带参数的方法，这个方法返回使用@Dao注解的类

![image](https://note.youdao.com/yws/api/personal/file/WEBda5b76786654dfc3ff4e86ad719d7b82?method=download&shareKey=789e414d95d1d440ba73cc1faff05d1a)

+ 依赖
```
// versions.room = "2.0.0"

implementation "androidx.room:room-compiler:$versions.room"
implementation "androidx.room:room-runtime:$versions.room"
implementation "androidx.room:room-rxjava2:$versions.room"
implementation "androidx.room:room-common:$versions.room"
```
+ 使用Room

User
```
@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}
```

UserDao
```
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
```

AppDatabase
```
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
```

创建上面的文件后，就可以创建数据库的实例了

```
AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-name").build();
```

+ 原理

在编译时，使用annotationProcessor来解析被@Database和@Dao标注的类，在build/generated/source/apt里生成具体的Impl类，相比于SQLite API，一定程度上减少了我们创建表和部分CRUD书写的代码量

### 问题列表

+ 没有实现dataBinding

```
Android resource linking failed
error: attribute visibleGone (aka com.swx.jetpackxbasic:visibleGone) not found.
error: failed linking file resources.
```
解决方案一：

为app:visibleGone创建一个BindingAdapter

```java
public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
```

解决方案二：

```
    <data>
        <variable
            name="loading"
            type="boolean" />
        <import type="android.view.View"/> <!-- remember to import -->
    </data>
    <LinearLayout >
        <Button
            android:visibility="@{loading ? View.GONE : View.VISIBLE}"
    </LinearLayout>
```

+ room警告

```
Room - Schema export directory is not provided to the annotation processor so we cannot export the schema
```
需要配置room的scheme输出文件的位置，这样就能轻松跟踪数据库变动的历史。

```
android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
            }
        }
    }
}
```

+ databing失败

```
找不到符号
符号:   类 DataBindingComponent
```

xml文件错误，或者room错误导致生成databing错误

+ Gson转换失败

```
java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
```

Gson转换的时候，内容和目标必须匹配，gson转换的时候，需要使用对象，不能使用接口。

+ Guava冲突

```
Program type already present: 
com.google.common.util.concurrent.ListenableFuture

Message{kind=ERROR, text=Program type already present: 
com.google.common.util.concurrent.ListenableFuture, sources=[Unknown source 
file], tool name=Optional.of(D8)}
```

解决方案：

```
configurations {
    all*.exclude group: 'com.google.guava', module: 'listenablefuture'
}
```

+ webview和CoordinatorLayout不兼容

