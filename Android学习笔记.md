## 1. compileOnly, api, debugImplementation, implementation的使用
    1. compileOnly 只是在编译有效, 当前model不会参与打包. 你要确保别的model有在打包依赖它, 否则打包成apk没有代码; 一般主APP依赖一个库, 组件model可以使用此模式;
    2. api: 共享效果, 参与编译和打包. 一个model使用api, 主app项目依赖这个model, 也会享有依赖库(model)里依赖的库其中的代码
    3. implementation: 参与编译和打包: 独享效果. 只在当前model有效
    4. debugImplementation: 参与编译和打包, 只在debug有效

## 2. 学习gradle
    build.gradle
        apply:
        android:
        dependencies:


    manifestPlaceholders字段 build.gradle 中可以使用manifestPlaceholders字段数组类型,
    设置key:value 格式, 相当于定义字段了, 然后在AndroidManifest 直接$使用;方便了数据的统一管理

## 3. 存储目录
    私有目录 data/data/包名/   是不需要权限直接就可以往里写文件的
    SD卡目录 Environment.getExternalStorageDirectory() 需要动态权限, 才能读写操作

## 4. inline 字段
    默认这个字段是内联的意思, 就是你调用带inline方法时, 他的代码是移动到你的方法来一起执行, 避免了调用方法的进栈和出栈的操作

## 5. run和apply
    如果你要是{}lambda表达式没有返回值, 可以使用run , this表示自己 方便设置参数. 否则使用apply方法返回值为调用者自己
    如果lambda里如要传递自己本身使用带it的 如: also, let


## 6. 约束布局
    1.每个控件必须有id
    2.constraintLayout可以手动拖动,灵活
    3.按照比例约束位置和尺寸
    4.margin 负值不其效果
    5.只能显示一页, 超出不会显示, 为此你可能需要scrollView 
        
    位置:
    layout constraintTop toTopOf   第一个top属性表示当前view的位置, 第二个top表示目标view的位置
    
    layout_constraintTop_toTopOf ------- -----期望视图的上边对齐另一个视图的上边。
    layout_constraintTop_toBottomOf--------期望视图的上边对齐另一个视图的底边。
    layout_constraintTop_toLeftOf ------------期望视图的上边对齐另一个视图的左边。
    layout_constraintTop_toRightOf -----------期望视图的上边对齐另一个视图的右边。
    layout_constraintBottom_toTopOf ---------期望视图的下边对齐另一个视图的上边。
    layout_constraintBottom_toBottomOf----- 期望视图的底边对齐另一个视图的底边。
    layout_constraintBottom_toLeftOf---------期望视图的底边对齐另一个视图的左边。
    layout_constraintBottom_toRightOf -------期望视图的底边对齐另一个视图的右边。
    layout_constraintLeft_toTopOf ------------- 期望视图的左边对齐另一个视图的上边。
    layout_constraintLeft_toBottomOf ---------期望视图的左边对齐另一个视图的底边。
    layout_constraintLeft_toLeftOf -------------期望视图的左边对齐另一个视图的左边。
    layout_constraintLeft_toRightOf ------------期望视图的左边对齐另一个视图的右边。
    layout_constraintRight_toTopOf------------ 期望视图的右边对齐另一个视图的上边。
    layout_constraintRight_toBottomOf --------期望视图的右边对齐另一个视图的底边。
    layout_constraintRight_toLeftOf ------------期望视图的右边对齐另一个视图的左边。
    layout_constraintRight_toRightOf -----------期望视图的右边对齐另一个视图的右边。
    
    可见性的位置: goneMargin
    goneMargin主要用于约束的控件可见性**被设置为gone的时候** 会生效的margin值，属性如下：
    layout_goneMarginStart
    layout_goneMarginEnd
    layout_goneMarginLeft
    layout_goneMarginTop
    layout_goneMarginRight
    layout_goneMarginBottom_
    
    文字的文本对齐:
    layout_constraintBaseline_toBaselineOf
    
    居中和偏移:
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent”
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    
    四都设置是居中,  设置left 和 right 就是水平居中
    光约束上面就是以上面对齐排列, 上下约束就是垂直居中
    
    layout_constraintHorizontal_bias 水平偏移比例
    
    尺寸约束:
    可以设置 0dp, 配合代替match_parent;
    在使用layout_marginBottom时必须先约束自己的位置
  
    <android.support.constraint.ConstraintLayout 
        android:layout_width="match_parent"
        android:layout_height="match_parent">
     
        <TextView
            android:id="@+id/TextView1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:layout_marginTop="10dp" 
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
     
    </android.support.constraint.ConstraintLayout>
    
    
    链:
    3个TextView 相互链在一起, layout_constraintHorizontal_weight 可以设置 3个TextView的相对位置, 聚在一起或是展示在2边
    
    辅助工具:
    android.support.constraint.Group 属性app:constraint_referenced_ids “设置多个控件的id” , 可以对他们一起控制(隐藏)
    
    
    Guideline: 辅助线, 不会显示到界面上, 但是能帮你定位; 先思考需要定位那些线
    android:orientation 垂直vertical，水平horizontal
    layout_constraintGuide_begin 开始位置
    layout_constraintGuide_end 结束位置
    layout_constraintGuide_percent 距离顶部的百分比(orientation = horizontal时则为距离左边)


## ANR文件的导出
    adb bugreport; 运行后会在当前studio的根目录产生一个文件夹.

## style的高度复用
    以后编写UI时, 先看设计图, 那些是类似的重复的, 抽取一个style(方法)复用其代码

## 成员变量
    成员变量不要直接在方法中使用, 应该转成局部变量(去掉m前缀)

## 裁剪图片
   通过path构造各种形状, 利用canvas.clipPath()可以把一个bitmap裁剪各种样子.
   Region可以保存路径, 利用drawRect绘制各种样子

## canvas
    裁剪画布禁用硬件加速
    canvas在你执行draw的各种方法,对画布造成的影响的不可逆的;比如你对画布clip成绿色,随后操作都在这个绿色画布上了;
    save()是用来保存当前画布的,把画布存到一个栈中; restore来弹出当前栈的画布,使用保存前的状态

## Android 资源文件命名与使用
    模块名-业务-控件描述-控件状态
    other_login_btn_pressed
    persional_login_activity

    dimen 资源
    module_horizontal_line_height

   View 组件的资源 id 建议以 View 的缩写
   progress_bar,ll,rl

## 泛型
   泛型只是在编译期有效, 它只是利用编译器帮我们做类型转换
   泛型类是在创建对象时, 指明其类型; 泛型方法是调用其方法时指明泛型的具体类型

    RootBean<你的实体类>通过类名的泛型 指定bean里data的返回数据类型
    class RootBean<T>{
        public T data;
    }

    //泛型类, 这里是泛型声明
    class Generic<T extend Number>{
        T t;
         Generic(T t){ // 泛型会从构造传进来
            this.t = t;
         }
    }

    //泛型方法, 返回值左边是泛型声明
    public static <T extend Number> T show(T t){

    }

## 泛型在继承中使用
    ```
     public class BaseGeneric<T extends Number> {

        public T data;

        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }

        //子类也可以定义自己的泛型, 如E 和 T 不关联; 泛型类的声明使用逗号隔开来声明多个
        static class Generic<E extends String, T extends Integer> extends BaseGeneric<T> {
            public E ChildData;
            public void setChildData(E childData) {
                ChildData = childData;
            }
            public E getChildData() {
                return ChildData;
            }
        }

        public static void main(String[] args) {
            Generic generic = new Generic();
            generic.setChildData("111");
            generic.setData(222);    //父类已经限定类型;子类可以从写方法, 其范围只能是缩小
            System.out.println(generic.getData());
            System.out.println(generic.getChildData());
        }
    }
    ```

## 泛型在集合中的应用

public class Fruit<T> {

    public T field;

    Fruit(T t) {
        this.field = t;
    }

    public void setField(T field) {
        this.field = field;
    }

    Fruit() {}

    static class Apple extends Fruit<Integer> { //对类型缩小限制
        @Override
        public void setField(Integer field) {
            super.setField(field);
        }
    }
    public static void main(String[] args) {
        //List<Fruit<Long>> list = new ArrayList<>();
        //list.add(new Fruit<Long>());

        //<List<Map<Apple, List<Map<String, Integer>>>>> 这么一堆只是表示Fruit的那个成员变量的类型而已
        Fruit<List<Map<Apple, List<Map<String, Integer>>>>>
            fruit = new Fruit<>();

        //其成员变量是list集合
        ArrayList<Map<Apple, List<Map<String, Integer>>>> list = new ArrayList<>();
        fruit.field = list;

        //map的key是一个对象, value是一个集合
        HashMap<Apple, List<Map<String, Integer>>> map = new HashMap<>();
        list.add(map);//集合的元素是map

        map.put(new Apple(),new ArrayList<Map<String, Integer>>());
        //遍历map取出key和value
        map.forEach(new BiConsumer<Apple, List<Map<String, Integer>>>() {
            @Override
            public void accept(Apple apple, List<Map<String, Integer>> maps) {

            }
        });

    }

## 泛型通配符
   当使用的具体类型不确定或是不需要使用类型的具体功能, 并不关心其类型, 可以用?表示未知类型

     public static void main(String[] args) {
           Generic<String,Integer> generic = new Generic();
           generic.setChildData("111");
           generic.setData(222);    //父类已经限定类型;子类可以从写方法, 其范围只能是缩小
           System.out.println(generic.getData());
           System.out.println(generic.getChildData());
           Generic<?, ?> generic1 = Generic.getInstance();
           show(generic1);
       }

       public static void show(Generic<?,?> generic){
           System.out.println(generic.getChildData());
       }

        //虽然这里是<?> 但我们在BaseGeneric类声明时已经限定它只能是Number或Number的子类,所以可以调用number的方法
       public static void show(BaseGeneric<?> generic){
           System.out.println(generic.getData().intValue());
       }

## 加密算法
    MD5,SHA-1、SHA-256 等常用算法是 hash算法, 逆向困难;
    用户的密码, 进行传输时, 就要使用加密算法, 这样黑客拿到加密后密码, 因为不可逆, 也无法知道明文(无法登陆你的账号)

## Android_id唯一标示
    9.0系统
    1.未签名的2个不同项目, 是相同的Android_id
    2.不同签名不同包, Android_id是不一样的
    3.同一个签名不同包, Android_id是一样的

    6.0系统
    1. Android_id固定的不变

        public String getAndroid_id() {
            String ANDROID_ID = "";
            try {
                ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ANDROID_ID;
        }


## 注解
    eventBus 通过定义注解, register保存类名, 然后在post时, 通过类名利用反射找到对应的注解, 反射调用对应的方法, 传递参数
    
    Todo todoAnnotation = (Todo)method.getAnnotation(Todo.class);

## 多渠道打包
    buildtypes: 根据不同的编译环境打包 比如有debug、release、beta等环境参数




